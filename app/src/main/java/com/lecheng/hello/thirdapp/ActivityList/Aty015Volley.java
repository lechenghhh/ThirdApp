package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.Utils.MyApplication;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import java.util.HashMap;
import java.util.Map;

public class Aty015Volley extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty015);
        tv = (TextView) findViewById(R.id.volley_tv);
        findViewById(R.id.volley_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyGet();

            }
        });
        findViewById(R.id.volley_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyPost();
            }
        });
    }

    private void volleyGet() {
        String url = "http://www.baidu.com";
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        new MyToast(Aty015Volley.this, s, 3000);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Aty015Volley.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
        request.setTag("cancelGet");
        MyApplication.getHttpQue().add(request);
    }

    private void volleyPost() {
        String url2 = "http://fanyi.youdao.com/openapi.do?";
        StringRequest request2 = new StringRequest
                (Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(Aty015Volley.this, s, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Aty015Volley.this, "failed222", Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("keyfrom", "951827860");
                map.put("key", "123087914");
                map.put("type", "data");
                map.put("doctype", "json");
                map.put("version", "1.1");
                map.put("q", "Chinese");
                return map;
            }
        };
        request2.setTag("cancelPost");
        MyApplication.getHttpQue().add(request2);
    }
}
