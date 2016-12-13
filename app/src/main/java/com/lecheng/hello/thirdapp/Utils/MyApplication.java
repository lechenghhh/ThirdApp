package com.lecheng.hello.thirdapp.Utils;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Cheng on 2016/3/16.
 */
public class MyApplication extends Application {
    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
//        ImageLoader imageLoader = ImageLoader.getInstance();

        File cacheDir = StorageUtils.getCacheDirectory(this);  //缓存文件夹路径
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions 内存缓存文件的最大长宽
                .threadPoolSize(3) // default  线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2) // default 设置当前线程的优先级
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .memoryCacheSizePercentage(13) // default
                // default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
                .imageDownloader(new BaseImageDownloader(this)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs() // 打印debug log
                .build(); //开始构建
        ImageLoader.getInstance().init(config);
    }

    public static RequestQueue getHttpQueue() {
        return queue;
    }
}
