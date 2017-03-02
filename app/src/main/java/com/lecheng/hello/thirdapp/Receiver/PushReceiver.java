package com.lecheng.hello.thirdapp.Receiver;

import android.content.Context;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.lecheng.hello.thirdapp.Bean.EventBusMsg.MsgPush;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by 乐城 on 2017/3/2.
 * 百度推送的Receiver
 */

public class PushReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int i, String s, String s1, String s2, String s3) {

    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String s1, String s2) {
        new MyToast(context, s1 + "/n" + s2, 3333);
        EventBus.getDefault().post(new MsgPush(s1, s2));
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {

    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {

    }
}
