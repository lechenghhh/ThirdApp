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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.Adapter.Common.CommonAdapter;
import com.lecheng.hello.thirdapp.Adapter.Common.ViewHolder;
import com.lecheng.hello.thirdapp.Bean.Bean039Weather;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.GsonUtil;
import com.lecheng.hello.thirdapp.Utils.MyApplication;
import com.lecheng.hello.thirdapp.Utils.MySharedPreferences;
import com.lecheng.hello.thirdapp.Utils.MyToast;
import com.lecheng.hello.thirdapp.Utils.MyUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Aty039NewWeather extends Activity implements View.OnClickListener {
    private TextView tv, tv1, tv2, tv3;
    private ListView lv1, lv2;
    private ImageView iv1;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private String[] cityList = {"北京", "上海", "广州", "深圳", "天津", "成都",
            "武汉", "重庆", "杭州", "沈阳", "厦门", "青岛", "福州"};
    private String bgUrl = "http://appserver.m.bing.net/BackgroundImageService/TodayImageService.svc/GetTodayImage?dateOffset=0&urlEncodeHeaders=true&osName=windowsPhone&osVersion=8.10&orientation=480x800&deviceName=WP8&mkt=en-US";

    private LayoutAnimationController lac;      //动画
    private ScaleAnimation sa;                  //动画

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty039);
        tv = (TextView) findViewById(R.id.aty40_tv_city);
        tv1 = (TextView) findViewById(R.id.aty40_tv1);
        tv2 = (TextView) findViewById(R.id.aty40_tv2);
        tv3 = (TextView) findViewById(R.id.aty40_tv_city);
        iv1 = (ImageView) findViewById(R.id.aty40_iv_bg);
        imageLoader.displayImage(bgUrl, iv1);
        lv1 = (ListView) findViewById(R.id.aty40_lv);
        lv2 = (ListView) findViewById(R.id.aty40_lv2);
        findViewById(R.id.aty40_ll).setOnClickListener(this);
        volleyGet();

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
                volleyGet();
            }
        });

        sa = new ScaleAnimation(0, 1, 0, 1, 0, 1);
        sa.setDuration(1000);
        lac = new LayoutAnimationController(sa, 0.2f);
        lv1.setLayoutAnimation(lac);
    }

    private void volleyGet() {
        String url = "http://wthrcdn.etouch.cn/weather_mini?city=" +
                MySharedPreferences.loadData(getApplicationContext(), "city", "福州");
        System.out.println("city" + MySharedPreferences.loadData(getApplicationContext(), "city", "福州"));
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String subject) {
//                        new MyToast(getApplicationContext(), subject, 3000);
                        try {
                            resolveJson(MyUtils.encodeChange(subject));//编码转换
                        } catch (Exception e) {
                            new MyToast(getApplicationContext(), "数据加载错误！", 3000);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                    }
                });
        request.setTag("cancelGet");
        MyApplication.getHttpQue().add(request);
    }

    private void resolveJson(String strJson) {//解析json方法，并呈现
        final Bean039Weather bean = GsonUtil.GsonToBean(strJson, Bean039Weather.class);
//        //呈现到列表上

        tv.setText(bean.getData().getCity());
        tv1.setText(bean.getData().getWendu() + "摄氏度");
        tv2.setText(bean.getData().getGanmao());
        lv1.setAdapter(new CommonAdapter<Bean039Weather.DataBean.ForecastBean>(getApplicationContext(), bean.getData().getForecast(), R.layout.cell040_weather) {
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aty40_ll:
                lv1.setVisibility(View.GONE);
                lv2.setVisibility(View.VISIBLE);
                break;
        }
    }
}
