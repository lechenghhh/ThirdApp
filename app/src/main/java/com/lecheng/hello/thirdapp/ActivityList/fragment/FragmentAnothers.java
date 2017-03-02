package com.lecheng.hello.thirdapp.ActivityList.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecheng.hello.thirdapp.R;

/**
 * Created by Cheng on 2015/7/25.
 */
public class FragmentAnothers extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentanothers = inflater.inflate(R.layout.fragment014_3,container,false);

        fragmentanothers.findViewById(R.id.fragmentback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return fragmentanothers;
    }
}
