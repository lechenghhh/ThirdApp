package com.lecheng.hello.thirdapp.Interface;

import android.content.Context;

import java.util.HashMap;

public interface I047Model {
    void http047Get(Context context, String url, I047Listener listener);

    void http047Post(Context context, String url, HashMap<String,String> hashMap, I047Listener listener);

    void http047Cancel(String tag);
}