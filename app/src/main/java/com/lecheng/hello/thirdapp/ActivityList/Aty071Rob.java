package com.lecheng.hello.thirdapp.ActivityList;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.R;

//http://blog.csdn.net/qq_16564849/article/details/70280755
public class Aty071Rob extends Activity {

    private static final String TAG = "Aty071Rob";
    private KeycodeBroadReceiver keycodebroadreceiver;
    private Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty071);
        Log.i(TAG, "Aty071Rob::onCreate");

        registerBroader();

        findViewById(R.id.btn_start).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openServiceSetting();
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int key = event.getKeyCode();

        switch (key) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Time t1 = new Time();
                t1.setToNow();
                int year1 = t1.year;
                int month1 = t1.month;
                int date1 = t1.monthDay;
                int hour1 = t1.hour;
                int minute1 = t1.minute + 1;
                int second1 = t1.second;
                c.set(year1, month1, date1, hour1, (int) minute1, second1);
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

    private void registerBroader() {
        Log.i(TAG, "Aty071Rob::registerBroader");
        keycodebroadreceiver = new KeycodeBroadReceiver();
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("com.exmaple.broadcaster.KEYDOWN");
        intentfilter.addAction("com.exmaple.broadcaster.KEYUP");
        registerReceiver(keycodebroadreceiver, intentfilter);
    }

    private void openServiceSetting() {
        try {
            Intent intent = new Intent(
                    android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "找到按键监听服务，开启即可", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Time t = new Time();
    private Time t1 = new Time();

    private class KeycodeBroadReceiver extends BroadcastReceiver {
        private static final String TAG = "ONRECEIVE";

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() == "com.exmaple.broadcaster.KEYUP") {
                t.setToNow();
                int year = t.year;
                int month = t.month;
                int date = t.monthDay;
                int hour = t.hour;
                int minute = t.minute + 1;
                int second = t.second;
                c.set(year, month, date, hour, minute, second);

                AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                am.setTime((c.getTimeInMillis()));

                Log.i(TAG, "KeycodeBroadReceiver::onReceive");

            } else if (intent.getAction() == "com.exmaple.broadcaster.KEYDOWN") {

                t1.setToNow();
                int year1 = t1.year;
                int month1 = t1.month;
                int date1 = t1.monthDay;
                int hour1 = t1.hour;
                int minute1 = t1.minute - 1;
                int second1 = t1.second;
                c.set(year1, month1, date1, hour1, (int) minute1, second1);
                AlarmManager am2 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                am2.setTime((c.getTimeInMillis()));
                Log.i(TAG, "KeycodeBroadReceiver::onReceive");
            }

        }

    }

}
