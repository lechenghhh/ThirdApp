package com.lecheng.hello.thirdapp.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cheng on 2015/8/6.
 */
//public class Adpt049Frag extends FragmentPagerAdapter {
//
//    private ArrayList<Fragment> fragments;
//
//    public Adpt049Frag(FragmentManager fm, ArrayList<Fragment> fragments) {
//        super(fm);
//        this.fragments = fragments;
//    }
//
//    @Override
//    public Fragment getItem(int i) {
//        return fragments.get(i);
//    }
//
//    @Override
//    public int getCount() {
//        return fragments.size();
//    }
//
//}import java.util.List;

//教程地址：http://blog.csdn.net/guo807015563/article/details/42081799
public class Adpt049Frag extends FragmentPagerAdapter {

    public FragmentManager fm;
    public List<Fragment> list;

    public Adpt049Frag(FragmentManager fm) {
        super(fm);
    }

    public Adpt049Frag(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fm = fm;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Log.i("sssssssssssss", "getItem");
        fragment = list.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id", "" + position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container,
                position);
        fm.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        Fragment fragment = list.get(position);
        fm.beginTransaction().hide(fragment).commit();
    }
}