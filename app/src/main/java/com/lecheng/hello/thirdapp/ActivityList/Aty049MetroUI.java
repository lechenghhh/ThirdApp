package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Adapter.Common.CommonAdapter;
import com.lecheng.hello.thirdapp.Adapter.Common.ViewHolder;
import com.lecheng.hello.thirdapp.Bean.Bean039Weather;
import com.lecheng.hello.thirdapp.Bean.Bean049;
import com.lecheng.hello.thirdapp.Http.Http047;
import com.lecheng.hello.thirdapp.Interface.I047Listener;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty049MetroUI extends AppCompatActivity implements I047Listener {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.lv1)
    ListView lv1;
    private Http047 http047;
    private static final String URL = "http://wangyi.butterfly.mopaasapp.com/news/api?type=war&page=1&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty049);
        ButterKnife.bind(this);
        http047 = new Http047();

    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                http047.http047Post(this, URL, new HashMap<String, String>(), this);
                break;
            case R.id.btn2:
                break;
        }
    }

    @Override
    public void onSuccess(String strJson) {
        Bean049 bean049 = GsonUtil.GsonToBean(strJson, Bean049.class);
        lv1.setAdapter(new CommonAdapter<Bean049.ListBean>(this, bean049.getList(), R.layout.cell049_metro) {
            @Override
            public void convert(ViewHolder helper, Bean049.ListBean item) {
                helper.setText(R.id.tv1, item.getTitle());
                helper.setText(R.id.tv2, item.getTime());
            }
        });


    }


}
