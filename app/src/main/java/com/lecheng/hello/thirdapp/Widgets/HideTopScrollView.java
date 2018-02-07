package com.lecheng.hello.thirdapp.Widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;


/**
 * 跳转置顶的ScrollView          16-11-25  lecheng override
 * 原文出处：http://blog.csdn.net/u010782846/article/details/52089925
 */
public class HideTopScrollView extends ScrollView implements View.OnClickListener {
    private View thisView;    //展示置顶的图片按钮,类型为view类
    private int screenHeight = 1200;    //默认高度

    public HideTopScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //1.0//设置滑动到多少出现
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    //2.0//设置滚动置顶按钮以及其点击监听事件
    public void setImgeViewOnClickGoToFirst(View goTopBtn) {
        this.thisView = goTopBtn;
        this.thisView.setOnClickListener(this);
    }

    @Override//3.0//重写滚动改变返回的回调// l oldl 分别代表水平位移// t oldt 代表当前左上角距离Scrollview顶点的距离
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        System.out.println("l" + l + "t" + t + "oldl" + oldl + "oldt" + oldt);
        /*** 滑动距离超过500px,出现置顶按钮,可以做为自定义属性
         * //3.1滑动距离如果用户设置了使用用户的 如果用户没有设置使用默认的*/
        int alpha = 255 - t / 2;
        int alpha2 = 255 - alpha;
        if (alpha < 0) alpha = 0;
        if (alpha2 > 255) alpha2 = 255;
        thisView.setBackgroundColor(Color.argb((int) alpha, 187, 0, 255));//AGB由相关工具获得，或者美工提供
    }

    //4.0//置顶按钮的点击事件监听
    @Override
    public void onClick(View v) {
        this.smoothScrollTo(0, 0);        //滑动到ScrollView的顶点
    }
}