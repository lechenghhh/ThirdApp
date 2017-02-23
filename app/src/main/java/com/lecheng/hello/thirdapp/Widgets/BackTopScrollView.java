package com.lecheng.hello.thirdapp.Widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.lecheng.hello.thirdapp.Utils.AnimationUtil;


/**
 * 跳转置顶的ScrollView          16-11-25  lecheng override
 * 原文出处：http://blog.csdn.net/u010782846/article/details/52089925
 */
public class BackTopScrollView extends ScrollView implements View.OnClickListener {
    private View goTopBtn;    //展示置顶的图片按钮,类型为view类
    private int screenHeight = 1200;    //默认高度

    public BackTopScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //1.0//设置滑动到多少出现
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    //2.0//设置滚动置顶按钮以及其点击监听事件
    public void setImgeViewOnClickGoToFirst(View goTopBtn) {
        this.goTopBtn = goTopBtn;
        this.goTopBtn.setOnClickListener(this);
    }

    @Override//3.0//重写滚动改变返回的回调// l oldl 分别代表水平位移// t oldt 代表当前左上角距离Scrollview顶点的距离
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        /*** 滑动距离超过500px,出现置顶按钮,可以做为自定义属性
         * //3.1滑动距离如果用户设置了使用用户的 如果用户没有设置使用默认的*/
        if ((t > screenHeight) && (oldt > t)) {
            if (goTopBtn.getVisibility() == View.INVISIBLE) {
                goTopBtn.setAnimation(AnimationUtil.moveToViewLocation());
                goTopBtn.setVisibility(VISIBLE);
            }
        } else {
            if (goTopBtn.getVisibility() == View.VISIBLE) {
                goTopBtn.setAnimation(AnimationUtil.moveToViewBottom());
                goTopBtn.setVisibility(INVISIBLE);
            }
        }
    }

    //4.0//置顶按钮的点击事件监听
    @Override
    public void onClick(View v) {
        this.smoothScrollTo(0, 0);        //滑动到ScrollView的顶点
    }
}