package com.lecheng.hello.thirdapp.ActivityList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.lecheng.hello.thirdapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//https://github.com/yingLanNull/ZxingLite
public class Aty044ZXingLite extends AppCompatActivity {

    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty044_zxing);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        tv.setText("Hello！");
        Intent intent = new Intent();
        intent.setClass(this, CaptureActivity.class);
        startActivityForResult(intent, 111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data && requestCode == 111) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Intents.Scan.RESULT);
            tv.setText(scanResult);
        }
    }
}
