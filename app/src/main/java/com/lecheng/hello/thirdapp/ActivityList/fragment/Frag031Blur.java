package com.lecheng.hello.thirdapp.ActivityList.fragment;

import android.os.Bundle;

import com.lecheng.hello.mylibrary.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by Cheng on 2017/3/5.
 */

public class Frag031Blur extends SupportBlurDialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.debug(true);
        this.setBlurRadius(4);
        this.setDownScaleFactor(5.0f);
    }
}
