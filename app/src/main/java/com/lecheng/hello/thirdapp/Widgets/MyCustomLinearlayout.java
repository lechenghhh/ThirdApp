package com.lecheng.hello.thirdapp.Widgets;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/*可拖动父容器控件
 * 该容器并不拦截事件，当拖动时，子控件的onclick会把event事件传回给该容器，因此不会造成滑动冲突*/
public class MyCustomLinearlayout extends LinearLayout {
    private float moveX, moveY;

    public MyCustomLinearlayout(Context context) {
        this(context, null);
        initView();
    }

    public MyCustomLinearlayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView();
    }

    public MyCustomLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = event.getX();
                moveY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                setTranslationX(getX() + (event.getX() - moveX));
                setTranslationY(getY() + (event.getY() - moveY));
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}