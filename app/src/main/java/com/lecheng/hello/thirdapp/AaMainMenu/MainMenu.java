package com.lecheng.hello.thirdapp.AaMainMenu;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainMenu extends ListActivity {
    //命名规范 前置Atyxxx  如果是实验模块功能请添加Test，如果是借鉴请添加Demo----16.9.6.
    private String classes[] = {
            "-------------Hello World !-------------",
            "Aty001Service", "Aty002ServiceAnother", "Aty003EveryLayout",
            "Aty004Animation", "Aty005MulTouch", "Aty006SQLite", "Aty007SurfaceView",
            "Aty008TestTextViewCtrl", "Aty009View", "Aty010ViewPager",
            "-------------------------------------",
            "Aty011HttpAsyncTask", "Aty012TestFileExplore", "Aty013JSONResolve",
            "Aty014Fragment", "Aty015Volley", "Aty016TestListView",
            "Aty017TestLvAndCheckBox", "Aty018MyBrowser",
            "Aty019Demo", "Aty020FragmentTabHost",
            "-------------------------------------",
            "Aty021ButterKnife", "Aty022UniversalImgLoader", "Aty023UploadPic",
            "Aty024ShareToFriends", "Aty025TestBackTop", "Aty026XListView",
            "Aty027ViewFlipper", "Aty028PicView", "Aty029AMap", "Aty030VideoView",
            "-------------------------------------",
            "Aty031Blur", "Aty032TestBanner", "Aty033UploadFile",
            "Aty034Tuner", "Aty035Contact", "Aty036Calculator", "Aty037Weather",
            "Aty038Game2048", "Aty039NewWeather", "Aty040NetNote",
            "-------------------------------------",
            "Aty041MVP_View", "Aty042", "Aty043",
    };

    private LayoutAnimationController lac;
    private ScaleAnimation sa;                  //动画
    private long lct = 0;                       //后退事件处理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>
                (MainMenu.this, android.R.layout.simple_list_item_1, classes));

        sa = new ScaleAnimation(0, 1, 0, 1, 0, 1);
        sa.setDuration(1000);
        lac = new LayoutAnimationController(sa, 0.2f);
        getListView().setLayoutAnimation(lac);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        try {
            Class ourClass = Class.forName("com.lecheng.hello.thirdapp.ActivityList." + classes[position]);
            Intent ourIntent = new Intent(MainMenu.this, ourClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            Toast.makeText(MainMenu.this, "跳转失败\n" + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        if (lct <= 0) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            lct = System.currentTimeMillis();
        } else {
            long cct = System.currentTimeMillis();
            if (cct - lct < 1200) {
                finish();
            } else {
                Toast.makeText(this, "再按一次吧", Toast.LENGTH_SHORT).show();
                lct = cct;
            }
        }
    }

}