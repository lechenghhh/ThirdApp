package com.lecheng.hello.thirdapp.ActivityList;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Utils.Interface.HttpVolley;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;

//http://blog.csdn.net/xyz_lmn/article/details/48055919
//http://www.open-open.com/lib/view/open1474856702239.html
//Api地址：
//http://gank.io/api/search/query/listview/category/%E7%A6%8F%E5%88%A9/count/10/page/1
public class Aty053Coordinator extends ActionBarActivity implements HttpVolley.OnResponseListener {
    private final String URL = "http://gank.io/api/search/query/listview/category/%E7%A6%8F%E5%88%A9/count/10/page/1";
    @Bind(R.id.tv1)
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty053);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21) {//沉浸式状态栏
            getWindow().getDecorView().setSystemUiVisibility
                    (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyToast(Aty053Coordinator.this, "HelloWorld!", 1);
            }
        });

        HttpVolley myAsyncHttp = new HttpVolley(this);
        myAsyncHttp.Get(URL, this);
    }

    @Override
    public void onSuccess(String strJson) {
        tv1.setText(strJson);
    }
}
