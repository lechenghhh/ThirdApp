package com.lecheng.hello.thirdapp.Widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

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
    }


}
