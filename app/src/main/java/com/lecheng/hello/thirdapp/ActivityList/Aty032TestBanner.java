package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Adapter.ImgCycViewPagerAdapter;
import com.lecheng.hello.thirdapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Aty032TestBanner extends AppCompatActivity {
    private LinearLayout ll;
    private ViewPager mVPlunbotu;
    private List<ImageView> lbtImages;
    private List<View> dots;
    private int currentItem;
    private ScheduledExecutorService scheduledExecutorService;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的url
    private String[] img_url = {
            "http://www.tctu.cn/Uploads/Seller/2017-01-24/58873cf689a67.png",
            "http://www.tctu.cn/Uploads/Seller/2017-01-24/58873e9a3ae6c.png",
            "http://www.tctu.cn/Uploads/Seller/2017-01-24/58873e1095bc5.png",
            "http://www.tctu.cn/Uploads/Seller/2016-11-08/5821708096a43.jpg",
            "http://www.tctu.cn/Uploads/Admin/2016-10-29/581450822d269.jpg"};
    //存放图片的标题
    private String[] titles = new String[]{
            "好得多",
            "扑树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送",
            "酷炫"
    };
    private TextView title;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty032);
        ll = (LinearLayout) findViewById(R.id.aty32_ll);

        showImgCycle();

    }

    private void showImgCycle() {
        mVPlunbotu = (ViewPager) findViewById(R.id.test8_vp);
        //显示的图片
        lbtImages = new ArrayList<ImageView>();
        for (int i = 0; i < img_url.length; i++) {
            ImageView imageView = new ImageView(this);
            imageLoader.displayImage(img_url[i], imageView);
            lbtImages.add(imageView);
        }
        //显示的小点
//        dots = new ArrayList<View>();
//        dots.add(findViewById(R.id.dot_0));
//        dots.add(findViewById(R.id.dot_1));
//        dots.add(findViewById(R.id.dot_2));
//        dots.add(findViewById(R.id.dot_3));
//        dots.add(findViewById(R.id.dot_4));
        //标题显示
//        title = (TextView) findViewById(R.id.test8_title);
//        title.setText(titles[0]);
        mVPlunbotu.setAdapter(new ImgCycViewPagerAdapter(getApplicationContext(), lbtImages));
        mVPlunbotu.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
//                title.setText(titles[position]);
//                dots.get(position).setBackgroundResource(R.color.colorblack);
//                dots.get(oldPosition).setBackgroundResource(R.color.colorGray);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    // 利用线程池定时执行动画轮播
    @Override
    protected void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(), 3, 3, TimeUnit.SECONDS);
    }

    private class ViewPageTask implements Runnable {
        @Override
        public void run() {
            currentItem = (currentItem + 1) % img_url.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    //接收子线程传递过来的数据
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mVPlunbotu.setCurrentItem(currentItem);
        }
    };

}
