package com.lecheng.hello.thirdapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

public class MySoftReference {
    static private MySoftReference cache;
    /**
     * 用于Chche内容的存储
     */
    private Hashtable<String, MySoftRef> hashRefs;
    /**
     * 垃圾Reference的队列（所引用的对象已经被回收，则将该引用存入队列中）
     */
    private ReferenceQueue<Bitmap> q;

    /**
     * 继承SoftReference，使得每一个实例都具有可识别的标识。
     */
    private class MySoftRef extends SoftReference<Bitmap> {
        private String _key = "";

        public MySoftRef(Bitmap bmp, ReferenceQueue<Bitmap> q, String key) {
            super(bmp, q);
            _key = key;
        }
    }

    private MySoftReference() {
        hashRefs = new Hashtable<String, MySoftRef>();
        q = new ReferenceQueue<Bitmap>();
    }

    /**
     * 取得缓存器实例
     */
    public static MySoftReference getInstance() {
        if (cache == null) {
            cache = new MySoftReference();
        }
        return cache;
    }

    /**
     * 以软引用的方式对一个Bitmap对象的实例进行引用并保存该引用
     */
    private void addCacheBitmap(Bitmap bmp, String key) {
        MySoftRef ref = new MySoftRef(bmp, q, key);
        hashRefs.put(key, ref);
    }

    /**
     * 依据所指定的drawable下的图片资源ID号（可以根据自己的需要从网络或本地path下获取），重新获取相应Bitmap对象的实例
     */
    public Bitmap getBitmap(String resId, Context context) {
        Bitmap bmp = null;
        MySoftRef ref = hashRefs.get(resId);
        bmp = ref.get();
        return bmp;
    }

    /**
     * 清除Cache内的全部内容
     */
    public void clearCache() {
        cleanCache();
        hashRefs.clear();
        System.gc();
        System.runFinalization();
    }

    private void cleanCache() {
        MySoftRef ref = null;
        while ((ref = (MySoftRef) q.poll()) != null) {
            hashRefs.remove(ref._key);
        }
    }
}