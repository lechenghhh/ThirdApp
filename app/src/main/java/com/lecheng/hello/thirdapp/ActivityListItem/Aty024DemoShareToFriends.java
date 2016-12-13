package com.lecheng.hello.thirdapp.ActivityListItem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.ShareToFriendsUtil;
import com.lecheng.hello.thirdapp.fragment.ShareToFriendsFragment;

public class Aty024DemoShareToFriends extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared24);

        loadShareFriendsFragment();
    }

    private void loadShareFriendsFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ShareToFriendsUtil.loadShareFriendsFragment(fm);
//		ShareToFriendsUtil.shareForFriend();
//		Fragment fragment = fm.findFragmentById(R.id.activity_main_share_friends_framelayout);
//		if (fragment == null) {
//			fragment = ShareToFriendsFragment.newInstance();
//			fm.beginTransaction().add(R.id.activity_main_share_friends_framelayout, fragment)
//					.commit();
//		}
    }
}
