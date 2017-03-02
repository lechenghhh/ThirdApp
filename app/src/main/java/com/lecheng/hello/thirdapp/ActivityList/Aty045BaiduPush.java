package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MySharedPreferences;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        setContentView(R.layout.activity045_baidupush);
        ButterKnife.bind(this);
        displayPushState();//显示推送状态

    }

    @OnClick({R.id.btnStartWork, R.id.btnStop, R.id.btnResume, R.id.btnSync})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartWork:
                PushManager.startWork(getApplicationContext(),    //百度推送apikey写入
                        PushConstants.LOGIN_TYPE_API_KEY, API_KEY);
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
                tv2.setText(MySharedPreferences.loadData(this, "push_data", "null") + "");
                break;
        }
    }

    private void displayPushState() {
        if (PushManager.isPushEnabled(this))
            tv.setText("推送已经开启");
        else
            tv.setText("推送已经停止");
    }

    /*
    *public void onMessage(Context context, String message, String customContentString);//接收透传消息
    * public static boolean isPushEnabled(Context context);//是否被停止
    *
    * */
}
