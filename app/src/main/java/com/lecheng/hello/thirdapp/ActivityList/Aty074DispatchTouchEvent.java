package com.lecheng.hello.thirdapp.ActivityList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Aty074DispatchTouchEvent extends ActionBarActivity {

    @Bind(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty074);
        ButterKnife.bind(this);

        findViewById(R.id.btn1).setOnClickListener(view -> new MyToast(this, "btn1", 1));
        findViewById(R.id.btn2).setOnClickListener(view -> new MyToast(this, "btn2", 1));
        findViewById(R.id.btn3).setOnClickListener(view -> new MyToast(this, "btn3", 1));
        findViewById(R.id.btn4).setOnClickListener(view -> new MyToast(this, "btn4", 1));
        tvTitle.setText("屏幕宽度:" + getScreenWidth() + "\n屏幕高度:" + getScreenHeigth());
    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager)getApplicationContext(). getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private int getScreenHeigth() {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
