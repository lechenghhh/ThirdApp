package com.lecheng.hello.thirdapp.ActivityListItem;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.UploadThread;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


public class Aty033UploadFile extends AppCompatActivity {
//    private String newName = "bc.jpg";
//    //要上传的本地文件路径
//    private String uploadFile = "/storage/emulated/0/bc.jpg";
//    //上传到服务器的指定位置
//    private String actionUrl = "http://tests.tctu.cn/mapi.php/UploadImg/upload";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty033_upload);
        findViewById(R.id.myButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://tests.tctu.cn/mapi.php/UploadImg/upload";
                File file = Environment.getExternalStorageDirectory();
                File fielAbs = new File(file, "bc.jpg");
                String fileName = fielAbs.getAbsolutePath();
                System.out.println(fileName);
                UploadThread httpThread = new UploadThread(getApplicationContext(), url, fileName);
                httpThread.start();
//                new Thread() {
//                    @Override
//                    public void run() {
//                        uploadFile();
//                    }
//                }.start();


            }
        });
    }

//    private void uploadFile() {
//        String end = "/r/n";
//        String Hyphens = "--";
//        String boundary = "*****";
//        try {
//            URL url = new URL(actionUrl);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//      /* 允许Input、Output，不使用Cache */
//            con.setDoInput(true);
//            con.setDoOutput(true);
//            con.setUseCaches(false);
//      /* 设定传送的method=POST */
//            con.setRequestMethod("POST");
//      /* setRequestProperty */
//            con.setRequestProperty("Connection", "Keep-Alive");
//            con.setRequestProperty("Charset", "UTF-8");
//            con.setRequestProperty("Content-Type",
//                    "multipart/form-data;boundary=" + boundary);
//      /* 设定DataOutputStream */
//            DataOutputStream ds = new DataOutputStream(con.getOutputStream());
//            ds.writeBytes(Hyphens + boundary + end);
//            ds.writeBytes("Content-Disposition: form-data;"
//                    + "name=\"file\";filename=" + "bc.jpg" + "\"" + end);
//            ds.writeBytes(end);
//      /* 取得文件的FileInputStream */
//            FileInputStream fStream = new FileInputStream(uploadFile);
//      /* 设定每次写入1024bytes */
//            int bufferSize = 1024;
//            byte[] buffer = new byte[bufferSize];
//            int length = -1;
//      /* 从文件读取数据到缓冲区 */
//            while ((length = fStream.read(buffer)) != -1) {
//        /* 将数据写入DataOutputStream中 */
//                ds.write(buffer, 0, length);
//            }
//            ds.writeBytes(end);
//            ds.writeBytes(Hyphens + boundary + Hyphens + end);
//            fStream.close();
//            ds.flush();
//      /* 取得Response内容 */
//            InputStream is = con.getInputStream();
//            int ch;
//            StringBuffer b = new StringBuffer();
//            while ((ch = is.read()) != -1) {
//                b.append((char) ch);
//            }
//            System.out.println("上传成功");
//            Toast.makeText(getApplicationContext(), "上传成功", Toast.LENGTH_LONG)
//                    .show();
//            ds.close();
//        } catch (Exception e) {
//            System.out.println("上传失败" + e.getMessage());
//            Toast.makeText(getApplicationContext(), "上传失败" + e, Toast.LENGTH_LONG).show();
//        }
//    }
}