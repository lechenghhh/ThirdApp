package com.lecheng.hello.thirdapp.Utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.lecheng.hello.thirdapp.R;


public class AnimationUtil {
    private static final String TAG = AnimationUtil.class.getSimpleName();

    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(100);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(100);
        return mHiddenAction;
    }


/*
* http://www.cnblogs.com/yc-755909659/p/4290114.html
* */

    public static void visibleAlphaAnim(Context c, View v) {
        if (v.getVisibility() != View.VISIBLE) {
            v.startAnimation(AnimationUtils.loadAnimation(c, R.anim.alpha_visible));
            v.setVisibility(View.VISIBLE);
        }
    }

    public static void invisibleAlphaAnim(Context c, View v) {
        if (v.getVisibility() != View.INVISIBLE) {
            v.startAnimation(AnimationUtils.loadAnimation(c, R.anim.alpha_gone));
            v.setVisibility(View.INVISIBLE);
        }
    }
}