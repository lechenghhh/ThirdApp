package com.lecheng.hello.thirdapp.Utils.Interface.HttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 以HttpClient访问网络AsyncTask,访问网络在子线程进行并返回主线程通知访问的结果
 * https://blog.csdn.net/xuehuayous/article/details/54143487
 */
public class HttpClientNetworkTask extends NetworkTask {

    public HttpClientNetworkTask(String method) {
        super(method);
    }

    @Override
    public String doGet(String url) {
        String result = null;
        HttpClient httpCient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        //httpGet.addHeader("key","value");
        try {
            HttpResponse httpResponse = httpCient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
            } else {
                isSuccess = false;
                result = "网络响应状态码不为200!";
            }
        } catch (IOException e) {
            isSuccess = false;
            result = "网络访问错误:" + e.getMessage();
        }
        return result;
    }

    @Override
    public String doPost(String url, Map<String, String> paramMap) {
        String result;
        HttpClient httpCient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        //httpPost.addHeader("key", "value");
        // 使用NameValuePair来保存要传递的Post参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse response = httpCient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                isSuccess = false;
                result = "网络响应状态码不为200!";
            }
        } catch (IOException e) {
            isSuccess = false;
            result = "网络访问错误:" + e.getMessage();
        }

        return result;
    }

     /*** 调用示例:

         NetworkTask networkTask = new HttpClientNetworkTask(NetworkTask.GET);
         networkTask.execute("http://baidu.com");
         networkTask.setResponceLintener(new NetworkTask.ResponceLintener() {
            @Override public void onSuccess(String result) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
            @Override public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
         });

     ————————————————
     版权声明：本文为CSDN博主「周文凯」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/xuehuayous/article/details/54143487
     ***/
}