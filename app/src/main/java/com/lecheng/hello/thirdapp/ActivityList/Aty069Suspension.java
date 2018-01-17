package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Service.Serv069Suspension;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import butterknife.ButterKnife;
import butterknife.OnClick;

//https://www.jianshu.com/p/ac63c57d2555
public class Aty069Suspension extends Activity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty069_suspension);
        ButterKnife.bind(this);
        intent = new Intent(this, Serv069Suspension.class);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            return true;
        } else return super.onKeyUp(keyCode, event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            new MyToast(this, "KEY DOWN", 1);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            new MyToast(this, "KEY UP", 1);
            return true;
        } else return super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.btnStart, R.id.btnStop, R.id.btnPremission})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
//                if (Build.VERSION.SDK_INT >= 23) {
//                    if (Settings.canDrawOverlays(this)) {
//                        Intent intent = new Intent(this, Serv069Suspension.class);
//                        Toast.makeText(this, "已开启Toucher", Toast.LENGTH_SHORT).show();
//                        startService(intent);
//                        finish();
//                    } else {
//                        //若没有权限，提示获取.
//                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                        Toast.makeText(this, "需要取得权限以使用悬浮窗", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
//                    }
//                } else {
                //SDK在23以下，不用管.
                startService(intent);
                finish();
//                }
                break;
            case R.id.btnStop:
                stopService(intent);
                break;
            case R.id.btnPremission:
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT >= 9) {
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    intent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
                }
                startActivity(intent);
                break;
        }
    }
}
