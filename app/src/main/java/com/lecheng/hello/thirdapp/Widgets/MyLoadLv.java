package com.lecheng.hello.thirdapp.Widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Cheng on 2017/12/6.
 */

public class MyLoadLv extends ListView {
    private Context context;
    private int itemId, imgId = android.R.drawable.ic_menu_recent_history;
    private String strLoad = "正在加载中...";
    private String strNoMore = "没有更多数据";

    public MyLoadLv(Context context) {
        super(context);
        this.context = context;
        super.setAdapter(new LoadAdpt(context, imgId, strLoad));
    }

    public MyLoadLv(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        super.setAdapter(new LoadAdpt(context, imgId, strLoad));
    }

    public MyLoadLv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        super.setAdapter(new LoadAdpt(context, imgId, strLoad));
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (adapter.getCount() == 0) {
//            System.out.println("MyLoadLv:没有行数！");
            super.setAdapter(new LoadAdpt(context, imgId, strNoMore));
            super.setDivider(null);
        } else {
            super.setAdapter(adapter);
        }
    }

    public void setLoadingImg(int imgId) {//请在setAdapter方法前调用
        this.imgId = imgId;
    }

    private class LoadAdpt extends BaseAdapter {
        private Context context;
        private int imgId;
        private String textLoad;

        public LoadAdpt(Context context, int imgId, String textLoad) {
            this.context = context;
            this.imgId = imgId;
            this.textLoad = textLoad;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return 1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //这里的Textview的父layout是ListView，所以要用ListView.LayoutParams

            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 800);
            layout.setLayoutParams(layoutParams);

            ImageView iv = new ImageView(context);
            LayoutParams ivParams = new LayoutParams(120, 120);
            iv.setLayoutParams(ivParams);
            iv.setImageResource(imgId);
            layout.addView(iv);

            TextView tv = new TextView(context);
            LayoutParams tvParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(tvParams);
            tv.setPadding(0, 32, 0, 0);
            tv.setText(textLoad);
            tv.setTextColor(Color.GRAY);
            tv.setTextSize(20);
            layout.addView(tv);

            layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });//拦截点击事件
            return layout;
        }
    }
}
