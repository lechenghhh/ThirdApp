package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.lecheng.hello.thirdapp.Interface.IWListener;
import com.lecheng.hello.thirdapp.Net.HttpGo;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

/* 超精准计步器：http://blog.csdn.net/linglongxin24/article/details/52868803*/
public class Aty055View extends ActionBarActivity implements IWListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty055_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn2)
    public void onViewClicked() {

        HashMap<String, String> map = new HashMap<String, String>();
        new HttpGo().http047Post(this, "http://10.0.110.134:8084/gallery", map, this);
    }

    @Override
    public void onSuccess(String strJson) {
        new MyToast(this, strJson, 1);
    }
}
