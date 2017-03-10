package com.lecheng.hello.thirdapp.ActivityList;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.lecheng.hello.thirdapp.MainMenu;
import com.lecheng.hello.thirdapp.Adapter.MyVPAdapter;
import com.lecheng.hello.thirdapp.R;

import java.util.ArrayList;
import java.util.List;

public class Aty010ViewPager extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager VP;            //VP界面
    private MyVPAdapter VPA;         //适配器
    private List<View> views;        //内容
    private ImageView[] dots;        //导航点的集合
    private int[] ids = {R.id.ivp1, R.id.ivp2, R.id.ivp3};    //导航点id的集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty010);
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.vp1, null));
        views.add(inflater.inflate(R.layout.vp2, null));
        views.add(inflater.inflate(R.layout.vp3, null));

        VPA = new MyVPAdapter(views, this);
        VP = (ViewPager) findViewById(R.id.vp);
        VP.setAdapter(VPA);

        views.get(2).findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Aty010ViewPager.this, MainMenu.class));
                finish();
            }
        });

        VP.setOnPageChangeListener(this);

        setdots();
    }

    private void setdots() {
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < ids.length; i++) {
            if (position == i) {
                dots[i].setImageResource(R.drawable.util_point_selected);
            } else {
                dots[i].setImageResource(R.drawable.util_point);
            }
        }
    }
}
