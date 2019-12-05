package com.lecheng.hello.thirdapp.Interface;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import java.util.HashMap;
import java.util.Map;

import static com.lecheng.hello.thirdapp.Utils.MyApplication.getHttpQue;


public class HttpVolley {
    public static final String CANCEL_GET = "CANCEL_GET";
    public static final String CANCEL_POST = "CANCEL_POST";
    public static final String CANCEL_GET_HEADER = "CANCEL_GET_HEADER";
    public static final String CANCEL_POST_HEADER = "CANCEL_POST_HEADER";
    private Context context;

    public HttpVolley(Context context) {
        this.context = context;
    }

    //Get请求
    public void Get(final String url, final OnResponseListener listener) {
        System.out.println(url);
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
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
                });
//        request.setRetryPolicy(new DefaultRetryPolicy(2 * 1000, 0, 1.0f));//超时2*1000ms
        request.setTag(CANCEL_GET);
        getHttpQue().add(request);
    }

    //Post请求
    public void Post(String url, final HashMap<String, String> keyValue, final OnResponseListener listener) {
        System.out.println(url);
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
                HashMap<String, String> map = keyValue;
                return keyValue;
            }
        };
//        request2.setRetryPolicy(new DefaultRetryPolicy(2 * 1000, 0, 1.0f));//超时2*1000ms
        request2.setTag(CANCEL_POST);
        getHttpQue().add(request2);
    }

    //取消队列
    public void Cancel(String tag) {
        getHttpQue().cancelAll(tag);
    }

    //Get请求,加入header
    public void Get(String url, final Map<String, String> header, final OnResponseListener listener) {
        System.out.println(url);
        HeaderStringRequest request = new HeaderStringRequest(Request.Method.GET, url, new HeaderInterface() {
            @Override
            public Map<String, String> addHeader() {
                return header;
            }
        }, new Response.Listener<String>() {
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
        });
//        request.setRetryPolicy(new DefaultRetryPolicy(2 * 1000, 0, 1.0f));//超时2*1000ms
        request.setTag(CANCEL_GET_HEADER);
        getHttpQue().add(request);
    }

    //Post请求加入header
    public void Post(String url, Map<String, String> header, final HashMap<String, String> keyValue, final OnResponseListener listener) {
        System.out.println(url);
        HeaderStringRequest request2 = new HeaderStringRequest(Request.Method.POST, url, new HeaderInterface() {
            @Override
            public Map<String, String> addHeader() {
                return null;
            }
        }, new Response.Listener<String>() {
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
                HashMap<String, String> map = keyValue;
                return map;
            }
        };
//        request2.setRetryPolicy(new DefaultRetryPolicy(2 * 1000, 0, 1.0f));//超时2*1000ms
        request2.setTag(CANCEL_POST_HEADER);
        getHttpQue().add(request2);
    }
}