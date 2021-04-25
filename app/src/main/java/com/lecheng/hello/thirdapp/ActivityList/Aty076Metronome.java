package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lecheng.hello.thirdapp.Bean.Json.Bean039Weather;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.HttpRequest;

public class Aty076Metronome extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty075_bitmap);
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Aty076Metronome.this, "haha", Toast.LENGTH_SHORT).show();
                HttpRequest.get("http://baidu.com", new HttpRequest.StringResponse() {
                    @Override
                    public void onSuccess(String result) {
//                        Toast.makeText(Aty076Metronome.this, result, Toast.LENGTH_SHORT).show();
                        Bean039Weather weather = JSON.parseObject(result, Bean039Weather.class);
                    }

                    @Override
                    public void onError(int code, Exception e) {
                        Toast.makeText(Aty076Metronome.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
