package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.lecheng.hello.thirdapp.R;

/**
 * Android实现左右滑动效果
 *
 * @Description: Android实现左右滑动效果
 * @File: MainAty.java
 * @Package com.android.flip
 * @Author Hanyonglu
 * @Date 2012-02-12 上午10:44:04
 * @Version V1.0
 */
public class Aty027ViewFlipper extends Activity implements OnGestureListener {
    private ViewFlipper flipper;
    private GestureDetector detector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity027);

        detector = new GestureDetector(this);
        flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper1);

        flipper.addView(addImageView(R.color.colorRed1));
        flipper.addView(addImageView(R.color.colorOrange1));
        flipper.addView(addImageView(R.color.colorAccent));
        flipper.addView(addImageView(R.color.colorGreen2));
        flipper.addView(addImageView(R.color.colorBlue3));
    }

    private View addImageView(int id) {
        ImageView iv = new ImageView(this);
        iv.setImageResource(id);
        return iv;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.detector.onTouchEvent(event);
    }

    //拦截？
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        if (e1.getX() - e2.getX() > 120) {
            this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
            this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
            this.flipper.showNext();
            return true;
        } else if (e1.getX() - e2.getX() < -120) {
            this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
            this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
            this.flipper.showPrevious();
            return true;
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }
}