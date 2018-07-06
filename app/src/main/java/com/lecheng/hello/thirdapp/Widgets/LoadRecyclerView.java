package com.lecheng.hello.thirdapp.Widgets;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

/**
 * Created by Cheng on 2017/12/6.
 */

public class LoadRecyclerView extends RecyclerView {
    private Context context;
    private int itemId, imgId = android.R.drawable.ic_menu_recent_history;
    private String strLoad = "正在加载中...";
    private String strNoMore = "没有更多数据";

    public LoadRecyclerView(Context context) {
        super(context);
        this.context = context;
        super.setAdapter(new LoadAdpt(context));
    }

    public LoadRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        super.setAdapter(new LoadAdpt(context));
    }

    public LoadRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        super.setAdapter(new LoadAdpt(context));
    }

    @Override
    public void setAdapter(Adapter adapter) {
//        if (adapter.getItemCount() == 0) {
////            System.out.println("MyLoadLv:没有行数！");
//            super.setAdapter(new LoadAdpt(context));
//        } else
            super.setAdapter(adapter);

    }

    public void setLoadingImg(int imgId) {//请在setAdapter方法前调用
        this.imgId = imgId;
    }


    private class LoadAdpt extends RecyclerView.Adapter<LoadAdpt.ViewHolder> {

        private Context context;
        private int size = 0;

        public LoadAdpt(Context context) {
            this.context = context;
//            this.size = size;

        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
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
            tv.setText(strLoad);
            tv.setTextColor(Color.GRAY);
            tv.setTextSize(20);
            layout.addView(tv);
            return new ViewHolder(layout);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//            holder.name.setText(data.get(position));
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.e("这里是点击每一行item的响应事件", "" + position + item);
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
//                name = itemView.findViewById(R.id.name);

            }
        }
    }


}
