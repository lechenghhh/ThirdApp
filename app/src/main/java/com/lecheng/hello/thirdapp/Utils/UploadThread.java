package com.lecheng.hello.thirdapp.Utils;

import android.content.Context;
import android.content.Entity;
import android.os.Environment;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/14.
 */
public class UploadThread extends Thread {
    private String fileName, url;
    private Context c;

    public UploadThread(Context c, String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
        this.c = c;
    }

    @Override
    public void run() {
        uploadFile();
    }

    private void uploadFile() {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        MultipartEntity multi = new MultipartEntity();

        File fileAbs = new File(Environment.getExternalStorageDirectory(), "bc.jpg");
//        File fileAbs = new File(Environment.getExternalStorageDirectory(), "bc.jpg");
        FileBody fileBody = new FileBody(fileAbs);

        multi.addPart("file", fileBody);
        post.setEntity(multi);

        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println(EntityUtils.toString(response.getEntity()));
                Toast.makeText(c, "上传成功：" + EntityUtils.toString(response.getEntity()), Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //原生上传文件
    private void yuanshengshangchuan() {
//        String boundary = "-----------------------------7de2c25201d48";
//        String prefix = "--";
//        String end = "\r\n";
//
//        try {
//            URL httpUrl = new URL(url);
//            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setRequestProperty("Content-Type",
//                    "multipart/form-data;boundary=" + boundary);
//            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
//            out.writeBytes(prefix + boundary + end);
//            out.writeBytes("Content-Disposition: form-data;"
//                    + "name=\"file\";filename=" + "bc.jpg" + "\"" + end);
//            out.writeBytes(end);
//            FileInputStream fileInputStream =
//                    new FileInputStream(new File(fileName));
//            byte[] b = new byte[1024 * 4];
//            int len;
//            while ((len = fileInputStream.read(b)) != -1) {
//                out.write(b, 0, len);
//            }
//            out.writeBytes(end);
//            out.writeBytes(prefix + boundary + prefix + end);
//            out.flush();
//
//            BufferedReader reader =
//                    new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuffer sb = new StringBuffer();
//            String str;
//            while ((str = reader.readLine()) != null) {
//                sb.append(str);
//            }
//
//            System.out.println("respose" + sb.toString());
//            if (out != null) {
//                out.close();
//            }
//            if (reader != null) {
//                reader.close();
//            }
//            System.out.println("提交成功");
//            Toast.makeText(c, "提交成功", Toast.LENGTH_LONG).show();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            System.out.println("MalformedURLException:" + e);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("IOException:" + e);
//        }
    }
}
