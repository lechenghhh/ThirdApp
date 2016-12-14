package com.lecheng.hello.thirdapp.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 自定义Adapter
 *
 * @author liuyazhuang
 */
public class ImgCycViewPagerAdapter extends PagerAdapter {
    List<ImageView> images;
    Context c;

    public ImgCycViewPagerAdapter(Context c, List<ImageView> images) {
        this.c = c;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
//			super.destroyItem(container, position, object);
//			view.removeView(view.getChildAt(position));
//			view.removeViewAt(position);
        view.removeView(images.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        view.addView(images.get(position));
        return images.get(position);
    }

}