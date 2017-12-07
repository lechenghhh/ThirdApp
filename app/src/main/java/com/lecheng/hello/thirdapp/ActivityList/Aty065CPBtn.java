package com.lecheng.hello.thirdapp.ActivityList;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;
import com.lecheng.hello.thirdapp.Widgets.CircularProgressButton.CircularProgressButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
//项目地址:https://github.com/dmytrodanylyk/circular-progress-button
//也可以gradle引用: compile 'com.github.dmytrodanylyk.circular-progress-button:library:1.1.3'
public class Aty065CPBtn extends Activity {

    @Bind(R.id.cpb1)
    CircularProgressButton cpb1;
    public static final int CPB_INIT = 0;
    public static final int CPB_SUCCESS = 100;
    public static final int CPB_LOADING = 50;
    public static final int CPB_FAILED = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty065_cpbtn);
        ButterKnife.bind(this);

        cpb1.setIndeterminateProgressMode(true); // cpb进入不精准进度模式

//        CircularProgressButton circularButton02 = (CircularProgressButton) findViewById(R.id.circularButton02);
//        circularButton02.setIndeterminateProgressMode(true);
//        circularButton02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CircularProgressButton btn = (CircularProgressButton) v;
//                int progress = btn.getProgress();
//                System.out.println("progress = " + progress);
//                if (progress == 0) { // 初始时progress = 0
//                    btn.setProgress(50); // 点击后开始不精准旋转，进度为50
//                } else if (progress == -1) { // 如果当前进度为-1，即出错状态，那么重新回到初始状态
//                    btn.setProgress(0);
//                } else if (progress == 50) { // 如果当前进度为50，那么点击后就显示出错的状态
//                    btn.setProgress(-1); // -1表示出错，显示出错的图片和背景
//                }
//            }
//        });
//
//        final CircularProgressButton circularButton03 = (CircularProgressButton) findViewById(R.id.circularButton03);
//        circularButton03.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (circularButton03.getProgress() == 0) {
//                    simulateSuccessProgress(circularButton03); // 如果是初始状态就开始进入进度条动画
//                } else {
//                    circularButton03.setProgress(0); // 如果不是初始状态，那么就回到初始状态
//                }
//            }
//        });
//
//        final CircularProgressButton circularButton04 = (CircularProgressButton) findViewById(R.id.circularButton04);
//        circularButton04.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (circularButton04.getProgress() == 0) { // 如果是初始状态就开始进入进度条动画
//                    simulateErrorProgress(circularButton04);
//                } else {
//                    circularButton04.setProgress(0); // 如果不是初始状态，那么就回到初始状态
//                }
//            }
//        });
//
//
//        final CircularProgressButton circularButton05 = (CircularProgressButton) findViewById(R.id.circularButton05);
//        circularButton05.setIndeterminateProgressMode(true);
//        circularButton05.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (circularButton05.getProgress() == 0) {
//                    circularButton05.setProgress(50);
//                } else if (circularButton05.getProgress() == 100) {
//                    circularButton05.setProgress(0);
//                } else {
//                    circularButton05.setProgress(100);
//                }
//            }
//        });
//
//        final CircularProgressButton circularButton06 = (CircularProgressButton) findViewById(R.id.circularButton06);
//        circularButton06.setIndeterminateProgressMode(true);
//        circularButton06.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (circularButton06.getProgress() == 0) {
//                    circularButton06.setProgress(50);
//                } else if (circularButton06.getProgress() == -1) {
//                    circularButton06.setProgress(0);
//                } else {
//                    circularButton06.setProgress(-1);
//                }
//            }
//        });
    }

    @OnClick({R.id.cpb1, R.id.btn1, R.id.btn2, R.id.btn3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cpb1:
                if (cpb1.getProgress() == CPB_INIT) {
                    cpb1.setProgress(CPB_LOADING); // 点击后开始不精准进度，不精准进度的进度值一直为50
                    new MyToast(this, "请求中...", 1);
                } else if (cpb1.getProgress() == CPB_SUCCESS || cpb1.getProgress() == CPB_FAILED) {
                    cpb1.setProgress(CPB_INIT);
                }
                break;
            case R.id.btn1:
                cpb1.setProgress(CPB_SUCCESS); // -1表示出错，显示出错的图片和背景，100表示完成，显示完成的图片和背景
                break;
            case R.id.btn2:
                cpb1.setProgress(CPB_FAILED);
                break;
            case R.id.btn3:
                break;
        }
    }

    /**
     * 设置成功的进度
     *
     * @param button
     */
    private void simulateSuccessProgress(final CircularProgressButton button) {
        // 这里巧妙运用了valueAnimator这个类来计算动画的值，这个类本身就起计算作用，不处理任何动画，这里在计算好后自行进行了进度的设定
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100); // 设定范围为1到100
        widthAnimation.setDuration(1500); // 设定动画的持续时间
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator()); // 设定动画的插值器
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // 在动画进行时进行处理
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value); // 设置进度为当前动画的进度
            }
        });
        widthAnimation.start(); // 开始动画的计算工作
    }

    /**
     * 设置出错时的进度条
     *
     * @param button
     */
    private void simulateErrorProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value); // 将当前动画的进度设置为按钮的进度
                if (value == 99) { // 如果按钮的进度到了99，那么直接设置为出错状态
                    button.setProgress(-1);
                }
            }
        });
        widthAnimation.start(); // 开始动画的计算
    }

}