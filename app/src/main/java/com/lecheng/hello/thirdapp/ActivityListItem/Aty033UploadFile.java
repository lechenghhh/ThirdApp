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
    private final String URL = "http://tests.tctu.cn/mapi.php/UploadImg/upload";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty033_upload);
        findViewById(R.id.myButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File fielAbs = new File(Environment.getExternalStorageDirectory(), "bc.jpg");
                String fileName = fielAbs.getAbsolutePath();
                System.out.println(fileName);
                UploadThread httpThread = new UploadThread(getApplicationContext(), URL, fileName);
                httpThread.start();
            }
        });
    }

}