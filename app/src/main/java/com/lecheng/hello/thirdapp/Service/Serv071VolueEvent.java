package com.lecheng.hello.thirdapp.Service;

import java.util.Calendar;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.ActivityList.Aty071VolueEvent;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.RootShellCmd;

//你真的理解AccessibilityService吗:https://www.jianshu.com/p/4cd8c109cdfb
public class Serv071VolueEvent extends AccessibilityService {

    private static final String TAG = "Aty071VolueEvent";
    private LinearLayout toucherLayout;    //要引用的布局文件.
    private WindowManager.LayoutParams params;    //布局参数.
    private WindowManager windowManager;    //实例化的WindowManager.
    private ImageButton ivBtn;
    private int statusBarHeight = -1;    //状态栏高度.（接下来会用到）
    private RootShellCmd rsc = new RootShellCmd();//root发送shell指令
    private boolean flag = true;
    private int simulateTapX = 1, simulateTapY = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        createToucher();        //OnCreate中来生成悬浮窗.
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {//        Log.i(TAG, "onKeyEvent=" + event);
        if (flag) switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                rsc.simulateKey(4);//Toast.makeText(Serv071VolueEvent.this, "音量-被按下", Toast.LENGTH_SHORT).show();
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                rsc.simulateTap(simulateTapX, simulateTapY);
                break;
            default:
                return false;
        }
        flag = !flag;
        return true;//拦截音量键的事件//return super.onKeyEvent(event);//返回音量键的事件
    }

    @Override
    public void onInterrupt() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
//        Log.i(TAG, "onAccessibilityEvent-" + event);
    }

    //创建悬浮窗以保证toast可以被显示
    private void createToucher() {
        if (toucherLayout == null) {


            LayoutInflater inflater = LayoutInflater.from(getApplication());
            //获取浮动窗口视图所在布局.
            toucherLayout = (LinearLayout) inflater.inflate(R.layout.unit_suspension, null);
            //浮动窗口按钮.
            ivBtn = (ImageButton) toucherLayout.findViewById(R.id.imageButton1);
            ivBtn.setBackgroundResource(R.drawable.ic_this);

            //赋值WindowManager&LayoutParam.
            params = new WindowManager.LayoutParams();
            windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
            //设置type.系统提示型窗口，一般都在应用程序窗口之上.
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            //设置效果为背景透明.
            params.format = PixelFormat.RGBA_8888;
            //设置flags.不可聚焦及不可使用按钮对悬浮窗进行操控.
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

            //设置窗口初始停靠位置.
            params.gravity = Gravity.LEFT | Gravity.TOP;
            params.x = 0;
            params.y = 0;

            //设置悬浮窗口长宽数据.
            //注意，这里的width和height均使用px而非dp.这里我偷了个懒
            //如果你想完全对应布局设置，需要先获取到机器的dpi
            //px与dp的换算为px = dp * (dpi / 160).
            params.width = 96;
            params.height = 96;
            //添加toucherlayout
            windowManager.addView(toucherLayout, params);

            Log.i(TAG, "toucherlayout-->left:" + toucherLayout.getLeft());
            Log.i(TAG, "toucherlayout-->right:" + toucherLayout.getRight());
            Log.i(TAG, "toucherlayout-->top:" + toucherLayout.getTop());
            Log.i(TAG, "toucherlayout-->bottom:" + toucherLayout.getBottom());

            //主动计算出当前View的宽高信息.
            toucherLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

            //用于检测状态栏高度.
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
            Log.i(TAG, "状态栏高度为:" + statusBarHeight);

            //其他代码...

            ivBtn.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int viewWidth = v.getMeasuredWidth();
                    int viewHeight = v.getMeasuredHeight();
                    //ImageButton我放在了布局中心，布局一共300dp
                    params.x = (int) event.getRawX() - viewWidth;
                    //这就是状态栏偏移量用的地方
                    params.y = (int) event.getRawY() - viewHeight;
                    System.out.println("Event-x=" + event.getRawX() + " y=" + event.getRawY());
                    simulateTapX = (int) params.x - 1;
                    simulateTapY = (int) event.getRawY() - viewWidth / 2 - 1;
                    windowManager.updateViewLayout(toucherLayout, params);
                    return false;
                }
            });
//            ivBtn.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    startActivity(new Intent(Serv071VolueEvent.this, Aty071VolueEvent.class)
//                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));//设置这个flags
//                    return true;
//                }
//            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (windowManager != null) windowManager.removeViewImmediate(toucherLayout);
    }
}
