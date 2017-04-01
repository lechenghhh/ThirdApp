package com.lecheng.hello.thirdapp.ActivityList;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.lecheng.hello.thirdapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/*http://blog.csdn.net/android_xiaoqi/article/details/7618895*/
public class Aty051PopWindow extends AppCompatActivity {
    private PopupWindow pop = null;
    private View view = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty051);
        ButterKnife.bind(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.aty051_pop, null);        // 引入窗口配置文件
        pop = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        pop.setBackgroundDrawable(new BitmapDrawable());//需要设置一下此参数，点击外边可消失
        pop.setOutsideTouchable(true);        //设置点击窗口外边窗口消失
        pop.setFocusable(true);        // 设置此参数获得焦点，否则无法点击

    }


    @OnClick({R.id.btn2, R.id.btn1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn2:
                break;
            case R.id.btn1:
                if (pop.isShowing())
                    pop.dismiss();            // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                else
                    pop.showAsDropDown(view);            // 显示窗口
                break;
        }
    }
}
