package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Adapter.Adpt021Rv;
import com.lecheng.hello.thirdapp.Adapter.RecyclerViewUnitAdpt.RvUnitAdpt;
import com.lecheng.hello.thirdapp.Adapter.RecyclerViewUnitAdpt.ViewHolder;
import com.lecheng.hello.thirdapp.Adapter.recyclerview.CommonAdapter;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty021RecyclerView extends Activity {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.rv1)
    RecyclerView rv1;
    private int a = 1;//显示的
    private ArrayList<String> arr = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty021);
        ButterKnife.bind(this);

        arr.add("第1个哈哈哈");
        arr.add("第2个哈哈哈");
        arr.add("第3个哈哈哈");
        arr.add("第4个哈哈哈");
        arr.add("第5个哈哈哈");
        arr.add("第6个哈哈哈");
        arr.add("第7个哈哈哈");
        rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @OnClick({R.id.btn1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                new MyToast(this, "好东西", 3000);
                tv.setText("数据是：" + a);
                a++;
                break;
        }
    }
}