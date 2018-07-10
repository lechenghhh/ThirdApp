package com.lecheng.hello.thirdapp.ActivityList.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecheng.hello.thirdapp.Bean.Bean068;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;
import com.lecheng.hello.thirdapp.databinding.Fragment068Binding;

//https://www.jianshu.com/p/bf1a4315e431
public class Frag068DataBinding extends Fragment {
    private Bean068 user = new Bean068();
    //        private ViewDataBinding atyBind;
    private Fragment068Binding atyBind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment068, container, false);
//        Fragment068Binding binding = Fragment068Binding.inflate(inflater, root, R.layout.fragment068, false);
        atyBind = DataBindingUtil.inflate(inflater, R.layout.fragment068, container, false);
        user.setAddress("仓山区阳光城新界");
        user.setAge(12);
        atyBind.btn1.setOnClickListener(view -> {
            user.setAge(22);
            new MyToast(getActivity(), user.getAge() + "", 1);
        });

        return atyBind.getRoot();
//        return root;
    }
}
