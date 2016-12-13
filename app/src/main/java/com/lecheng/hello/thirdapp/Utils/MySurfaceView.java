package com.lecheng.hello.thirdapp.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Cheng on 2015/8/4.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,View.OnTouchListener{
    private Paint p = new Paint();
    private Path path = new Path();

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        p.setColor(Color.BLUE);
        p.setTextSize(10);
        p.setStyle(Paint.Style.STROKE);
        setOnTouchListener(this);
    }

    public void draw(){
        Canvas c = getHolder().lockCanvas();
        c.drawColor(Color.WHITE);
        c.drawPath(path, p);
        getHolder().unlockCanvasAndPost(c);

    }

    public void clear(){
        path.reset();
        draw();
    }


    public void setMyView(int i){
        switch (i) {
            case 1:
                p.setColor(Color.RED);
                draw();
                break;
            case 2:
                p.setColor(Color.GREEN);
                draw();
                break;
            case 3:
                p.setColor(Color.YELLOW);
                draw();
                break;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(),event.getY());                            //获取坐标绘制
                draw();
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());                            //持续获取坐标并绘制
                draw();
                break;
        }


        return true;
    }
}
