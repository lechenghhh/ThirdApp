package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.Service.Ser001;
import com.lecheng.hello.thirdapp.R;


public class Aty001Service extends Activity implements View.OnClickListener, ServiceConnection {
    private EditText ed1 ,ed2;
    private TextView tv1;
    private Intent i;
    private Ser001.MyBinder b ;
    private static String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity001);

        Toast.makeText(this,"欢迎来到服务的学习实验",Toast.LENGTH_LONG).show();

        i = new Intent(Aty001Service.this,Ser001.class);

        ed1 = (EditText) findViewById(R.id.et1);
        ed2 = (EditText) findViewById(R.id.et2);
        tv1 = (TextView) findViewById(R.id.tv1);
        findViewById(R.id.btnstartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("haha",ed1.getText().toString());
                startService(i);
            }
        });

        findViewById(R.id.btnstopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(i);
            }
        });

        findViewById(R.id.btnbindService).setOnClickListener(this);
        findViewById(R.id.btnunbindService).setOnClickListener(this);
        findViewById(R.id.btnsync).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnbindService:
                bindService(new Intent(this,Ser001.class),this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnunbindService:
                unbindService(this);
                break;
            case R.id.btnsync:
                if (b!=null){
                    b.setdata1(ed2.getText().toString());
                }
                tv1.setText(a);
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Toast.makeText(this, "服务绑定成功", Toast.LENGTH_SHORT).show();
        b = (Ser001.MyBinder) iBinder;
        b.getService().setCb(new Ser001.Callback() {
            @Override
            public void onDataChange(String Data) {                 //调用接口2下的回调方法，
                a=Data;
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Toast.makeText(this, "服务解除绑定", Toast.LENGTH_SHORT).show();
    }
}
