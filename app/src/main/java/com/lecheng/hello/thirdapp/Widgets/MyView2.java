package com.lecheng.hello.thirdapp.Widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Cheng on 2015/8/5.
 */
public class MyView2 extends View {
    private float i = 0;
    private Paint paint = new Paint();

    public MyView2(Context context) {
        super(context);
        startload();
    }

    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        startload();
    }

//        super(context, attrs, defStyleAttr);
//        startload();
//    }

    public void startload() {
        paint.setColor(Color.BLUE);                         //初始化，为保证初始化成功，在三个方法中皆调用
    }

    @Override
    public void draw(Canvas canvas) {                       //重写绘制函数
        super.draw(canvas);

        canvas.save();
        canvas.translate(140, 170);
        canvas.rotate(i, 100, 100);                         //旋转到i角度,以200,200为中心旋转
        canvas.drawRect(0, 0, 200, 200, paint);             //绘制方形
        i++;
        System.out.printf("旋转我在旋转");

        canvas.restore();
        invalidate();                                       //调用此方法使系统认为该视图无效
        // 因此重新执行draw();

    }
}
