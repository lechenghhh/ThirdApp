package com.lecheng.hello.thirdapp.ActivityListItem;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Aty011HttpAsyncTask_1 extends AppCompatActivity {
    TextView thcget ;
    EditText editText2;
    DefaultHttpClient client ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity011_1);
        editText2 = (EditText) findViewById(R.id.editText2);
        client = new DefaultHttpClient();
        thcget = (TextView) findViewById(R.id.texthttpclientget);

        findViewById(R.id.btnhttpclientget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText2.getText().toString();
                text = text.replace("\n","");text = text.replace(" ","");
                ReadURLhttpclientget("http://fanyi.youdao.com/openapi.do?keyfrom=951827860&key=123087914&type=data&doctype=json&version=1.1&q="+text);
            }
        });
    }

    //get方式
    public void ReadURLhttpclientget (String url){
        new AsyncTask<String,Void,String>(){
            @Override
            protected String doInBackground(String... params) {
                String url2 = params[0];
                HttpGet get = new HttpGet(url2);

                try {

                    HttpResponse r = client.execute(get);
                    String linehcg = EntityUtils.toString(r.getEntity());
                    StringBuilder builder = new StringBuilder();

                    return linehcg;


                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
            @Override
            protected void onPostExecute(String s) {

                thcget.setText(s);
            }
        }.execute(url);
    }

    //post方式




}
