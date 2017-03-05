package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty044AliPay extends AppCompatActivity {

    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity044_alipay);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        tv.setText("Hello！");
    }
}
