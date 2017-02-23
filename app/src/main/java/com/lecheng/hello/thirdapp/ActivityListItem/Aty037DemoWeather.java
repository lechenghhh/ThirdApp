package com.lecheng.hello.thirdapp.ActivityListItem;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.ActivityListItem.OtherActivity.SetCity037;
import com.lecheng.hello.thirdapp.Adapter.AdptWeather;
import com.lecheng.hello.thirdapp.R;

public class Aty037DemoWeather extends Activity {
    private Button btnselectcity;
    private TextView tvcity, tvdate, tvtem, tvweather;
    private String fweather[] = new String[14], ftem[] = new String[14],
            fdate[] = new String[14], fweek[] = new String[14];
    HttpClient client;
    String s_cityweather;
    private ListView lv;
    private AdptWeather a;
    String intentcity;
    //	 = "北京"
    public static String urlEncode;
    //	= "%E5%8C%97%E4%BA%AC&"
    private static LinearLayout linearlayout1;
    private SharedPreferences sdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity037);


        client = new DefaultHttpClient();
        tvcity = (TextView) findViewById(R.id.tvcity);
        tvdate = (TextView) findViewById(R.id.tvdate);
        tvtem = (TextView) findViewById(R.id.tvtem);
        tvweather = (TextView) findViewById(R.id.tvweather);
        lv = (ListView) findViewById(R.id.lv1);
        linearlayout1 = (LinearLayout) findViewById(R.id.linearlayout1);
        btnselectcity = (Button) findViewById(R.id.btnselectcity);
    }

    private void loaddata() {
        Intent i1 = getIntent(); // 获取选择的城市
        if (i1.getStringExtra("city_list_item") != null) {
            intentcity = i1.getStringExtra("city_list_item");

            try { // 将城市中文转换为UTF-8
                urlEncode = URLEncoder.encode(intentcity, "UTF-8");
                System.out.println(urlEncode + "/n^_^_^_^_^_^_^_^_^");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        sdata = getSharedPreferences("city_utf", 0);
        SharedPreferences.Editor b = sdata.edit();
        b.putString("city_utf", urlEncode);
        b.commit();
    }

    public void selectcity(View s) {
        startActivity(new Intent(Aty037DemoWeather.this, SetCity037.class));
    }

    public void syncweather(View s) {                                                    //准备链接
        if (s_cityweather != null) {
            resolutionJSONandshow(s_cityweather);
        }
    }

    public void getcityweather(String url) {                                            //联网子线程
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String url = params[0];
                HttpGet get = new HttpGet(url);
                try {
                    HttpResponse r = client.execute(get);
                    String line = EntityUtils.toString(r.getEntity());
                    return line;
                } catch (ParseException | IOException e) {
                    System.out.println("出现异常");
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(String result) {
                s_cityweather = result;
            }

            ;
        }.execute(url);
    }

    public void resolutionJSONandshow(String s) {

        try {

            JSONObject j = new JSONObject(s);
            String s_result = j.getString("result");
            JSONObject j_result = new JSONObject(s_result);
            String s_today = j_result.getString("today");
            JSONObject j_today = new JSONObject(s_today);

            setweatherbackground(j_today.getString("weather"));
            tvdate.setText(j_today.getString("week") + "\n" + j_today.getString("date_y"));
            tvcity.setText(j_today.getString("city"));
            tvtem.setText(j_today.getString("temperature"));
            tvweather.setText(j_today.getString("weather"));


            String s_future = j_result.getString("future");
            JSONArray ja_future = new JSONArray(s_future);


            for (int i = 0; i < ja_future.length(); i++) {
                String s_array = ja_future.getString(i);
                JSONObject j_array = new JSONObject(s_array);
                ftem[i] = j_array.getString("temperature");
                fweather[i] = j_array.getString("weather");
                fweek[i] = j_array.getString("week");
                fdate[i] = j_array.getString("date");
            }

            a = new AdptWeather(fweather, ftem, fdate, fweek, this);
            lv.setAdapter(a);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setweatherbackground(String s_weather) { // 根据天气设置背景
        if (s_weather.indexOf("雪") != -1) {
            linearlayout1.setBackgroundResource(R.drawable.weather_snow);
        } else if (s_weather.indexOf("雨") != -1) {
            linearlayout1.setBackgroundResource(R.drawable.weather_rain);
        } else if (s_weather.indexOf("阴") != -1) {
            linearlayout1.setBackgroundResource(R.drawable.weather_cloudy);
        } else if (s_weather.indexOf("晴") != -1) {
            linearlayout1.setBackgroundResource(R.drawable.weather_sunny);
        } else if (s_weather.indexOf("云") != -1) {
            linearlayout1.setBackgroundResource(R.drawable.weather_broken_sky);
        }
    }

    @Override
    protected void onResume() {
        loaddata();
        sdata = getSharedPreferences("city_utf", 0);
        urlEncode = sdata.getString("city_utf", "%E5%85%B0%E5%B7%9E");
        getcityweather("http://v.juhe.cn/weather/index?format=2&cityname=" + urlEncode + "&key=f6a5074cf362053ef92951e43b75beea");
        super.onResume();
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}
