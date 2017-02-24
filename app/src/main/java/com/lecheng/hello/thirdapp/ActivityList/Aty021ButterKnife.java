package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty021ButterKnife extends Activity {


    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.btn1)
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity021);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.tv, R.id.btn1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                new MyToast(getApplication(), "好东西", 3000);
                break;
            case R.id.btn1:
                break;
        }
    }
}