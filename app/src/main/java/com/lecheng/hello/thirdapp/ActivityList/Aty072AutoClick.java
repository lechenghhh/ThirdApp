package com.lecheng.hello.thirdapp.ActivityList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
//http://blog.csdn.net/fuchaosz/article/details/51852442
public class Aty072AutoClick extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "[TAG][Aty071Rob]";
    private static final String PACKAGE_NAME = "com.qiyi.video";
    private String apkPath = "/mnt/sdcard/test.apk";
    public static boolean flag = false;//控制只能自己的app才能执行智能安装
    private TextView tvTest;
    private MyInstallReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty072);
        tvTest = (TextView) findViewById(R.id.tv_test);
        findViewById(R.id.btn_install).setOnClickListener(this);
        findViewById(R.id.btn_uninstall).setOnClickListener(this);
        findViewById(R.id.btn_set).setOnClickListener(this);
        findViewById(R.id.btn_smart_install).setOnClickListener(this);
        //注册apk安装监听
        receiver = new MyInstallReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_ADDED");
        filter.addAction("android.intent.action.PACKAGE_REMOVED");
        filter.addDataScheme("package");
        this.registerReceiver(receiver, filter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //静默安装
            case R.id.btn_install:
                installSlient();
                break;
            //静默卸载
            case R.id.btn_uninstall:
                uninstallSlient();
                break;
            //设置无障碍服务
            case R.id.btn_set:
                //跳转到开启无障碍服务的界面
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
                break;
            //智能安装
            case R.id.btn_smart_install:
                //控制只能自己的app才能智能安装
                flag = true;
                smartInstall();
                break;
        }
    }

    //静默安装
    private void installSlient() {
        String cmd = "pm install -r /mnt/sdcard/test.apk";
        Process process = null;
        DataOutputStream os = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        try {
            //静默安装需要root权限
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.write(cmd.getBytes());
            os.writeBytes("\n");
            os.writeBytes("exit\n");
            os.flush();
            //执行命令
            process.waitFor();
            //获取返回结果
            successMsg = new StringBuilder();
            errorMsg = new StringBuilder();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //显示结果
        tvTest.setText("成功消息：" + successMsg.toString() + "\n" + "错误消息: " + errorMsg.toString());
    }

    //静默卸载
    private void uninstallSlient() {
        String cmd = "pm uninstall " + PACKAGE_NAME;
        Process process = null;
        DataOutputStream os = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        try {
            //卸载也需要root权限
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.write(cmd.getBytes());
            os.writeBytes("\n");
            os.writeBytes("exit\n");
            os.flush();
            //执行命令
            process.waitFor();
            //获取返回结果
            successMsg = new StringBuilder();
            errorMsg = new StringBuilder();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //显示结果
        tvTest.setText("成功消息：" + successMsg.toString() + "\n" + "错误消息: " + errorMsg.toString());
    }

    //智能安装
    private void smartInstall() {
        Uri uri = Uri.fromFile(new File(apkPath));
        Intent localIntent = new Intent(Intent.ACTION_VIEW);
        localIntent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(localIntent);
    }

    //监听apk安装
    private class MyInstallReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {     // install
                String packageName = intent.getDataString();
                Log.i(TAG, "安装了 :" + packageName);
                //安装完毕，设置flag，从而使得其余的apk不能自动安装
                flag = false;
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {   // uninstall
                String packageName = intent.getDataString();
                Log.i(TAG, "卸载了 :" + packageName);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }
}