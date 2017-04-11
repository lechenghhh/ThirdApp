package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.RippleBackground;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//https://github.com/skyfishjy/android-ripple-background
public class Aty009View extends AppCompatActivity {


    @Bind(R.id.ripplebg)
    RippleBackground ripplebg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty009);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.button4, R.id.iv1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                ripplebg.stopRippleAnimation();
                break;
            case R.id.iv1:
                ripplebg.startRippleAnimation();
                break;
        }
    }


}
