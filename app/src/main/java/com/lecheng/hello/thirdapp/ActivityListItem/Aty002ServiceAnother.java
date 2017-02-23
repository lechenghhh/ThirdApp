package com.lecheng.hello.thirdapp.ActivityListItem;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lecheng.hello.thirdapp.Service.MyService;
import com.lecheng.hello.thirdapp.R;

public class Aty002ServiceAnother extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    private MyService.MyBinder b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity002);

        findViewById(R.id.btn_service_another_bind).setOnClickListener(this);
        findViewById(R.id.btn_service_another_unbind).setOnClickListener(this);
        findViewById(R.id.btn_service_another_send).setOnClickListener(this);
        findViewById(R.id.btn_service_another_intent).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_service_another_bind:
                bindService(new Intent(this,MyService.class),this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_service_another_unbind:
                unbindService(this);
                break;
            case R.id.btn_service_another_send:
                if (b2!=null){
                    b2.setdata2("这是来自另一个activity的信息");
                }
                break;
            case R.id.btn_service_another_intent:
                startActivity(new Intent(Aty002ServiceAnother.this,Aty001Service.class));
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        b2 = (MyService.MyBinder) service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
