package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.R;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnClick;

//音量键后台监听：http://blog.csdn.net/qq_16564849/article/details/70280755
//root后模拟按钮：http://blog.csdn.net/jzj1993/article/details/39158865
public class Aty071VolueEvent extends Activity {

    private static final String TAG = "Aty071VolueEvent";
    private KeycodeBroadReceiver keycodebroadreceiver;
    private Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty071);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate");
        keycodebroadreceiver = new KeycodeBroadReceiver();
    }

    @OnClick({R.id.btnAccessibility, R.id.btnRegister, R.id.btnUnRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAccessibility:
                try {
                    startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                    Toast.makeText(this, "找到按键监听服务，开启即可", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnRegister:
                IntentFilter intentfilter = new IntentFilter();
                intentfilter.addAction("com.exmaple.broadcaster.KEYDOWN");
                intentfilter.addAction("com.exmaple.broadcaster.KEYUP");
                registerReceiver(keycodebroadreceiver, intentfilter);
                break;
            case R.id.btnUnRegister:
                unregisterReceiver(keycodebroadreceiver);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int key = event.getKeyCode();
        switch (key) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Time t1 = new Time();
                t1.setToNow();
                c.set(t1.year, t1.month, t1.monthDay, t1.hour, (int) t1.minute + 1, t1.second);
                AlarmManager am2 = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                am2.setTime((c.getTimeInMillis()));
                Intent downintent = new Intent("com.exmaple.broadcaster.KEYDOWN");
                downintent.putExtra("dtime", System.currentTimeMillis());
                sendBroadcast(downintent);
                Log.i(TAG, "KEYCODE_VOLUME_DOWN");
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                Intent upintent = new Intent("com.exmaple.broadcaster.KEYUP");
                upintent.putExtra("utime", System.currentTimeMillis());
                sendBroadcast(upintent);
                Log.i(TAG, "KEYCODE_VOLUME_UP");
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }


    private Time t3 = new Time();
    private Time t4 = new Time();

    private class KeycodeBroadReceiver extends BroadcastReceiver {
        private static final String TAG = "ONRECEIVE";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == "com.exmaple.broadcaster.KEYUP") {
                t3.setToNow();
                int year = t3.year;
                int month = t3.month;
                int date = t3.monthDay;
                int hour = t3.hour;
                int minute = t3.minute + 1;
                int second = t3.second;
                c.set(year, month, date, hour, minute, second);
                AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                am.setTime((c.getTimeInMillis()));
                Log.i(TAG, "KeycodeBroadReceiver::onReceive");
            } else if (intent.getAction() == "com.exmaple.broadcaster.KEYDOWN") {
                t4.setToNow();
                int year1 = t4.year;
                int month1 = t4.month;
                int date1 = t4.monthDay;
                int hour1 = t4.hour;
                int minute1 = t4.minute - 1;
                int second1 = t4.second;
                c.set(year1, month1, date1, hour1, (int) minute1, second1);
                AlarmManager am2 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                am2.setTime((c.getTimeInMillis()));
                Log.i(TAG, "KeycodeBroadReceiver::onReceive");
            }
        }
    }
}
