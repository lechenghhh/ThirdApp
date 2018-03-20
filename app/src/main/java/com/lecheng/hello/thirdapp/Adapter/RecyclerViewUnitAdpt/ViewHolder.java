package com.lecheng.hello.thirdapp.Adapter.RecyclerViewUnitAdpt;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public ViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public static ViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(context, itemView, parent);
        return holder;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    //ImageView加载id资源
    public ViewHolder setImage(int viewId, int resId) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(resId).into(view);//view.setImageResource(resId);
        return this;
    }

    //ImageView加载url资源
    public ViewHolder setImage(int viewId, String resId) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(resId).into(view);//view.setImageResource(resId);
        return this;
    }

    //ImageView加载url资源
    public ViewHolder setImage(int viewId, Bitmap resId) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(resId).into(view);//view.setImageResource(resId);
        return this;
    }

    //ImageView加载url资源,如果传入空值，则隐藏
    public ViewHolder setImage(int viewId, String resId, boolean fail2Gone) {
        ImageView view = getView(viewId);
        if (fail2Gone) {
            if (resId.equals("") || resId == null) {
                view.setVisibility(View.GONE);
                return this;
            }
        }
        Glide.with(mContext).load(resId).into(view);//view.setImageResource(resId);
        return this;
    }

    //View显示隐藏
    public ViewHolder setVisible(int viewId, int vis) {
        View v1 = getView(viewId);
        v1.setVisibility(vis);
        return this;
    }

    //监听事件
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

}