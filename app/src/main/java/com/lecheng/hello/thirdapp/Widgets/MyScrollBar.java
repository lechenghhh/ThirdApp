package com.lecheng.hello.thirdapp.Widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Cheng on 2017/11/13.
 */

//http://blog.csdn.net/leilba/article/details/47845081
public class MyScrollBar extends View {
    //当前小球的位置
    private PointF currrentPosition = new PointF(100, 100);
    //手指触摸起点坐标
    private PointF moveStartPosition = new PointF(0, 0);
    //当前手指位置坐标
    private PointF moveEndPosition = new PointF(0, 0);

    public MyScrollBar(Context context) {
        super(context);
    }

    public MyScrollBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.rgb(10, 200, 80));
        canvas.drawRect(0, 0, 1000, 1000, p);

        Paint p2 = new Paint();
        p2.setColor(Color.rgb(255, 255, 255));
        canvas.drawCircle(currrentPosition.x + (moveEndPosition.x - moveStartPosition.x), 50, 40, p2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                moveEndPosition.x = event.getX();
                this.postInvalidate();                //刷新
                break;
            case MotionEvent.ACTION_UP:
                currrentPosition.x += (moveEndPosition.x - moveStartPosition.x);
                moveStartPosition.x = moveEndPosition.x;
                break;
        }
        return true;
    }
}
