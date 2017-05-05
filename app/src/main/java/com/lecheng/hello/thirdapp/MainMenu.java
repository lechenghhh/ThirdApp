package com.lecheng.hello.thirdapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.Utils.MyToast;

public class MainMenu extends ListActivity {
    //命名规范 前置Atyxxx  如果是实验模块功能请添加Test，如果是借鉴请添加Demo----16.9.6.
    private String classes[] = {
            "https://github.com/lechenghhh/ThirdApp",
            "-------------Hello World !-------------",
            "Aty001Service", "Aty002ServiceAnother", "Aty003EveryLayout",
            "Aty004Animation", "Aty005MulTouch", "Aty006SQLite", "Aty007SurfaceView",
            "Aty008Player", "Aty009View", "Aty010ViewPager",
            "-------------------------------------",
            "Aty011HttpAsyncTask", "Aty012TestFileExplore", "Aty013JSONResolve",
            "Aty014Fragment", "Aty015Volley", "Aty016TestListView",
            "Aty017TestLvAndCheckBox", "Aty018MyBrowser",
            "Aty019MVC", "Aty020FragmentTabHost",
            "-------------------------------------",
            "Aty021ButterKnife", "Aty022UniversalImgLoader", "Aty023UploadPic",
            "Aty024ShareToFriends", "Aty025TestBackTop", "Aty026XListView",
            "Aty027ViewFlipper", "Aty028PicView", "Aty029AMap", "Aty030VideoView",
            "-------------------------------------",
            "Aty031Blur", "Aty032TestBanner", "Aty033UploadFile",
            "Aty034Tuner", "Aty035Contact", "Aty036Calculator", "Aty037Weather",
            "Aty038Game2048", "Aty039NewWeather", "Aty040NetNote",
            "-------------------------------------",
            "Aty041MVP_View", "Aty042SerDownLoad", "Aty043WeiXPay",
            "Aty044AliPay", "Aty045BaiduPush", "Aty046MsgVerify",
            "Aty047ProgressDialog", "Aty048TimePiker", "Aty049MetroUI", "Aty050CrashTest",
            "-------------------------------------",
            "Aty051PopWindow", "Aty052CardView", "Aty053Coordinator",
            "Aty054PullToRefresh", "Aty055View", "Aty056",
            "Aty057", "Aty058", "Aty059", "Aty060",
    };

    private LayoutAnimationController lac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>
                (MainMenu.this, android.R.layout.simple_list_item_1, classes));

        Animation animation = new AlphaAnimation(0, 1);     //AlphaAnimation 控制渐变透明的动画效果
        animation.setDuration(100);                         //动画时间毫秒数
        LayoutAnimationController controller = new LayoutAnimationController(animation, 1);
        getListView().setLayoutAnimation(controller);
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

    private long lct = 0;                       //后退事件处理

    @Override
    public void onBackPressed() {
        if (lct <= 0) {
            new MyToast(this, "再按一次退出程序", 1);
            lct = System.currentTimeMillis();
        } else {
            long cct = System.currentTimeMillis();
            if (cct - lct < 1200) {
                finish();
            } else {
                new MyToast(this, "再按一次吧", 1);
                lct = cct;
            }
        }
    }

}