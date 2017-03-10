package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty021ButterKnife extends Activity {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.btn1)
    Button btn1;
    private int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty021);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn1)
    public void onClick() {
        new MyToast(getApplication(), "好东西", 3000);
        tv.setText("数据是：" + a);
        a++;
    }
}