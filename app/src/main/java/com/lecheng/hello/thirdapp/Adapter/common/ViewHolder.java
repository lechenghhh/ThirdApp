package com.lecheng.hello.thirdapp.Adapter.Common;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

//import com.example.zhy_baseadapterhelper.ImageLoader.Type;

public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
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

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /*
    * lecheng create by 2016-11-7
    * */
//    public ViewHolder setGridView(int viewId,)

    /**
     * 为ImageView设置图片
     *
     * @param viewId //	 * @param drawableId
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId //	 * @param drawableId
     * @return
     */
    public ViewHolder setImageByUrl(int viewId, String url) {
//		ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
//				(ImageView) getView(viewId));
        imageLoader.displayImage(url, (ImageView) getView(viewId));
        return this;
    }

    //View显示隐藏
    public ViewHolder setVisible(int viewId, boolean b) {
        View v1 = getView(viewId);
        if (b) {
            v1.setVisibility(View.VISIBLE);
        } else {
            v1.setVisibility(View.GONE);
        }
        return this;
    }


    public int getPosition() {
        return mPosition;
    }


}
