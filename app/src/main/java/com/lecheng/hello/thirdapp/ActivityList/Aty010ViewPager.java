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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import java.util.ArrayList;
import java.util.List;

public class Aty010ViewPager extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager VP;            //VP界面
    private MyVPAdapter VPA;         //适配器
    private List<View> views;        //内容
    private ImageView[] dots;        //导航点的集合
    private int[] ids = {R.id.ivp1, R.id.ivp2, R.id.ivp3};    //导航点id的集合
    private ImageView iv, iv2, iv3;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty010);
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        iv = new ImageView(this);
        iv2 = new ImageView(this);
        iv3 = new ImageView(this);
        imageLoader.displayImage("drawable://" + R.drawable.bg_guide1, iv);
        imageLoader.displayImage("drawable://" + R.drawable.bg_guide2, iv2);
        imageLoader.displayImage("drawable://" + R.drawable.bg_guide3, iv3);
        views.add(iv);
        views.add(iv2);
        views.add(iv3);
        VPA = new MyVPAdapter(views, this);
        VP = (ViewPager) findViewById(R.id.vp);
        VP.setAdapter(VPA);
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
        /*switch (position) {
            case 0:
                iv.setBackgroundResource(R.drawable.bg_guide1);
                break;
            case 1:
                iv.setBackgroundResource(R.drawable.bg_guide2);
                break;
            case 2:
                iv.setBackgroundResource(R.drawable.bg_guide2);
                break;
        }*/
        for (int i = 0; i < ids.length; i++) {
            if (position == i) {
                dots[i].setImageResource(R.drawable.util_point_selected);
            } else {
                dots[i].setImageResource(R.drawable.util_point);
            }
        }
    }
}
