package com.lecheng.hello.thirdapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

public class AdptWeather extends BaseAdapter{
	private Context c;
	private String fweather[],ftem[],fdate[],fweek[];
	private TextView tvfutruetem,tvfutrueweather,tvfutrueweek,tvfutruedate;
	private ImageView img_weather;
	private LinearLayout l;
	public AdptWeather(String[] fweather,String[] ftem,String[] fdate,String[] fweek,Context c){
		this.fweather = fweather;
		this.ftem = ftem;
		this.fdate = fdate;
		this.fweek = fweek;
		this.c = c;
	}
	
	@Override
	public int getCount() {
		return 7;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater=LayoutInflater.from(c);
		l = (LinearLayout) inflater.inflate(R.layout.item037_weather, null);
		tvfutrueweather = (TextView) l.findViewById(R.id.tvfutrueweather);
		tvfutruetem = (TextView) l.findViewById(R.id.tvfutruetem);
		tvfutrueweek = (TextView) l.findViewById(R.id.tvfutrueweek);
		tvfutruedate = (TextView) l.findViewById(R.id.tvfutruedate);
		img_weather = (ImageView) l.findViewById(R.id.img_weather);
		
		
		tvfutrueweather.setText(fweather[position]);
		tvfutruetem.setText(ftem[position]);
		tvfutrueweek.setText(fweek[position]);
		tvfutruedate.setText(fdate[position]);
		
		if (fweather[position].indexOf("雪") != -1) {
			img_weather.setBackgroundResource(R.drawable.ic_snow);
		} else if (fweather[position].indexOf("雨") != -1) {
			img_weather.setBackgroundResource(R.drawable.ic_rain);
		} else if (fweather[position].indexOf("阴") != -1) {
			img_weather.setBackgroundResource(R.drawable.ic_cloudy);
		} else if (fweather[position].indexOf("晴") != -1) {
			img_weather.setBackgroundResource(R.drawable.ic_sunny);
		} else if (fweather[position].indexOf("云") != -1) {
			img_weather.setBackgroundResource(R.drawable.ic_broken_sky);
		}
		
		return l;
	}

}
