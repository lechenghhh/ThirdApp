package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.lecheng.hello.thirdapp.Adapter.RecyclerViewUnitAdpt.CommonAdapter;
import com.lecheng.hello.thirdapp.Adapter.RecyclerViewUnitAdpt.base.ViewHolder;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.LoadRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty064MyLoadRv extends AppCompatActivity {

    @Bind(R.id.lv1)
    LoadRecyclerView lrv1;

    private String[] strings = {"第1个", "第2个", "第3个", "第4个",};
    private List<String> arr = new ArrayList<>();
    private CommonAdapter<String> adpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty064_myloadlv);
        ButterKnife.bind(this);

        lrv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lrv1.setItemAnimator(new DefaultItemAnimator());

        arr = Arrays.asList(strings);
//        lrv1.setLoadingImg(R.drawable.ic_play);
    }

    @OnClick({R.id.btnLoadLv,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoadLv:
                lrv1.setAdapter(adpt = new CommonAdapter<String>(this, arr, R.layout.item053_fethg) {
                    @Override
                    protected void convert(ViewHolder holder, String s, int position) {
                        holder.setText(R.id.tv1, s);
                    }
                });
                adpt.notifyDataSetChanged();
                break;
        }
    }
}
