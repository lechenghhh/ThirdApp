package com.lecheng.hello.thirdapp.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.R;

import static android.R.id.message;

/**
 * Created by Cheng on 2016/8/15.
 */
public class MyToast {
    private static Toast toastStart;
    private TextView tvMsg;
    private ImageView ivIcon;

    public MyToast(Context c, String text, int duration) {
        //加载Toast布局
        View toastRoot = LayoutInflater.from(c).inflate(R.layout.unit_toast_style, null);
        //初始化布局控件
        tvMsg = (TextView) toastRoot.findViewById(R.id.tvMsg);
//        ivIcon = (ImageView) toastRoot.findViewById(R.id.ivIcon);
        //为控件设置属性
        tvMsg.setText(text);
//        ivIcon.setImageResource(R.mipmap.ic_launcher);

        //Toast的初始化
        if (toastStart == null) {
            toastStart = new Toast(c);
            //获取屏幕高度
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            int height = wm.getDefaultDisplay().getHeight();
            //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
            toastStart.setGravity(Gravity.TOP, 0, height * 2 / 3);
            toastStart.setDuration(duration);
            toastStart.setView(toastRoot);
        } else {
//            toastStart.setText(text);
            toastStart.setDuration(duration);
            toastStart.setView(toastRoot);
        }
        toastStart.show();

        /*
            if (mToast == null) {
                mToast = Toast.makeText(c, text, duration);
            } else {
                mToast.setText(text);
                mToast.setDuration(duration);
            }
            mToast.show();
        */
    }
}
