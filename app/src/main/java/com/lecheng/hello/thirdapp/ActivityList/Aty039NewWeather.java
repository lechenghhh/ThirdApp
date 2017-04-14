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

import com.lecheng.hello.thirdapp.Adapter.Common.UnityAdpt;
import com.lecheng.hello.thirdapp.Adapter.Common.ViewHolder;
import com.lecheng.hello.thirdapp.Bean.Bean039Weather;
import com.lecheng.hello.thirdapp.Http.Http047;
import com.lecheng.hello.thirdapp.Interface.I047Listener;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.GsonUtil;
import com.lecheng.hello.thirdapp.Utils.MySharedPreferences;
import com.lecheng.hello.thirdapp.Utils.MyUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty039NewWeather extends Activity implements I047Listener {
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
    private TextView tv, tv1, tv2, tv3;
    private ListView lv1, lv2;
    private ImageView iv1;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private String[] cityList = {"北京", "上海", "广州", "深圳", "天津", "成都",
            "武汉", "重庆", "杭州", "沈阳", "厦门", "青岛", "福州"};
    private String bgUrl = "http://appserver.m.bing.net/BackgroundImageService/TodayImageService.svc/GetTodayImage?dateOffset=0&urlEncodeHeaders=true&osName=windowsPhone&osVersion=8.10&orientation=480x800&deviceName=WP8&mkt=en-US";

    private LayoutAnimationController lac;      //动画
    private ScaleAnimation sa;                  //动画

    private Http047 http047 = new Http047();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty039);
        ButterKnife.bind(this);
        imageLoader.displayImage(bgUrl, iv1);
        http047.http047Get(this, "http://wthrcdn.etouch.cn/weather_mini?city=" +
                MySharedPreferences.loadData(getApplicationContext(), "city", "福州"), this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getApplicationContext(), android.R.layout.simple_list_item_1);
        lv2.setAdapter(adapter);
        for (int i = 0; i < cityList.length; i++) {
            adapter.add(cityList[i]);
        }
        MyUtils.setListViewHeightBasedOnChildren(lv2);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MySharedPreferences.saveData(getApplicationContext(), "city", cityList[position]);
                lv1.setVisibility(View.VISIBLE);
                lv2.setVisibility(View.GONE);
                http047.http047Get(Aty039NewWeather.this,
                        "http://wthrcdn.etouch.cn/weather_mini?city=" +
                                MySharedPreferences.loadData(getApplicationContext(), "city", "福州"),
                        Aty039NewWeather.this);
            }
        });

        sa = new ScaleAnimation(0, 1, 0, 1, 0, 1);
        sa.setDuration(1000);
        lac = new LayoutAnimationController(sa, 0.2f);
        lv1.setLayoutAnimation(lac);
    }


    @Override
    public void onSuccess(String strJson) {
        final Bean039Weather bean = GsonUtil.GsonToBean(strJson, Bean039Weather.class);
//        //呈现到列表上
        tv.setText(bean.getData().getCity());
        tv1.setText(bean.getData().getWendu() + "摄氏度");
        tv2.setText(bean.getData().getGanmao());
        lv1.setAdapter(new UnityAdpt<Bean039Weather.DataBean.ForecastBean>(getApplicationContext(), bean.getData().getForecast(), R.layout.cell040_weather) {
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
        MyUtils.setListViewHeightBasedOnChildren(lv1);

    }

    @OnClick(R.id.aty40_ll)
    public void onClick() {
        lv1.setVisibility(View.GONE);
        lv2.setVisibility(View.VISIBLE);
    }
}
