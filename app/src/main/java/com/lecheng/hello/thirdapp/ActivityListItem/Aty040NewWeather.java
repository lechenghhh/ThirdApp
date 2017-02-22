package com.lecheng.hello.thirdapp.ActivityListItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.Bean.Bean040Weather;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.GsonUtil;
import com.lecheng.hello.thirdapp.Utils.MyApplication;

import java.util.HashMap;
import java.util.Map;

public class Aty040NewWeather extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty040_new_weather);
    }

    private void Volley_post() {
        String url2 = "http://fanyi.youdao.com/openapi.do?";
        StringRequest request2 = new StringRequest
                (Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        resolveJson(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Aty040NewWeather.this,"failed222",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map= new HashMap<String,String>();
                map.put("keyfrom","951827860");
                return map;
            }
        };
        request2.setTag("cancelweather");
        MyApplication.getHttpQueue().add(request2);
    }

    private void resolveJson(String strJson) {//解析json方法，并呈现
        final Bean040Weather bean= GsonUtil.GsonToBean(strJson, Bean040Weather.class);
//        //呈现到列表上
//        mListView.setAdapter(new CommonAdapter<BeanSearch.list>(Search.this, beanSearch.getLists(), R.layout.cell62_hot) {
//
    }


}
