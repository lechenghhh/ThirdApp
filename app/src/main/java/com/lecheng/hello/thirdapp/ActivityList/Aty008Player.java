package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Service.Serv008Media;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty008Player extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty008);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.v1, R.id.v2, R.id.v3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v1:
                stopService(new Intent(Aty008Player.this, Serv008Media.class));
                break;
            case R.id.v2:
                startService(new Intent(Aty008Player.this, Serv008Media.class));
                break;
            case R.id.v3:
                new MyToast(Aty008Player.this, "下一首", 3333);
                break;
        }
    }
}
