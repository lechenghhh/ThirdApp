package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Adapter.RecyclerViewUnitAdpt.RvAdpt;
import com.lecheng.hello.thirdapp.Adapter.RecyclerViewUnitAdpt.ViewHolder;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty021RecyclerView extends Activity {

    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.rv1)
    RecyclerView rv1;
    ArrayList<String> bean = new ArrayList<>();
    RvAdpt<String> adpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty021);
        ButterKnife.bind(this);
        bean.add("wx_oCpooxHPGU_XIsyaUdC91b4xcy-s");
        bean.add("http://www.ifashionpai.cn:8085//upload/1518277443256-5F3E8199863777B11D99FAAE94CB1D2D.jpg");
        bean.add("18606940620");
        bean.add("wx_oCpooxLTlUQ6uX5ENQGYlwMHpITw");
        bean.add("http://p1.music.126.net/0PHvkaVqhs8Lai0kVrkj1g==/19095218439691647.jpg?param=180y180");
        rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv1.setItemAnimator(new DefaultItemAnimator());
    }

    @OnClick({R.id.btn1, R.id.btnAdd, R.id.btnDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                rv1.setAdapter(adpt = new RvAdpt<String>(this, R.layout.item021_rv, bean) {
                    @Override
                    public void convert(ViewHolder holder, String s, int position) {
                        holder.setText(R.id.tv1, s);
                        holder.setOnClickListener(R.id.tv2, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new MyToast(Aty021RecyclerView.this, "点击条目" + (position + 1), 1);
                                holder.setImage(R.id.iv1, s);
                            }
                        });
                    }
                });
                break;
            case R.id.btnAdd:
                bean.add("new Item");
                adpt.notifyItemInserted(bean.size());
                break;
            case R.id.btnDelete:
                if (bean.size() != 0)
                    bean.remove(0);
                adpt.notifyItemRemoved(0);
                break;
        }
    }

}