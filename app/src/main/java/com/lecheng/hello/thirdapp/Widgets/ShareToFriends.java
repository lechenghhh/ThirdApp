package com.lecheng.hello.thirdapp.Widgets;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.fragment.ShareToFriendsFragment;

public class ShareToFriends {

    public static void shareForFriend(Context context, View view) {
        Intent intent = new Intent(Intent.ACTION_SEND); // �������?�͵�����
        intent.setType("text/plain"); // ���?�͵��������
        intent.putExtra(Intent.EXTRA_SUBJECT, "111"); // ���������
        String text = "我在发现了好消息！！！\n";
        text += "快来看这个网站:http://blog.csdn.net/lemon_tree12138";
        intent.putExtra(Intent.EXTRA_TEXT, text); // ���������
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享一下吧！")); // Ŀ��Ӧ��ѡ��Ի���ı���
    }

    public static void loadShareFriendsFragment(FragmentManager fm) {
//        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_main_share_friends_framelayout);
        if (fragment == null) {
            fragment = ShareToFriendsFragment.newInstance();
            fm.beginTransaction().add(R.id.activity_main_share_friends_framelayout, fragment)
                    .commit();
        }
    }

}
