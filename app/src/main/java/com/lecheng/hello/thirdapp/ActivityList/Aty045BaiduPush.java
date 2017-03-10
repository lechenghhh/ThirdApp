package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.lecheng.hello.thirdapp.Bean.EventBusMsg.MsgPush;
import com.lecheng.hello.thirdapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/*
* http://push.baidu.com/doc/android/api
* */
public class Aty045BaiduPush extends AppCompatActivity {
    private final String PACKGET_NAME = " com.lecheng.hello.thirdapp";
    private final String APP_ID = "9345218";
    private final String API_KEY = "0Lzoyo7vGG9fQTk35KkYcxKw";
    private final String SECRET_KEY = "ql1RgXog6xffloiT3QrW6f205jyW8jKR";
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.tv2)
    TextView tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty045_baidupush);
        ButterKnife.bind(this);
        PushManager.startWork(getApplicationContext(),    //百度推送apikey写入
                PushConstants.LOGIN_TYPE_API_KEY, API_KEY);
        displayPushState();//显示推送状态
        EventBus.getDefault().register(this);//注册事件监听器
    }

    @OnClick({R.id.btnStartWork, R.id.btnStop, R.id.btnResume, R.id.btnSync})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartWork:
                displayPushState();
                break;
            case R.id.btnStop:
                PushManager.stopWork(this);
                displayPushState();
                break;
            case R.id.btnResume:
                PushManager.resumeWork(this);
                displayPushState();
                break;
            case R.id.btnSync:
                break;
        }
    }

    private void displayPushState() {
        if (PushManager.isPushEnabled(this))
            tv.setText("推送已经开启");
        else
            tv.setText("推送已经停止");
    }

    public void onEvent(MsgPush msgPush) {
        tv2.setText(msgPush.msg + "/n" + msgPush.msg2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PushManager.stopWork(this);
        EventBus.getDefault().unregister(this);//注销事件监听器
    }
}
