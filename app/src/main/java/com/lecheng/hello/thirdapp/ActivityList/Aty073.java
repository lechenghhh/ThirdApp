package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.HideTopScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Aty073 extends Activity {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.hsv)
    HideTopScrollView hsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty073);
        ButterKnife.bind(this);
        hsv.setImgeViewOnClickGoToFirst(tvTitle);
    }

    public void onScrollChanged(int x, int y, int oldx, int oldy) {
//        // TODO Auto-generated method stub
//        // Log.i("TAG", "y--->" + y + "    height-->" + height);
//        if (y <= 0) {
//            textView.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
//        } else if (y > 0 && y <= imageHeight) {
//            float scale = (float) y / imageHeight;
//            float alpha = (255 * scale);
//            // 只是layout背景透明(仿知乎滑动效果)
//            textView.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
//        } else {
//            textView.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
//        }
    }
}
