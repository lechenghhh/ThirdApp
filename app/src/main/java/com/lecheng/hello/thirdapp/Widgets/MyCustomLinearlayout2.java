package com.lecheng.hello.thirdapp.Widgets;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

//可拖动的自己的摄像头画面
public class MyCustomLinearlayout2 extends FrameLayout {
    private int x, y;
    private int r, l, t, b;
//    private OnFragmentChangeListener callBackUpdate;

    public MyCustomLinearlayout2(Context context) {
        this(context, null);
        initView();
    }

    private void initView() {
//        callBackUpdate = (OnFragmentChangeListener) getContext();
    }

    public MyCustomLinearlayout2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView();
    }

    public MyCustomLinearlayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private int eventState = 0;
    private int mX1, mX2;
    private int mY1, mY2;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void setText(String str) {
//        tv.setText(str);
    }

    MotionEvent downEvent = null;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downEvent = MotionEvent.obtain(event);

                eventState = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                l = (int) (x + getTranslationX() - getWidth() / 2 + getLeft());
                t = (int) (y + getTranslationY() - getHeight() / 2 + getTop());
                r = l + getWidth();
                b = t + getHeight();
                layout(l, t, r, b);
//                System.out.println("l=" + l + "t=" + t + "r=" + r + "b=" + b);

                if (eventState == 0) {
                    mX1 = l;
                    mY1 = t;
                    eventState = 1;
                }
                mX2 = l;
                mY2 = t;
                break;
            case MotionEvent.ACTION_UP:
                eventState = 0;
//                System.out.println("l============" + Math.abs(mX1 - mX2) + "--" + Math.abs(mY1 - mY2));
                if (Math.abs(mX1 - mX2) < 20 && Math.abs(mY1 - mY2) < 20) {
                    if (downEvent != null) {
                        super.dispatchTouchEvent(downEvent);
                        super.dispatchTouchEvent(event);
                    }
                }
                downEvent = null;
                mY2 = mX2 = mY1 = mX1 = 0;
                break;
        }
        return true;
    }

    private void startAnmi(boolean isStart) {
        if (isStart) {
            AnimatorSet setDown = new AnimatorSet();
            setDown.playTogether(//动画
                    ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.5f),
                    ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.5f),
                    ObjectAnimator.ofFloat(this, "alpha", 1f, 0.5f)
            );
            setDown.start();
        } else {
            AnimatorSet setUp = new AnimatorSet();
            setUp.playTogether(//动画
                    ObjectAnimator.ofFloat(this, "scaleX", 1.5f, 1f),
                    ObjectAnimator.ofFloat(this, "scaleY", 1.5f, 1f),
                    ObjectAnimator.ofFloat(this, "alpha", 0.5f, 1f)
            );
            setUp.start();
        }
    }
}