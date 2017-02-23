package com.lecheng.hello.thirdapp.Utils;

import android.content.Context;
import android.content.Intent;

import com.lecheng.hello.thirdapp.ActivityList.Aty024ShareToFriends;

public class ShareToFriends {

    public ShareToFriends(Context context, Aty024ShareToFriends view, String title, String url) {
        Intent intent = new Intent(Intent.ACTION_SEND); // �������?�͵�����
        intent.setType("text/plain"); // ���?�͵��������
        intent.putExtra(Intent.EXTRA_SUBJECT, "111"); // ���������
        String text = title + "\n";
        text += url;//分享的内容
        intent.putExtra(Intent.EXTRA_TEXT, text); // ���������
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享一下吧！"));//对话框标题
    }
}
