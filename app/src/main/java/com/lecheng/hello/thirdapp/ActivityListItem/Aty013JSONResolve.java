package com.lecheng.hello.thirdapp.ActivityListItem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Aty013JSONResolve extends AppCompatActivity {
    private String s = "{"+"\"future\":{\n" +
            "\t\t\t\"day_20150901\":{\n" +
            "\t\t\t\t\"temperature\":\"18℃~25℃\",\n" +
            "\t\t\t\t\"weather\":\"阵雨转阴\",\n" +
            "\t\t\t\t\"weather_id\":{\n" +
            "\t\t\t\t\t\"fa\":\"03\",\n" +
            "\t\t\t\t\t\"fb\":\"02\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"wind\":\"微风\",\n" +
            "\t\t\t\t\"week\":\"星期二\",\n" +
            "\t\t\t\t\"date\":\"20150901\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"day_20150902\":{\n" +
            "\t\t\t\t\"temperature\":\"18℃~29℃\",\n" +
            "\t\t\t\t\"weather\":\"多云\",\n" +
            "\t\t\t\t\"weather_id\":{\n" +
            "\t\t\t\t\t\"fa\":\"01\",\n" +
            "\t\t\t\t\t\"fb\":\"01\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"wind\":\"微风\",\n" +
            "\t\t\t\t\"week\":\"星期三\",\n" +
            "\t\t\t\t\"date\":\"20150902\"\n" +
            "\t\t\t}"+"}";
    private TextView tvjson,tvtem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity013);
        tvjson = (TextView) findViewById(R.id.tvjson);
        tvjson.setText(s);
        tvtem = (TextView) findViewById(R.id.tvtem);
        try {
            JSONObject j_all = new JSONObject(s);
            String s_weather = j_all.getString("day_20150901");
            JSONObject j =new JSONObject(s_weather);
            System.out.println(j);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
