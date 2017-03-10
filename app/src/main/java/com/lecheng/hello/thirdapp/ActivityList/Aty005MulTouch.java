package com.lecheng.hello.thirdapp.ActivityList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lecheng.hello.thirdapp.R;


public class Aty005MulTouch extends ActionBarActivity {
    private ImageView iv3;
    private FrameLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty005);

        iv3= (ImageView) findViewById(R.id.iv3);

        root= (FrameLayout) findViewById(R.id.Con);
        root.setOnTouchListener(new View.OnTouchListener() {

            float currentDistance;
            float lastDistance = -1;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("move");

/////////////////////////////////////////////////////////////////////////////////////////////////

                        if (motionEvent.getPointerCount()>=2) {
                            float offsetX = motionEvent.getX(0) - motionEvent.getX(1);
                            float offsetY = motionEvent.getY(0) - motionEvent.getY(1);
                            currentDistance = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY);

                            if (lastDistance<0){
                                lastDistance = currentDistance;
                            }else{
                                if(currentDistance-lastDistance>5){

                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv3.getLayoutParams();
                                    lp.width=(int)(1.1f* iv3.getWidth());
                                    lp.height=(int)(1.1f* iv3.getHeight());

                                    iv3.setLayoutParams(lp);
                                    lastDistance=currentDistance;

                                }else if (lastDistance-currentDistance>5){
                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv3.getLayoutParams();
                                    lp.width=(int)(0.9f* iv3.getWidth());
                                    lp.height=(int)(0.9f* iv3.getHeight());

                                    iv3.setLayoutParams(lp);
                                    lastDistance=currentDistance;

                                }
                            }
                        }
////////////////////////////////////////////////////////////////////////////////////////////////////
//                        System.out.println("触摸点数是"+motionEvent.getPointerCount());
//                        System.out.println(String.format("x1%f y1%f x2%f y2%f",
//                                motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(1), motionEvent.getY(1)));
////////////////////////////////////////////////////////////////////////////////////////////////////
//   点击模块               FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv3.getLayoutParams();
//                        lp.leftMargin= (int) motionEvent.getX();
//                        lp.topMargin=(int)motionEvent.getY();
//
//                        iv3.setLayoutParams(lp);
//
//                        System.out.println(String.format("x:%f,y:%f",motionEvent.getX(),motionEvent.getY()));
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("upup");
                        break;

                }
                return true;
            }
        });




    }

}
