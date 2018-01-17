package com.lecheng.hello.thirdapp.Service;

import java.util.Calendar;
import java.util.List;

import android.R.integer;
import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class Serv071RobMoney extends AccessibilityService {

    private static final String TAG = "Aty071Rob";
    private Calendar c = Calendar.getInstance();
    int flag = 0;

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Log.i(TAG, "onKeyEvent");
        int key = event.getKeyCode();
        switch (key) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:

                Intent downintent = new Intent("com.exmaple.broadcaster.KEYDOWN");
                downintent.putExtra("dtime", System.currentTimeMillis());
                if (flag == 0) {
                    sendBroadcast(downintent);
                } else if (flag == 1) {
                    flag = 0;
                }
                Log.i(TAG, "KEYCODE_VOLUME_DOWN");
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                Intent upintent = new Intent("com.exmaple.broadcaster.KEYUP");
                upintent.putExtra("utime", System.currentTimeMillis());
                if (flag == 0) {
                    sendBroadcast(upintent);
                    flag += 1;
                } else if (flag == 1) {
                    flag = 0;
                }
                Log.i(TAG, "KEYCODE_VOLUME_UP");
                break;
            default:
                break;
        }
        return super.onKeyEvent(event);
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Serv071RobMoney::onCreate");
        super.onCreate();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

}
