package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*              view移动动画
*/

public class Aty004Animation extends ActionBarActivity {

    @Bind(R.id.textView3)
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty004);
        ButterKnife.bind(this);
        findViewById(R.id.btnAnimMe3).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final ScaleAnimation sa = new ScaleAnimation(0.98f, 1.0f, 0.98f, 1f, 1, 0.5f, 1, 0.5f);
                sa.setDuration(100);
                v.startAnimation(sa);
                return false;                   //true会消耗掉这个事件
            }
        });
    }

    @OnClick({R.id.btnAnimMe, R.id.btnAnimMe2, R.id.btnAnimMe3})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAnimMe:
                final TranslateAnimation ta = new TranslateAnimation(0, 222, 0, 111);
                ta.setDuration(1000);
                v.startAnimation(ta);
                break;
            case R.id.btnAnimMe2:

                break;
            case R.id.btnAnimMe3:
//                final TranslateAnimation ta2 = new TranslateAnimation(2, 2, 2, 2);
//                ta2.setDuration(10);
//                v.startAnimation(ta2);
                break;
        }
    }
}
