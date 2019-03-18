package com.lecheng.hello.thirdapp.Interface;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import java.util.HashMap;
import java.util.Map;

import static com.lecheng.hello.thirdapp.Utils.MyApplication.getHttpQue;


public class HttpVolley {
    public static final String CANCEL_GET = "cancelGet";
    public static final String CANCEL_POST = "cancelPost";
    private Context context;

    public HttpVolley(Context context) {
        this.context = context;
    }

    //Get请求
    public void Get(final String url, final OnResponseListener listener) {
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        System.out.println(url);
                        System.out.println("HttpVolley-Success:\n" + s);
                        listener.onSuccess(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
//                        listener.onError();
                        Toast.makeText(context, "请求失败:\n" + e, Toast.LENGTH_LONG).show();
                    }
                });
        request.setTag(CANCEL_GET);
        getHttpQue().add(request);
    }

    //Post请求
    public void Post(String url, final HashMap<String, String> hashMap, final OnResponseListener listener) {
        StringRequest request2 = new StringRequest
                (Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        System.out.println("HttpVolley-Success:\n" + s);
                        listener.onSuccess(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
//                        listener.onError();
                        Toast.makeText(context, "请求失败:\n" + e, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = hashMap;
                return map;
            }
        };
        request2.setTag(CANCEL_POST);
        getHttpQue().add(request2);
    }

    //取消队列
    public void Cancel(String tag) {
        getHttpQue().cancelAll(tag);
    }
}