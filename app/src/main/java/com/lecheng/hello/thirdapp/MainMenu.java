package com.lecheng.hello.thirdapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.Utils.MyToast;
import com.lecheng.hello.thirdapp.Utils.SystemInfoUtil;

import java.util.Timer;
import java.util.TimerTask;

public class MainMenu extends ListActivity {
    //命名规范 前置Atyxxx  如果是实验模块功能请添加Test，如果是借鉴请添加Demo----16.9.6.
    private String classes[] = {
            "------------Hello World !------------",
            "https://github.com/lechenghhh/ThirdApp",
            SystemInfoUtil.getCpuInfo(),
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
            "Aty054PullToRefresh", "Aty055View", "Aty056Bluetooth",
            "Aty057Chart", "Aty058Share", "Aty059WebViewPlus", "Aty060RxJava",
            "-------------------------------------",
            "Aty061Kotlin", "Aty062Text2Speech", "Aty063",
            "Aty064MyLoadLv", "Aty065CPBtn", "Aty066FancyBtn",
            "Aty067RoundCornerPB", "Aty068MVVM", "Aty069Suspension", "Aty070AutoClick",
            "-------------------------------------",
            "Aty071VolueEvent", "", "", "", "", "", "", "",
    };

    private LayoutAnimationController lac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(MainMenu.this, android.R.layout.simple_list_item_1, classes));

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
            new MyToast(this, "跳转失败", 1);
        }
    }

    private boolean mBackKeyPressed = false;

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            new MyToast(this, "再按一次退出程序", 1);
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {
            this.finish();
            System.exit(0);
        }
    }

}