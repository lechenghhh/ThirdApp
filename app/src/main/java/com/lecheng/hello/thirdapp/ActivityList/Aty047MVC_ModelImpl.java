package com.lecheng.hello.thirdapp.ActivityList;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.Utils.MyApplication;

/**
 * Description:从网络获取天气信息接口实现
 * User: xjp
 * Date: 2015/6/3
 * Time: 15:40
 */

public class Aty047MVC_ModelImpl implements Aty047MVC_Model {

    @Override
    public void getWeather(String cityNumber, final Aty047MVC_Listener listener) {
        String url = "http://www.weather.com.cn/data/sk/" + cityNumber + ".html";
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        listener.onSuccess(s);
                        System.out.println("Aty047-onResponse");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        listener.onError();
                        System.out.println("Aty047-onErrorResponse");
                    }
                });
        request.setTag("cancel047");
        MyApplication.getHttpQue().add(request);
    }


}