package com.lecheng.hello.thirdapp.Net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.Interface.I047Listener;
import com.lecheng.hello.thirdapp.Interface.I047Model;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import java.util.HashMap;
import java.util.Map;

import static com.lecheng.hello.thirdapp.Utils.MyApplication.getHttpQue;

/*
*/

public class Http047 implements I047Model {

    @Override               //Get请求
    public void http047Get(final Context c, final String url, final I047Listener listener) {
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        System.out.println(url);
                        System.out.println("onResponse-Success:\n" + s);
                        listener.onSuccess(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
//                        listener.onError();
                        new MyToast(c, "请求失败:\n" + e, 3000);
                    }
                });
        request.setTag("cancelGet");
        getHttpQue().add(request);
    }

    @Override               //Post请求
    public void http047Post(final Context c, String url, final HashMap<String, String> hashMap, final I047Listener listener) {
        StringRequest request2 = new StringRequest
                (Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        System.out.println("onResponse-Success:\n" + s);
                        listener.onSuccess(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        new MyToast(c, "请求失败:\n" + e, 3000);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = hashMap;
                return map;
            }
        };
        request2.setTag("cancelPost");
        getHttpQue().add(request2);
    }

    @Override               //取消队列
    public void http047Cancel(String tag) {
        getHttpQue().cancelAll(tag);
    }
}