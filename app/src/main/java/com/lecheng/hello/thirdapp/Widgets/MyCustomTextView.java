package com.lecheng.hello.thirdapp.Widgets;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

//可拖动的自己的摄像头画面
public class MyCustomTextView extends TextView {
    private int x, y;
    private int r, l, t, b;

    public MyCustomTextView(Context context) {
        this(context, null);
    }

    public MyCustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                AnimatorSet setDown = new AnimatorSet();
                setDown.playTogether(
                        ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.5f),
                        ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.5f),
                        ObjectAnimator.ofFloat(this, "alpha", 1f, 0.5f)
                );
                setDown.start();
                break;
            case MotionEvent.ACTION_MOVE:
                l = (int) (x + getTranslationX() - getWidth() / 2 + getLeft());
                t = (int) (y + getTranslationY() - getHeight() / 2 + getTop());
                r = l + getWidth();
                b = t + getHeight();
                layout(l, t, r, b);
                break;
            case MotionEvent.ACTION_UP:
                AnimatorSet setUp = new AnimatorSet();
                setUp.playTogether(
                        ObjectAnimator.ofFloat(this, "scaleX", 1.5f, 1f),
                        ObjectAnimator.ofFloat(this, "scaleY", 1.5f, 1f),
                        ObjectAnimator.ofFloat(this, "alpha", 0.5f, 1f)
                );
                setUp.start();
                break;
        }
        return true;
    }
}  