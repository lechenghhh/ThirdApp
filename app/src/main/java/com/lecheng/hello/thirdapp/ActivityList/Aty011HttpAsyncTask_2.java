package com.lecheng.hello.thirdapp.ActivityList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Aty011HttpAsyncTask_2 extends AppCompatActivity {
    private EditText et;
    private TextView tv;
    private StringBuilder builder = new StringBuilder();
    private DefaultHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity011_2);
        client = new DefaultHttpClient();
        et = (EditText) findViewById(R.id.edtext);
        tv = (TextView) findViewById(R.id.tvtext);

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.insert(builder.length(), "                                     " +
                        "我说：" + et.getText().toString() + "\n");
                tv.setText(builder);
                String chat = et.getText().toString();
                chat = chat.replace(" ", "");
                chat = chat.replace("\n", "");
                et.setText("");
                ReadChat1("http://www.tuling123.com/openapi/api?key=b3009ca88821f37ebe3e10afaa193398&info=" + chat);
            }
        });
    }

    public void ReadChat1(String url) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String url = params[0];
                HttpGet get = new HttpGet(url);
                try {
                    HttpResponse r = client.execute(get);
                    String line = EntityUtils.toString(r.getEntity());

                    return analyze(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                builder.insert(builder.length(), "老王说：" + s + "\n");
                tv.setText(builder.toString());

            }
        }.execute(url);
    }

    public String analyze(String str) {
        try {

            JSONObject jb = new JSONObject(str);
            return jb.getString("text");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
