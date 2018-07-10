package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*              view移动动画
http://www.open-open.com/lib/view/open1436757584428.html
//        findViewById(R.id.btnAnimMe3).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                final ScaleAnimation sa = new ScaleAnimation(0.98f, 1.0f, 0.98f, 1f, 1, 0.5f, 1, 0.5f);
//                sa.setDuration(100);
//                v.startAnimation(sa);
//                return false;                   //true会消耗掉这个事件
//            }
//        });

http://www.cnblogs.com/yc-755909659/p/4290114.html
*/

public class Aty004Animation extends ActionBarActivity {


    @Bind(R.id.vView)
    View vView;
    @Bind(R.id.tv1)
    TextView tv1;
    private Animation myAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty004);
        ButterKnife.bind(this);
        tv1.setText(getResources().getConfiguration().locale.getCountry());
    }

    @OnClick({R.id.vView, R.id.btnAnimMe, R.id.btnAnimMe2, R.id.btnAnimMe3, R.id.btnAnimMe4, R.id.btnAnimMe5, R.id.btnAnimMe6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.vView:
                break;
            case R.id.btnAnimMe:
                final TranslateAnimation ta = new TranslateAnimation(0, 222, 0, 111);
                ta.setDuration(1000);
                vView.startAnimation(ta);
                break;
            case R.id.btnAnimMe2:
                vView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_gone));
                vView.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnAnimMe3:
                vView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_visible));
                vView.setVisibility(View.VISIBLE);
                break;
            case R.id.btnAnimMe4:
                final TranslateAnimation ta2 = new TranslateAnimation(12, 0, 12, 0);
                ta2.setDuration(10);
                vView.startAnimation(ta2);
                break;
            case R.id.btnAnimMe5:
                break;
            case R.id.btnAnimMe6:
                break;
        }
    }
}
