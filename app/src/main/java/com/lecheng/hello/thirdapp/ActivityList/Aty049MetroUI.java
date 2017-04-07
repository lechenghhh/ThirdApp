package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.lecheng.hello.thirdapp.ActivityList.fragment.Frag049;
import com.lecheng.hello.thirdapp.Adapter.Adpt049Frag;
import com.lecheng.hello.thirdapp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Aty049MetroUI extends AppCompatActivity {

    @Bind(R.id.vp1)
    ViewPager vp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty049);
        ButterKnife.bind(this);//绑定
        ArrayList<Fragment> list = new ArrayList<Fragment>();
        list.add(new Frag049("war"));
        list.add(new Frag049("sport"));
        list.add(new Frag049("tech"));
        list.add(new Frag049("edu"));
        list.add(new Frag049("ent"));
        list.add(new Frag049("money"));
        list.add(new Frag049("lady"));
        list.add(new Frag049("travel"));
        FragmentManager manager = getSupportFragmentManager();
        Adpt049Frag adapter049 = new Adpt049Frag(manager, list);
        vp1.setAdapter(adapter049);



    }
}
