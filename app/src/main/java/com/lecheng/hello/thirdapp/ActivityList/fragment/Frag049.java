package com.lecheng.hello.thirdapp.ActivityList.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.ActivityList.Aty018MyBrowser;
import com.lecheng.hello.thirdapp.Adapter.Common.UnityAdpt;
import com.lecheng.hello.thirdapp.Adapter.Common.ViewHolder;
import com.lecheng.hello.thirdapp.Bean.Gson.Bean049;
import com.lecheng.hello.thirdapp.Net.HttpGo;
import com.lecheng.hello.thirdapp.Interface.IWListener;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.GsonUtil;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Cheng on 2017/3/19.
 * 文档地址：http://wangyi.butterfly.mopaasapp.com/
 */

public class Frag049 extends Fragment implements IWListener {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.lv1)
    ListView lv1;
    private HttpGo myAsyncHttp;
    private static final String URL = "http://wangyi.butterfly.mopaasapp.com/news/api";
    // + "?type=war&page=1&limit=10";
    private HashMap<String, String> hashMap = new HashMap<String, String>();
    private String type = "war";
    private int page = 1;

    public Frag049(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.aty049_item, null, false);
        ButterKnife.bind(this, root);
        init();
        return root;

    }

    private void init() {
        tvTitle.setText(type);
        myAsyncHttp = new HttpGo();
        hashMap.put("type", type);
        hashMap.put("page", page + "");
        hashMap.put("limit", "10");
        myAsyncHttp.http047Post(getActivity(), URL, hashMap, this);
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                if (page != 1) {
                    page--;
                    hashMap.put("page", page + "");
                    myAsyncHttp.http047Post(getActivity(), URL, hashMap, this);
                } else
                    new MyToast(getActivity(), "已到第一页", 3333);
                break;
            case R.id.btn2:
                page++;
                hashMap.put("page", page + "");
                myAsyncHttp.http047Post(getActivity(), URL, hashMap, this);
                break;
        }
    }

    @Override
    public void onSuccess(final String strJson) {
        new MyToast(getActivity(), type + "数据加载成功,page=" + page, 3333);
        final Bean049 bean049 = GsonUtil.GsonToBean(strJson, Bean049.class);
//        lv1.setAdapter(new Adpt049List(getActivity(), bean049));
        lv1.setAdapter(new UnityAdpt<Bean049.ListBean>(getActivity(), bean049.getList(), R.layout.cell049_metro) {
            @Override
            public void convert(ViewHolder helper, Bean049.ListBean item) {
                helper.setText(R.id.tv1, item.getTitle());
                helper.setText(R.id.tv2, item.getTime());
            }
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                final TranslateAnimation ta2 = new TranslateAnimation(4, 4, 4, 4);
                ta2.setDuration(1);
                v.startAnimation(ta2);
                startActivity(new Intent(getActivity(), Aty018MyBrowser.class)
                        .putExtra("url", bean049.getList().get(position).getDocurl()));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        myAsyncHttp.http047Cancel("cancelPost");
    }
}
