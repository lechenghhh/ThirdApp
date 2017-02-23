package com.lecheng.hello.thirdapp.ActivityList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.BackTopScrollView;

public class Aty025TestBackTop extends AppCompatActivity implements View.OnClickListener {
    private BackTopScrollView scrollView;
    private View btn;
    private static int moveY = 0;
    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
//    float x1 = 0,x2 = 0,y1 = 0,y2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity025);
        scrollView = (BackTopScrollView) findViewById(R.id.sv25);
        btn = findViewById(R.id.btn25_v);
        scrollView.setImgeViewOnClickGoToFirst(btn);
//        btn.setOnClickListener(this);
//        scrollView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    // 可以监听到ScrollView的滚动事件
//                    System.out.println(moveY + "<--moveY & getScrollY-->" + scrollView.getScrollY());
//                    if (moveY < scrollView.getScrollY()) {
//                        hideBackTopBtn(btn, 2);
//                        moveY = scrollView.getScrollY();
//                    } else if (moveY > scrollView.getScrollY()) {
//                        hideBackTopBtn(btn, 32);
//                        moveY = scrollView.getScrollY() + 20;
//                    } else {
//                    }
//                }
//                return false;
//            }
//        });
    }

//    private void hideBackTopBtn(LinearLayout ll, int height) {
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ll.getLayoutParams();
//        lp.height = MyDensityUtil.dip2pxMethod(Aty025TestBackTop.this, height);             //120dp根据屏幕转换成px
//        btn.setLayoutParams(lp);
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //继承了Activity的onTouchEvent方法，直接监听点击事件
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            //当手指按下的时候
//            x1 = event.getX();
//            y1 = event.getY();
//        }
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            //当手指离开的时候
//            x2 = event.getX();
//            y2 = event.getY();
//            if (y1 - y2 > 50) {
//                Toast.makeText(this, "向上滑", Toast.LENGTH_SHORT).show();
//            } else if (y2 - y1 > 50) {
//                Toast.makeText(this, "向下滑", Toast.LENGTH_SHORT).show();
//            } else if (x1 - x2 > 50) {
//                Toast.makeText(this, "向左滑", Toast.LENGTH_SHORT).show();
//            } else if (x2 - x1 > 50) {
//                Toast.makeText(this, "向右滑", Toast.LENGTH_SHORT).show();
//            }
//        }
////        return super.onTouchEvent(event);
//        return true;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn25_v:
//                scrollView.smoothScrollTo(0, 0);
//                Toast.makeText(this, "返回顶部", Toast.LENGTH_SHORT).show();
//                hideBackTopBtn(btn, 2);
                moveY = 0;
                break;
        }
    }
}
