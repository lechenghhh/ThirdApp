package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lecheng.hello.thirdapp.Adapter.ListViewUnitAdpt.LvUnitAdpt;
import com.lecheng.hello.thirdapp.Adapter.ListViewUnitAdpt.ViewHolder;
import com.lecheng.hello.thirdapp.Bean.Json.Bean039Weather;
import com.lecheng.hello.thirdapp.Interface.HttpVolley;
import com.lecheng.hello.thirdapp.Interface.OnResponseListener;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MySP;
import com.lecheng.hello.thirdapp.Utils.MyUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty039NewWeather extends Activity implements OnResponseListener {
    @Bind(R.id.aty40_iv_bg)
    ImageView aty40IvBg;
    @Bind(R.id.aty40_tv_city)
    TextView aty40TvCity;
    @Bind(R.id.aty40_tv1)
    TextView aty40Tv1;
    @Bind(R.id.aty40_tv2)
    TextView aty40Tv2;
    @Bind(R.id.aty40_lv)
    ListView aty40Lv;
    @Bind(R.id.aty40_lv2)
    ListView aty40Lv2;
    @Bind(R.id.aty40_sync)
    ScrollView aty40Sync;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private String[] cityList = {"北京", "上海", "广州", "深圳", "天津", "成都",
            "武汉", "重庆", "杭州", "沈阳", "厦门", "青岛", "福州"};
    private String bgUrl = "http://appserver.m.bing.net/BackgroundImageService/TodayImageService.svc/GetTodayImage?dateOffset=0&urlEncodeHeaders=true&osName=windowsPhone&osVersion=8.10&orientation=480x800&deviceName=WP8&mkt=en-US";

    private LayoutAnimationController lac;      //动画
    private ScaleAnimation sa;                  //动画

    private HttpVolley myAsyncHttp = new HttpVolley();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty039);
        ButterKnife.bind(this);
        imageLoader.displayImage(bgUrl, aty40IvBg);
//        myAsyncHttp.http047Get(this, "http://wthrcdn.etouch.cn/weather_mini?city=" +
//                MySP.loadData(getApplicationContext(), "city", "福州"), this);
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("city", MySP.loadData(getApplicationContext(), "city", "福州") + "");
        myAsyncHttp.http047Post(this, "http://wthrcdn.etouch.cn/weather_mini", hm, this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getApplicationContext(), android.R.layout.simple_list_item_1);
        aty40Lv2.setAdapter(adapter);
        for (int i = 0; i < cityList.length; i++) {
            adapter.add(cityList[i]);
        }
        MyUtils.setListViewHeightBasedOnChildren(aty40Lv2);
        aty40Lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MySP.saveData(getApplicationContext(), "city", cityList[position]);
                aty40Lv.setVisibility(View.VISIBLE);
                aty40Lv2.setVisibility(View.GONE);
                myAsyncHttp.http047Get(Aty039NewWeather.this,
                        "http://wthrcdn.etouch.cn/weather_mini?city=" +
                                MySP.loadData(getApplicationContext(), "city", "福州"),
                        Aty039NewWeather.this);
            }
        });

        sa = new ScaleAnimation(0, 1, 0, 1, 0, 1);
        sa.setDuration(1000);
        lac = new LayoutAnimationController(sa, 0.2f);
        aty40Lv.setLayoutAnimation(lac);
    }


    @Override
    public void onSuccess(String strJson) {
        try {
//            final Bean039Weather bean = GsonUtil.GsonToBean(strJson, Bean039Weather.class);
            final Bean039Weather bean = JSON.parseObject(strJson, Bean039Weather.class);
            //呈现到列表上
            aty40TvCity.setText(bean.getData().getCity());
            aty40Tv1.setText(bean.getData().getWendu() + "摄氏度");
            aty40Tv2.setText(bean.getData().getGanmao());
            aty40Lv.setAdapter(new LvUnitAdpt<Bean039Weather.DataBean.ForecastBean>(getApplicationContext(), bean.getData().getForecast(), R.layout.item040_weather) {
                @Override
                public void convert(ViewHolder helper, Bean039Weather.DataBean.ForecastBean item) {
                    helper.setText(R.id.listcell40_type, item.getType());
                    helper.setText(R.id.listcell40_fengxiang, item.getFengxiang());
                    helper.setText(R.id.listcell40_fengli, item.getFengli());
                    helper.setText(R.id.listcell40_high, "最" + item.getHigh() + "度");
                    helper.setText(R.id.listcell40_low, "最" + item.getLow() + "度");
                    helper.setText(R.id.listcell40_date, item.getDate());
                }
            });
            MyUtils.setListViewHeightBasedOnChildren(aty40Lv);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @OnClick(R.id.aty40_ll)
    public void onClick() {
        aty40Lv.setVisibility(View.GONE);
        aty40Lv2.setVisibility(View.VISIBLE);
    }
}
