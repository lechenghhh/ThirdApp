package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.SimpleAdapter;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.xListView.XListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Aty026XListView extends AppCompatActivity implements XListView.IXListViewListener {
    private XListView mListView;
    private SimpleAdapter mAdapter1;
    private Handler mHandler;
    private ArrayList<HashMap<String, Object>> dlist;
    /** 初始化本地数据 */
    String data[] = new String[] { "三块石国家森林公园", "关山湖国家水利风景区", "小鹿沟青龙寺景区",
            "天女山风景区", "后安腰堡采摘园" };
    String data1[] = new String[] { "抚顺县救123兵乡王木村", "抚顺县救兵乡王木村", "抚顺县救兵乡王木村",
            "抚顺县救兵乡王木村", "抚顺县救兵乡王木村" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity026);
        /** 下拉刷新，上拉加载 */
        dlist = new ArrayList<HashMap<String, Object>>();
        mListView = (XListView) findViewById(R.id.techan_xListView);// 你这个listview是在这个layout里面
        mListView.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
        mAdapter1 = new SimpleAdapter(Aty026XListView.this, getData(),
                R.layout.cell026, new String[] { "name", "img", "content" },
                new int[] { R.id.title, R.id.mImage, R.id.content });
        mListView.setAdapter(mAdapter1);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
    }
    private ArrayList<HashMap<String, Object>> getData() {
        for (int i = 0; i < data.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", data[i]);
            map.put("content", data1[i]);
            map.put("img", R.drawable.ic_delete);
            dlist.add(map);
        }
        return dlist;
    }


    private void onLoad() {/** 停止刷新， */
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override // 刷新
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                getData();
                mListView.setAdapter(mAdapter1);
                onLoad();
            }
        }, 2000);
    }


    @Override // 加载更多
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                getData();
                mAdapter1.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.finish();
        }
        return false;
    }

}
