package com.lecheng.hello.thirdapp.Utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentUtil {
    public static void startFragment(Context c, android.support.v4.app.Fragment frag) {
        AppCompatActivity activity = (AppCompatActivity) c;
        activity.getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)  //将当前fragment加入到返回栈中
//                        .setCustomAnimations(R.anim.bottom_in, R.anim.bottom_out)//设置动画效果
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)//设置动画效果
                .replace(android.R.id.content, frag)
                .commit();
    }

    public static void startFragment(Context c, android.support.v4.app.Fragment frag, Bundle b) {
        AppCompatActivity activity = (AppCompatActivity) c;
        frag.setArguments(b);
        activity.getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)  //将当前fragment加入到返回栈中
//                        .setCustomAnimations(R.anim.bottom_in, R.anim.bottom_out)//设置动画效果
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)//设置动画效果
                .replace(android.R.id.content, frag)
                .commit();
    }
}
