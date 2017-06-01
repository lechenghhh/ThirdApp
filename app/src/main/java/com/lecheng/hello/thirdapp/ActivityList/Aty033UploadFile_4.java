package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MySP;
import com.lecheng.hello.thirdapp.Utils.MyToast;
import com.lecheng.hello.thirdapp.Utils.MyUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//17-6-1测试成功
public class Aty033UploadFile_4 extends ActionBarActivity {
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    private String URL_UPLOAD = "http://10.0.110.134:8085/Upload";//java后台地址
    //    private String URL_UPLOAD = "http://10.0.110.134:8086/file_upload";//nodejs地址
    private ImageLoader il = ImageLoader.getInstance();
    private String srcPath = "";
    private final int TIME_OUT = 30 * 1000;   //超时时间
    private final String CHARSET = "utf-8"; //设置编码
    @Bind(R.id.iv1)
    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty033_3);
        ButterKnife.bind(this);
        tv1.setText("服务器Url：" + URL_UPLOAD);
    }

    @OnClick({R.id.btnTakePic, R.id.btnSelect, R.id.btnUpload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSelect:
                if (!srcPath.equals(""))
                    submitUploadFile();
                startActivityForResult(new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT), 2);
                break;
            case R.id.btnTakePic:
                if (!srcPath.equals(""))
                    submitUploadFile();
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 1);
                break;
            case R.id.btnUpload:
                if (srcPath.equals(""))
                    Toast.makeText(this, "文件不存在！！！", Toast.LENGTH_LONG).show();
                else
                    submitUploadFile();
                finish();
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {    //拍照上传
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode" + requestCode + "resultCode" + resultCode);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    Bundle extras = data.getExtras();
                    Bitmap b = (Bitmap) extras.get("data");
                    String name = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    String fileNmae = Environment.getExternalStorageDirectory().toString() + File.separator + "tct/image/" + name + ".jpg";
                    srcPath = fileNmae;
                    tv2.setText("资源文件Uri：" + srcPath);
                    System.out.println("保存路径1----------:" + srcPath);
                    File myCaptureFile = new File(fileNmae);
                    try {
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            if (!myCaptureFile.getParentFile().exists()) {
                                myCaptureFile.getParentFile().mkdirs();
                            }
                            BufferedOutputStream bos;
                            bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                            b.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                            bos.flush();
                            bos.close();
                        } else {
                            new MyToast(Aty033UploadFile_4.this, "保存失败，SD卡无效", Toast.LENGTH_SHORT);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    srcPath = MyUtils.getPath(Aty033UploadFile_4.this, data.getData());//Uri转Url
                    tv2.setText("资源文件Uri：" + srcPath);
                    System.out.println("Uri:" + data.getData() + "\n保存路径2----------:" + srcPath);
                    break;
            }
            il.displayImage("file://" + srcPath, iv1);
        }
    }

    private void submitUploadFile() {
        final File file = new File(srcPath);
        final String RequestURL = URL_UPLOAD;
        if (file == null || (!file.exists())) {
            return;
        }
        System.out.println("请求的URL=========" + URL_UPLOAD + "\n请求的fileName====" + file.getName());
        final Map<String, String> params = new HashMap<String, String>();
//        params.put("image", "image");
        params.put("file", "file");//这里修改文件的键名

        new Thread(new Runnable() { //开启线程上传文件
            @Override
            public void run() {
                try {
//                    Looper.getMainLooper();//获取主线程looper以便接下来toast
                    JSONObject jsonObject = new JSONObject(uploadFile(file, URL_UPLOAD, params));
                    //把图片列表存在sp中的pic_id键值对中
                    String strPicId = (String) MySP.loadData(Aty033UploadFile_4.this, "pic_id", "");
                    strPicId = strPicId + jsonObject.get("pic_id") + "-";
                    MySP.saveData(Aty033UploadFile_4.this, "pic_id", strPicId);
//                    new MyToast(Aty033UploadFile_2.this, "上传成功", 3000);
//                    finish();
                } catch (Exception e) {
//                    new MyToast(Aty033UploadFile_2.this, "上传失败\n" + e, 3000);
                }
            }
        }).start();
    }

    private String uploadFile(File file, String RequestURL, Map<String, String> param) {
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString();  //边界标识   随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data";   //内容类型
        // 显示进度框
//      showProgressDialog();
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true);  //允许输入流
            conn.setDoOutput(true); //允许输出流
            conn.setUseCaches(false);  //不允许使用缓存
            conn.setRequestMethod("POST");  //请求方式
            conn.setRequestProperty("Charset", CHARSET);  //设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            if (file != null) {
                // 当文件不为空，把文件包装并且上传
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                StringBuffer sb = new StringBuffer();

                String params = "";
                if (param != null && param.size() > 0) {
                    Iterator<String> it = param.keySet().iterator();
                    while (it.hasNext()) {
                        sb = null;
                        sb = new StringBuffer();
                        String key = it.next();
                        String value = param.get(key);
                        sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
                        sb.append("Content-Disposition: form-data; name=\"").append(key).append("\"").append(LINE_END).append(LINE_END);
                        sb.append(value).append(LINE_END);
                        params = sb.toString();
                        System.out.println("自带提示Log：" + key + "=" + params + "##");
                        dos.write(params.getBytes());
//                      dos.flush();
                    }
                }
                sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意：
                 * name里面的值为服务器端需要key   只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的   比如:abc.png
                 */
                sb.append("Content-Disposition: form-data; name=\"file\";filename=\"" + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: image/pjpeg; charset=" + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();

                /**
                 * 获取响应码  200=成功
                 * 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                System.out.println("响应回调response code=========:" + res);
                if (res == 200) {
                    InputStream input = conn.getInputStream();
                    StringBuffer sb1 = new StringBuffer();
                    int ss;
                    while ((ss = input.read()) != -1) {
                        sb1.append((char) ss);
                    }
                    result = sb1.toString();
                    System.out.println("返回结果result===============:" + result);
//                 // 移除进度框
//                  removeProgressDialog();
//                    finish();
                } else {
                    System.out.println("失败=========" + result);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
