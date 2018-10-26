package com.lecheng.hello.thirdapp.Utils;

import android.util.LruCache;

public class MyLruCache<T> extends LruCache<String, T> {

    private static volatile MyLruCache instance = null;
    private T t;

    private MyLruCache(int maxSize) {
        super(maxSize);
    }

    public static <T> MyLruCache getInstance() {
        if (instance == null) {
            synchronized (MyLruCache.class) {
                if (instance == null) {
                    instance = new MyLruCache<T>(1024 * 1024 * 20);
                }
            }
        }
        return instance;
    }
}