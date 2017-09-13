package com.lecheng.hello.thirdapp.Interface;

import android.content.Context;

import java.util.HashMap;

public interface IWModel {
    void http047Get(Context context, String url, IWListener listener);

    void http047Post(Context context, String url, HashMap<String,String> hashMap, IWListener listener);

    void http047Cancel(String tag);
}