package com.lecheng.hello.thirdapp.ActivityListItem;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.ActivityListItem.OtherActivity.UploadPicAty;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyApplication;


public class Aty033UploadFile extends AppCompatActivity {
    //    private String newName = "bc.jpg";
//    //要上传的本地文件路径
//    private String uploadFile = "/storage/emulated/0/bc.jpg";
//    //上传到服务器的指定位置
//    private String actionUrl = "http://tests.tctu.cn/mapi.php/UploadImg/upload";
    private TextView aty33_myText4;
    private Button btn;
    private ProgressDialog pd;
    private String img_android = "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";
    private String imgstr = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity033);

        File fielAbs = new File(Environment.getExternalStorageDirectory(), "bc.jpg");
        final String filePath = fielAbs.getAbsolutePath();
        TextView tv1 = (TextView) findViewById(R.id.aty33_myText3);
        aty33_myText4 = (TextView) findViewById(R.id.aty33_myText4);
        btn = (Button) findViewById(R.id.aty33_btn2);
        tv1.setText(filePath);

        findViewById(R.id.aty33_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volley_post();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UploadPicAty.class));
            }
        });
        findViewById(R.id.aty33_btn_tct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Aty033UploadFile2.class));
            }
        });
    }

    private void volley_post() {
        String url2 = "http://tests.tctu.cn/mapi.php/UploadImg/upload";
        StringRequest request2 = new StringRequest
                (Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(getApplicationContext(), "成功" + s, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), "请求失败\n" + volleyError, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("file", img_android);
                return map;
            }
        };
        request2.setTag("cancelPost33");
        MyApplication.getHttpQue().add(request2);
    }

//                httpThread.start();
//                volley_post();

}