package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lecheng.hello.thirdapp.Adapter.Common.UnityAdpt;
import com.lecheng.hello.thirdapp.Adapter.Common.ViewHolder;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyLoadLv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty064MyLoadLv extends AppCompatActivity {

    @Bind(R.id.lv1)
    MyLoadLv lv1;
    private String[] strings = {"第1个", "第2个", "第3个", "第4个",};
    private List<String> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty064_myloadlv);
        ButterKnife.bind(this);

        arr = Arrays.asList(strings);
        lv1.setLoadingImg(R.drawable.ic_play);
    }

    @OnClick({R.id.btnLoadLv, })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoadLv:
                lv1.setAdapter(new UnityAdpt<String>(this, arr, R.layout.cell053_fethg) {
                    @Override
                    public void convert(ViewHolder helper, String item) {
                        helper.setText(R.id.tv1, item);
                    }
                });
                break;
        }
    }
}
