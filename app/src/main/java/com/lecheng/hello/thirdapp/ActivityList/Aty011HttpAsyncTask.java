package com.lecheng.hello.thirdapp.ActivityList;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Aty011HttpAsyncTask extends AppCompatActivity {
    private TextView textasync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity011);

        findViewById(R.id.btnstarthttpclientget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Aty011HttpAsyncTask.this, Aty011HttpAsyncTask_1.class));
            }
        });

        textasync = (TextView) findViewById(R.id.textasync);
        findViewById(R.id.btnasync).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ReadURL("http://www.baidu.com");
            }
        });
        findViewById(R.id.btnchatrobot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Aty011HttpAsyncTask.this, Aty011HttpAsyncTask_2.class));

            }
        });
    }

    private void ReadURL(String url) {                         //以下采用Http的get方式获取网络数据
        new AsyncTask<String, Float, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    URLConnection c = url.openConnection();

                    InputStream is = c.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);

                    String line;
                    StringBuffer b = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        b.append(line);
                    }
                    br.close();
                    is.close();
                    isr.close();
                    return b.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                textasync.setText(s);
                super.onPostExecute(s);
            }
        }.execute(url);

    }
}
