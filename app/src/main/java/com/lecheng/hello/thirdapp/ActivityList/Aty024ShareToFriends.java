package com.lecheng.hello.thirdapp.ActivityList;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.ShareToFriends;

public class Aty024ShareToFriends extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity024);
        findViewById(R.id.activity_main_share_friends_framelayout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new ShareToFriends(Aty024ShareToFriends.this,
                this, "hello", "http://www.tctu.cn");         //分享在这
    }
}
