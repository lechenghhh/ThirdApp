package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.lecheng.hello.thirdapp.R;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.duration;

public class Aty048Test extends AppCompatActivity {

    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etCode)
    EditText etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty048);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSend, R.id.btnCheck})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                break;
            case R.id.btnCheck:
                break;
        }
    }



}
