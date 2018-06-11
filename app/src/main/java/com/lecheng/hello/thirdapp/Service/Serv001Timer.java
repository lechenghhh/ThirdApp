package com.lecheng.hello.thirdapp.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.util.Log;

public class Serv001Timer extends IntentService {
    private static final String TAG = "Serv001Timer";
    protected int totalTime = 0;
    private int timed = 0;
    private boolean isRunning;

    public Serv001Timer() {
        super("Serv001Timer");
        Log.e(TAG, "Serv001Timer");
    }

//    public class MyBinder extends Binder {//通过bind机制 返回改service对象
//
//        public Serv001Timer getService() {
//            return Serv001Timer.this;
//        }
//
//        public void setTotalTime(int totalTime) {
//            Log.e(TAG, "setTotalTime-totalTime=" + totalTime);
//            Serv001Timer.this.totalTime = totalTime;
//        }
//    }
//
//    public int getTimed() {//供activity调用获取数据，和binder配合使用
//        return timed;
//    }

    @Override//intent方式启动
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "onHandleIntent");
        totalTime = intent.getIntExtra("totalTime", 0);
        timed = totalTime;
        try {
            isRunning = true;
            while (isRunning) {
                Log.e(TAG, "1s过去了");
                Thread.sleep(100);
                timed -= 100;
                if (timed <= 0) {
                    isRunning = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "线程结束运行..." + totalTime);
    }

}