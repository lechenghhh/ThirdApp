package com.lecheng.hello.thirdapp.ActivityList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Service.Ser042Download;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty042ServiceDownLoad extends AppCompatActivity {

    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity042_test);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btnDownload, R.id.tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDownload:
                tv.setText("点击了下载...");
                Bundle bundle = new Bundle();
                bundle.putString("download_url", "http://121.42.56.143:8080/task/klhb/GridTask.apk");/*电子签名下载地址*/
                Intent it = new Intent().setClass(this, Ser042Download.class).putExtras(bundle);
                startService(it);
                break;
            case R.id.tv:
                break;
        }
    }
}
