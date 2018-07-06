package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Adapter.RecyclerViewUnitAdpt.wrapper.EmptyWrapper;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;
import com.lecheng.hello.thirdapp.Adapter.RecyclerViewUnitAdpt.CommonAdapter;
import com.lecheng.hello.thirdapp.Adapter.RecyclerViewUnitAdpt.base.ViewHolder;

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
    //    RvAdpt<String> adpt;
    CommonAdapter<String> adpt2;

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

        adpt2 = new CommonAdapter<String>(this, R.layout.item021_rv, bean) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv1, s);
                holder.setText(R.id.tv2, "Position:" + holder.getAdapterPosition() + "," + holder.getLayoutPosition());
                holder.setOnClickListener(R.id.tv2, (v) -> {
                    new MyToast(Aty021RecyclerView.this, "点击条目" + (position + 1), 1);
                    holder.setImage(R.id.iv1, s);
                });
            }
        };
    }

    @OnClick({R.id.btn1, R.id.btnHeader, R.id.btnAdd, R.id.btnDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                rv1.setAdapter(adpt2);
                break;
            case R.id.btnHeader:
//                HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adpt2);
//                TextView t1 = new TextView(this);
//                t1.setText("Header 1");
//                TextView t2 = new TextView(this);
//                t2.setText("Header 2");
//                mHeaderAndFooterWrapper.addHeaderView(t1);
//                mHeaderAndFooterWrapper.addHeaderView(t2);
                EmptyWrapper mEmptyWrapper = new EmptyWrapper(adpt2);
                mEmptyWrapper.setEmptyView(LayoutInflater.from(this).inflate(R.layout.unit_suspension, rv1, false));
                break;
            case R.id.btnAdd:
                bean.add("new Item");
                adpt2.notifyItemInserted(bean.size());
                break;
            case R.id.btnDelete:
                if (bean.size() != 0)
                    bean.remove(0);
                adpt2.notifyItemRemoved(0);
                break;
        }
    }
}