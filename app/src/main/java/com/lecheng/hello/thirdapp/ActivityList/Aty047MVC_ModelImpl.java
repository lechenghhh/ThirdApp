package com.lecheng.hello.thirdapp.ActivityList;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.Utils.MyApplication;
import com.lecheng.hello.thirdapp.Utils.MyToast;

/**
 * Description:从网络获取天气信息接口实现
 * User: xjp
 * Date: 2015/6/3
 * Time: 15:40
 */

public class Aty047MVC_ModelImpl implements Aty047MVC_Model {

    @Override
    public void getWeather(final Context c, String url, final Aty047MVC_Listener listener) {
        StringRequest request = new StringRequest
                (Request.Method.GET, "http://" + url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        listener.onSuccess(s);
                        System.out.println("Aty047-onResponse");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
//                        listener.onError();
                        new MyToast(c, "请求失败:\n" + e, 3333);
                    }
                });
        request.setTag("cancel047");
        MyApplication.getHttpQue().add(request);
    }


}