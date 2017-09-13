package com.lecheng.hello.thirdapp.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Cheng on 2015/8/6.
 */
public class MyPreviewAdpt extends PagerAdapter {
    private List<View> views;
    private Context c;

    public MyPreviewAdpt(List<View> views, Context c) {
        this.views = views;
        this.c = c;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(views.get(position));


//        ImageLoader imageLoader = ImageLoader.getInstance();
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}
