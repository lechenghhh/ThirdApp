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
    public static Context mContext;
    private int IMAGE_NAME = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty058_share);
        ButterKnife.bind(this);
        mContext = this;
    }


    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
//                for (int i = 0; i < lenght; i++) {
//                    File file = saveImageToSdCard(images.get(i));
//                    files.add(file);
//                    F.makeLog(file.toString());
//                }
//
//
                Intent intent = new Intent();
                ComponentName comp = new ComponentName("com.tencent.mm",
                        "com.tencent.mm.ui.tools.ShareToTimeLineUI");
                intent.setComponent(comp);
                intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                intent.setType("image/*");
                intent.putExtra("Kdescription", "这是我分享的图片");
                ArrayList<Uri> imageUris = new ArrayList<Uri>();
//                for (File f : files) {
//                    imageUris.add(Uri.fromFile(f));
//                }
                imageUris.add(Uri.fromFile(
                        new File("http://avatar.csdn.net/9/2/1/1_u011340932.jpg")));
//                imageUris.add(Uri.fromFile(
//                        new File(Environment.getExternalStorageDirectory() + "/timg.jpeg")));
//                imageUris.add(Uri.fromFile(
//                        new File(Environment.getExternalStorageDirectory() + "/timg.jpeg")));
                intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                startActivity(intent);
                break;
            case R.id.btn2:
                final String url = "http://wx4.sinaimg.cn/mw690/48d84e6fly1firh5odb69j20ps0fvwwk.jpg";
                ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                    @Override//图片保存方法http://blog.csdn.net/guolin_blog/article/details/17482165/
                    public void onResponse(Bitmap response) {
//                                imageView.setImageBitmap(response);
                        System.out.println("Volley-Success");
                        saveBitmap(response, url);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("VolleyError error");
                    }
                });
                getHttpQue().add(imageRequest);
                break;
            case R.id.btn3:
                System.out.println("Environment:" + Environment.getExternalStorageDirectory());
//                startActivity(new Intent(this, Aty058_1.class));
                break;
        }
    }

    public void saveBitmap(Bitmap bm, String picName) {

        System.out.println("保存图片...");
        File f = new File(Environment.getExternalStorageDirectory() + "/Android/", picName.replace("/", ""));
        if (f.exists()) f.delete();
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            System.out.println("保存成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
