package com.lecheng.hello.thirdapp.ActivityListItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.lecheng.hello.thirdapp.R;

import static com.lecheng.hello.thirdapp.R.*;


public class Aty003EveryLayout_Frame extends Activity {


    private android.widget.FrameLayout root;
    private ImageView ivA,ivB;
    private ScaleAnimation sta0 = new ScaleAnimation(1,0,1,1,
            Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
    private ScaleAnimation sta1 = new ScaleAnimation(0,1,1,1,
            Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity010_1);

        root = (android.widget.FrameLayout) findViewById(R.id.root);
        initview();


        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ivA.getVisibility() == View.VISIBLE) {              //判断A是否在展示
                    ivA.startAnimation(sta0);                           //开启A的关闭动画
                }else{
                    ivB.startAnimation(sta0);
                }

            }
        });




    }

    private void showA(){
        ivA.setVisibility(View.VISIBLE);
        ivB.setVisibility(View.INVISIBLE);

    }

    private void showB(){
        ivB.setVisibility(View.VISIBLE);
        ivA.setVisibility(View.INVISIBLE);

    }

    private void initview(){                                            //初始化
        ivA= (ImageView) findViewById(R.id.ivA);
        ivB= (ImageView) findViewById(R.id.ivB);
        showA();                                                        //显示A
        sta0.setDuration(500);
        sta1.setDuration(500);
        sta0.setAnimationListener(new Animation.AnimationListener() {   //A的结束动画监听
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (ivA.getVisibility() == View.VISIBLE) {              //若之前A处在显示
                    ivA.setAnimation(null);
                    showB();                                            //展示B
                    ivB.startAnimation(sta1);                           //启动B的动画，为开启动画
                }else {
                    ivB.setAnimation(null);
                    showA();
                    ivA.startAnimation(sta1);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });
    }


}
