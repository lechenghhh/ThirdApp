package com.lecheng.hello.thirdapp.Service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class Serv001Timer extends IntentService {
    private static final String TAG = "Serv001Timer";

    private boolean isRunning;

    private int count;

    public Serv001Timer() {
        super("Serv001Timer");
        Log.e(TAG, "Serv001Timer");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "onHandleIntent");
        int timed = intent.getIntExtra("Timed", 0);
        try {
//            Thread.sleep(1000);
            isRunning = true;
//            count = 0;
            while (isRunning) {
                count++;
//                if (count >= 100) {
//                    isRunning = false;
//                }
                Thread.sleep(1000);
//                sendThreadStatus("线程运行中...", count);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "线程结束运行..." + count);
    }
}