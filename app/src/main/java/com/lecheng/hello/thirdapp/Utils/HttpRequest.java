package com.lecheng.hello.thirdapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiaoyehai on 2018/5/21 0021.
 * 版权声明：本文为CSDN博主「qq_36699930」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_36699930/article/details/80392658
 * 本程序稍作优化命名等  by LeCheng
 *

    HttpRequest.GET("http://baidu.com", new HttpRequest.StringResponse() {
        @Override
        public void onSuccess(String result) {
            System.out.println("onSuccess:" + result);
        }

        @Override
        public void onError(int code, Exception e) {
            System.out.println("onError:" + e + " code:" + code);
        }
    });

 */
public class HttpRequest {

    public static final String GET = "GET";
    public static final String POST = "POST";

    private static final int MAX_THREAD = 5;
    private static final int REQUEST_TIMEOUT = 5000;
    private static final int RESPONSE_TIMEOUT = 5000;
    private static final boolean ENABLE_CACHE = false;

    //线程池
    private static ExecutorService executor;
    private static Handler mHandler;

    static {
        executor = Executors.newFixedThreadPool(MAX_THREAD);
        mHandler = new Handler();
    }

    /**
     * 执行网络请求操作,返回数据会解析成字符串String
     *
     * @param url 请求的url
     */
    public static String GET(String url, StringResponse callback) {
        return request(GET, url, null, callback);
    }

    public static String GET(String url, BitmapResponse callback) {
        return request(GET, url, null, callback);
    }

    public static String GET(String url, ByteArrayResponse callback) {
        return request(GET, url, null, callback);
    }

    /**
     * 执行网络请求操作,返回数据会解析成字符串String
     *
     * @param url    请求的url
     * @param params 请求的参数
     */
    public static String POST(String url, Map<String, String> params, StringResponse callback) {
        return request(POST, url, params, callback);
    }

    public static String POST(String url, Map<String, String> params, BitmapResponse callback) {
        return request(POST, url, params, callback);
    }

    public static String POST(String url, Map<String, String> params, ByteArrayResponse callback) {
        return request(POST, url, params, callback);
    }

    private static String request(final String method, final String url, final Map<String, String> params, final StringResponse response) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                OutputStream outputStream = null;
                try {
                    URL u = new URL(url);
                    connection = (HttpURLConnection) u.openConnection();
                    // 设置输入可用
                    connection.setDoInput(true);
                    // 设置输出可用
                    connection.setDoOutput(true);
                    // 设置请求方式
                    connection.setRequestMethod(method);
                    // 设置连接超时
                    connection.setConnectTimeout(REQUEST_TIMEOUT);
                    // 设置读取超时
                    connection.setReadTimeout(RESPONSE_TIMEOUT);
                    // 设置缓存不可用
                    connection.setUseCaches(ENABLE_CACHE);
                    //设置header参数
                    //connection.setRequestProperty("","");
                    // 开始连接
                    connection.connect();

                    // 只有当POST请求时才会执行此代码段
                    if (params != null) {
                        // 获取输出流,connection.getOutputStream已经包含了connect方法的调用
                        outputStream = connection.getOutputStream();
                        StringBuilder sb = new StringBuilder();
                        Set<Map.Entry<String, String>> sets = params.entrySet();
                        // 将Hashmap转换为string
                        for (Map.Entry<String, String> entry : sets) {
                            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                        }
                        String param = sb.substring(0, sb.length() - 1);
                        // 使用输出流将string类型的参数写到服务器
                        outputStream.write(param.getBytes());
                        outputStream.flush();
                    }

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String result = inputStream2String(inputStream);
                        if (result != null && response != null) {
                            postSuccessString(response, result);
                        }
                    } else {
                        if (response != null) {
                            postError(response, responseCode, new Exception("请求数据失败：" + responseCode));
                        }
                    }

                } catch (final Exception e) {
                    e.printStackTrace();
                    if (response != null) {
                        postError(response, 0, e);
                    }

                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        return null;
    }

    private static String request(final String method, final String url, final Map<String, String> params, final BitmapResponse response) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                OutputStream outputStream = null;
                InputStream inputStream = null;
                try {
                    URL u = new URL(url);
                    connection = (HttpURLConnection) u.openConnection();
                    // 设置输入可用
                    connection.setDoInput(true);
                    // 设置输出可用
                    connection.setDoOutput(true);
                    // 设置请求方式
                    connection.setRequestMethod(method);
                    // 设置连接超时
                    connection.setConnectTimeout(REQUEST_TIMEOUT);
                    // 设置读取超时
                    connection.setReadTimeout(RESPONSE_TIMEOUT);
                    // 设置缓存不可用
                    connection.setUseCaches(ENABLE_CACHE);
                    //设置header参数
                    //connection.setRequestProperty("","");
                    // 开始连接
                    connection.connect();

                    // 只有当POST请求时才会执行此代码段
                    if (params != null) {
                        // 获取输出流,connection.getOutputStream已经包含了connect方法的调用
                        outputStream = connection.getOutputStream();
                        StringBuilder sb = new StringBuilder();
                        Set<Map.Entry<String, String>> sets = params.entrySet();
                        // 将Hashmap转换为string
                        for (Map.Entry<String, String> entry : sets) {
                            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                        }
                        String param = sb.substring(0, sb.length() - 1);
                        // 使用输出流将string类型的参数写到服务器
                        outputStream.write(param.getBytes());
                        outputStream.flush();
                    }

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        inputStream = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        if (bitmap != null && response != null) {
                            postSuccessBitmap(response, bitmap);
                        }
                    } else {
                        if (response != null) {
                            postError(response, responseCode, new Exception("请求图片失败：" + responseCode));
                        }
                    }

                } catch (final Exception e) {
                    e.printStackTrace();
                    if (response != null) {
                        postError(response, 0, e);
                    }

                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        return null;
    }

    private static String request(final String method, final String url, final Map<String, String> params, final ByteArrayResponse response) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                OutputStream outputStream = null;
                try {
                    URL u = new URL(url);
                    connection = (HttpURLConnection) u.openConnection();
                    // 设置输入可用
                    connection.setDoInput(true);
                    // 设置输出可用
                    connection.setDoOutput(true);
                    // 设置请求方式
                    connection.setRequestMethod(method);
                    // 设置连接超时
                    connection.setConnectTimeout(REQUEST_TIMEOUT);
                    // 设置读取超时
                    connection.setReadTimeout(RESPONSE_TIMEOUT);
                    // 设置缓存不可用
                    connection.setUseCaches(ENABLE_CACHE);
                    //设置header参数
                    //connection.setRequestProperty("","");
                    // 开始连接
                    connection.connect();

                    // 只有当POST请求时才会执行此代码段
                    if (params != null) {
                        // 获取输出流,connection.getOutputStream已经包含了connect方法的调用
                        outputStream = connection.getOutputStream();
                        StringBuilder sb = new StringBuilder();
                        Set<Map.Entry<String, String>> sets = params.entrySet();
                        // 将Hashmap转换为string
                        for (Map.Entry<String, String> entry : sets) {
                            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                        }
                        String param = sb.substring(0, sb.length() - 1);
                        // 使用输出流将string类型的参数写到服务器
                        outputStream.write(param.getBytes());
                        outputStream.flush();
                    }

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        byte[] bytes = inputStream2ByteArray(inputStream);
                        if (bytes != null && response != null) {
                            postSuccessByte(response, bytes);
                        }
                    } else {
                        if (response != null) {
                            postError(response, responseCode, new Exception("请求图片失败：" + responseCode));
                        }
                    }

                } catch (final Exception e) {
                    e.printStackTrace();
                    if (response != null) {
                        postError(response, 0, e);
                    }

                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        return null;
    }

//    /**
//     * 执行网络请求操作,返回数据是对象
//     *
//     * @param method 请求方式(需要传入String类型的参数:"GET","POST")
//     * @param url    请求的url
//     * @param params 请求的参数
//     */
//    private static <T> void request(final String method, final String url, final Map<String, String> params, final Class<T> cls, final ObjectResponse response) {
//
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection connection = null;
//                OutputStream outputStream = null;
//                try {
//                    URL u = new URL(url);
//                    connection = (HttpURLConnection) u.openConnection();
//                    // 设置输入可用
//                    connection.setDoInput(true);
//                    // 设置输出可用
//                    connection.setDoOutput(true);
//                    // 设置请求方式
//                    connection.setRequestMethod(method);
//                    // 设置连接超时
//                    connection.setConnectTimeout(REQUEST_TIMEOUT);
//                    // 设置读取超时
//                    connection.setReadTimeout(RESPONSE_TIMEOUT);
//                    // 设置缓存不可用
//                    connection.setUseCaches(ENABLE_CACHE);
//                    // 开始连接
//                    connection.connect();
//
//                    // 只有当POST请求时才会执行此代码段
//                    if (params != null) {
//                        // 获取输出流,connection.getOutputStream已经包含了connect方法的调用
//                        outputStream = connection.getOutputStream();
//                        StringBuilder sb = new StringBuilder();
//                        Set<Map.Entry<String, String>> sets = params.entrySet();
//                        // 将Hashmap转换为string
//                        for (Map.Entry<String, String> entry : sets) {
//                            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//                        }
//                        String param = sb.substring(0, sb.length() - 1);
//                        // 使用输出流将string类型的参数写到服务器
//                        outputStream.write(param.getBytes());
//                        outputStream.flush();
//                    }
//
//                    int responseCode = connection.getResponseCode();
//                    if (responseCode == 200) {
//                        InputStream inputStream = connection.getInputStream();
//                        String result = inputStream2String(inputStream);
//                        if (result != null && response != null) {
////                            postSuccessObject(response, new Gson().fromJson(result, cls));
//                            postSuccessObject(response, result);
//                        }
//                    } else {
//                        if (response != null) {
//                            postError(response, responseCode, new Exception("请求数据失败：" + responseCode));
//                        }
//                    }
//
//                } catch (final Exception e) {
//                    e.printStackTrace();
//                    if (response != null) {
//                        postError(response, 0, e);
//                    }
//
//                } finally {
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                    if (outputStream != null) {
//                        try {
//                            outputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//    }

    private static void postSuccessString(final StringResponse callback, final String result) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    callback.onSuccess(result);
                } catch (Exception ex) {
                    callback.onError(0, ex);
                }
            }
        });
    }

    private static void postSuccessBitmap(final Response response, final Bitmap bitmap) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                BitmapResponse bitmapResponse = (BitmapResponse) response;
                bitmapResponse.onSuccess(bitmap);
            }
        });
    }

    private static void postSuccessByte(final Response response, final byte[] bytes) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ByteArrayResponse byteArrayResponse = (ByteArrayResponse) response;
                byteArrayResponse.onSuccess(bytes);
            }
        });
    }

    private static <T> void postSuccessObject(final ObjectResponse callback, final T t) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ObjectResponse objectResponse = (ObjectResponse) callback;
                objectResponse.onSuccess(t);
            }
        });
    }

    private static void postError(final Response response, final int code, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                response.onError(code, e);
            }
        });
    }

    /**
     * 字节流转换成字符串
     *
     * @param inputStream
     * @return
     */
    private static String inputStream2String(InputStream inputStream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            return new String(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 字节流转换成字节数组
     *
     * @param inputStream 输入流
     * @return
     */
    private static byte[] inputStream2ByteArray(InputStream inputStream) {
        byte[] result = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 缓冲区
        byte[] bytes = new byte[1024];
        int len = -1;
        try {
            // 使用字节数据输出流来保存数据
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            result = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 判断是否联网
     *
     * @param context
     * @return
     */
    public static boolean ISConnecting(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    public interface Response {
        void onError(int code, Exception e);
    }

    public interface StringResponse extends Response {
        void onSuccess(String result);
    }

    public interface BitmapResponse extends Response {
        void onSuccess(Bitmap bitmap);
    }

    public interface ByteArrayResponse extends Response {
        void onSuccess(byte[] bytes);
    }

    public interface ObjectResponse<T> extends Response {
        void onSuccess(T t);
    }

}

