package com.lecheng.hello.thirdapp.ActivityList;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyPopWindow;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*http://blog.csdn.net/android_xiaoqi/article/details/7618895*/
public class Aty051PopWindow extends AppCompatActivity {
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.v1)
    View v1;
    private PopupWindow pop = null;
    private View view = null;
    private PopupWindow mPopupWindow;
    private Handler mHandler = new Handler();
    private int detchTime = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty051);
        ButterKnife.bind(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.aty051_pop, null);        // 引入窗口配置文件
        pop = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        pop.setBackgroundDrawable(new BitmapDrawable());//需要设置一下此参数，点击外边可消失
        pop.setOutsideTouchable(true);        //设置点击窗口外边窗口消失
        pop.setFocusable(true);        // 设置此参数获得焦点，否则无法点击

        // 显示popWindow
        showPopWindow(btn2);

        findViewById(R.id.btn0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyToast(Aty051PopWindow.this,"FDFD",1);
                MyPopWindow p2 = new MyPopWindow(Aty051PopWindow.this,view,R.layout.aty051_pop,"haha");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void showPopWindow(final View v) {      //显示popwindow
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.aty051_pop, null);        // 引入窗口配置文件
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());//需要设置一下此参数，点击外边可消失
        mPopupWindow.setOutsideTouchable(true);        //设置点击窗口外边窗口消失
        mPopupWindow.setFocusable(true);
        /*****************以下代码用来循环检测activity是否初始化完毕***************/
        Runnable showPopWindowRunnable = new Runnable() {
            @Override
            public void run() {
                View view = v;
                // 如何根元素的width和height大于0说明activity已经初始化完毕
                if (view != null && view.getWidth() > 0 && view.getHeight() > 0) {
                    int[] location = new int[2];
                    v.getLocationOnScreen(location);
                    mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY,
                            location[0], location[1] - mPopupWindow.getHeight());
                    mHandler.removeCallbacks(this);                   // 停止检测
                } else
                    mHandler.postDelayed(this, detchTime);  // 如果activity没有初始化完毕则等待5毫秒再次检测
            }
        };
        mHandler.post(showPopWindowRunnable);        // 开始检测
        /******************以上代码用来循环检测activity是否初始化完毕*************/
    }

    @OnClick({R.id.btn2, R.id.btn1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn2:
                break;
            case R.id.btn1:
                if (pop.isShowing())
                    pop.dismiss();            // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                else
                    pop.showAsDropDown(view);            // 显示窗口
                break;
        }
    }
}
