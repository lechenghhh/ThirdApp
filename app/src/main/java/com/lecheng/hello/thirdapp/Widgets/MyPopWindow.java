package com.lecheng.hello.thirdapp.Widgets;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.lecheng.hello.thirdapp.R;


/**
 * Created by Administrator on 2016/11/24.
 */
public class MyPopWindow {
    public MyPopWindow(final Context mContext, View view, int xmlID, int viewID, final String msg) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(xmlID, null);
        // 设置按钮的点击事件
//        contentView.findViewById(viewID).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
//            }
//        });
        final PopupWindow popupWindow = new PopupWindow
                (contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("mengdd", "onTouch : ");
                return false;       // 这里如果返回true的话，touch事件将被拦截
            }
        });                         // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
        popupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.color.tran1));
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框,我觉得这里是API的一个bug
        popupWindow.showAsDropDown(view);        // 设置好参数之后再show
    }
}
