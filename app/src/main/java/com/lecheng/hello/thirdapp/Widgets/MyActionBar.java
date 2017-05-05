package com.lecheng.hello.thirdapp.Widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lecheng.hello.thirdapp.R;
/**
 * Created by 乐城 on 2017/5/5.
 * Constructor构造函数------>onMeasure测量View大小------>onSizeChanged确定View大小------>
 * onLayout确定View布局的位置------>onDraw绘制View中的内容------>视图状态是否改变------>显示
 * <p>
 * 记得在res/values/attr里面声明
 */
public class MyActionBar extends View {
    public MyActionBar(Context context) {
        super(context);
    }

    public MyActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*super.setScaleType(SCALE_TYPE);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);
        int mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_border_width, DEFAULT_BORDER_WIDTH);
        int  mBorderColor = a.getColor(R.styleable.CircleImageView_border_color, DEFAULT_BORDER_COLOR);
        a.recycle();*/
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println(widthMeasureSpec + ":" + heightMeasureSpec);
        // 如果是自定ViewGroup，计算自定义的ViewGroup中所有子控件的大小
        //measureChildren(widthMeasureSpec, heightMeasureSpec);
        int w = getMeasureWidth(widthMeasureSpec);
        int h = getMeasureHeight(heightMeasureSpec);
        setMeasuredDimension(w, h);  //必须调用此方法，否则会抛出异常
    }

    private int getMeasureHeight(int heightMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(heightMeasureSpec);  //每次调用此方法，测量用到的size会发生变化
        int mode = MeasureSpec.getMode(heightMeasureSpec);  //根据定义的Layout_width,Layout_height，会对此值产生影响
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            result = getPaddingLeft()
                    + getPaddingRight();
        } else {
            result = Math.min(result, size);
        }
        System.out.println("Height size:" + size);
        System.out.println("Height mode:" + mode);
        return result;
    }

    private int getMeasureWidth(int widthMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            result = getPaddingTop()
                    + getPaddingBottom();
        } else {
            result = Math.min(result, size);
        }
        System.out.println("Width size:" + size);
        System.out.println("Width mode:" + mode);
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
