package com.lecheng.hello.thirdapp.ActivityListItem.OtherActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lecheng.hello.thirdapp.ActivityListItem.Aty037DemoWeather;
import com.lecheng.hello.thirdapp.R;

public class SetCity037 extends Activity {
	private String[] citylistarray = { "北京","上海","广州","深圳","天津","成都",
			"武汉","重庆","杭州","沈阳","厦门","青岛","福州" };
	private ArrayAdapter<String> arraA;
	private ListView citylistview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity037_1);
		citylistview = (ListView) findViewById(R.id.lv2);
		arraA = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, citylistarray);
		citylistview.setAdapter(arraA);
		citylistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println(citylistarray[position] + ">>>>>>>");
				Intent i2 = new Intent(SetCity037.this,Aty037DemoWeather.class);
				i2.putExtra("city_list_item", citylistarray[position]);
				startActivity(i2);
			}
		});
	}
	@Override
	protected void onPause() {
		finish();
		super.onPause();
	}
}
