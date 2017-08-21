package com.lecheng.hello.thirdapp.ActivityList;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.lecheng.hello.thirdapp.ActivityList.Test.Aty058_1;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.UrlUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lecheng.hello.thirdapp.Utils.MyApplication.getHttpQue;

//多图分享到微信：http://blog.csdn.net/xiazdong/article/details/7687439
public class Aty058Share extends ActionBarActivity {
    private int IMAGE_NAME = 0;
    ArrayList<String> arrImg = new ArrayList<String>();
    ArrayList<String> arrShare = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty058_share);
        ButterKnife.bind(this);
        arrImg.add("http://wx4.sinaimg.cn/mw690/48d84e6fly1firh5odb69j20ps0fvwwk.jpg");
        arrImg.add("http://fanyi.baidu.com/static/translation/img/header/logo_cbfea26.png");
        arrImg.add("https://os.alipayobjects.com/rmsportal/uGWcpAFgWdynxBy.png");
        arrImg.add("https://ss0.baidu.com/73t1bjeh1BF3odCf/it/u=2512058291,4137050375&fm=85&s=5B5E38D210FE598C627CB649030040F5");
    }


    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                break;
            case R.id.btn2:
                getImg(arrImg);
                break;
            case R.id.btn3:
                System.out.println("Environment:" + Environment.getExternalStorageDirectory());
                break;
        }
    }

    private void getImg(final ArrayList<String> arrUrl) {
        for (int i = 0; i < arrUrl.size(); i++) {
            final int finalI = i;
            ImageRequest imageRequest = new ImageRequest(arrUrl.get(i), new Response.Listener<Bitmap>() {
                @Override//图片保存方法http://blog.csdn.net/guolin_blog/article/details/17482165/
                public void onResponse(Bitmap bm) {
                    System.out.println("Volley-Success");
                    File file = new File(Environment.getExternalStorageDirectory() + "/Android/img/", finalI + ".jpg");
                    File fDir = new File(Environment.getExternalStorageDirectory() + "/Android/img/");
                    if (!fDir.exists()) fDir.mkdir();
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.PNG, 90, out);
                        out.flush();
                        out.close();
                        arrShare.add(Environment.getExternalStorageDirectory() + "/Android/img/" + finalI + ".jpg");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println(e + "");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (finalI == arrUrl.size() - 1) share2Wx(arrShare);
                }
            }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("VolleyError error");
                }
            });
            getHttpQue().add(imageRequest);
        }
    }

    public void share2Wx(ArrayList<String> arrayList) {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putExtra("Kdescription", "这是我分享的图片");
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("arrSHare:" + arrayList.get(i));
            imageUris.add(Uri.fromFile(new File(arrayList.get(i))));
        }
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        startActivity(intent);

    }

}
