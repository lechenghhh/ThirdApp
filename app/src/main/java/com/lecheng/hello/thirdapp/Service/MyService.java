package com.lecheng.hello.thirdapp.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    public String data1 = "默认信息1";
    private boolean serviceRunning=false;
    private String data2 = "默认信息2";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {      //当其他组件绑定这个服务时，返回给它 自定义MyBinder
        return new MyBinder();
    }
    public class MyBinder extends Binder{       //自定义MyBinder 增加了功能：把外面传进值传给自身
        public void setdata1(String data){
            MyService.this.data1 = data;
        }
        public void setdata2(String data){
            MyService.this.data2 = data;
        }
        public MyService getService(){          //使外界获取到MyService类
            return MyService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        data1 = intent.getStringExtra("haha");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceRunning = true;
        new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (serviceRunning){
                    i++;
                    String str1 = "服务正在运行。。。显示Aty1内容: "+data1+" 数字: "+i;
                    String str2 = "服务正在运行。。。显示Aty2内容: "+data2+" 数字: "+i;
                    System.out.println(str1);
                    System.out.println(str2);
                    if (cb!=null){
                        cb.onDataChange(str2);             //把接口2内容改变
                    }
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("服务已经停止了。。。");
        serviceRunning = false;
    }




    //回调函数

    private Callback cb = null;

    public void setCb(Callback cb) {
        this.cb = cb;
    }

    public Callback getCb() {
        return cb;
    }

    public static interface Callback{                          //接口2
        void onDataChange(String Data);                 //设置抽象方法但不实现
    }

}