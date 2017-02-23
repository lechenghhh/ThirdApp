package com.lecheng.hello.thirdapp.ActivityListItem;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyShareToFriendsUtils;

public class Aty024DemoShareToFriends extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity024);

        new MyShareToFriendsUtils(Aty024DemoShareToFriends.this,
                this, "hello", "http://www.tctu.cn");         //分享在这
    }


}
