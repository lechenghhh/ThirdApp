package com.lecheng.hello.thirdapp.Adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Bean.PhoneInfo;
import com.lecheng.hello.thirdapp.R;

public class AdptGetNum extends BaseAdapter{
	private List<PhoneInfo>lists;
	private Context context;
	private LinearLayout layout;
	
	public AdptGetNum(List<PhoneInfo>lists,Context context){
		this.lists=lists;
		this.context=context;
	}

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int arg0) {
		return lists.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LayoutInflater inflater=LayoutInflater.from(context);
		layout=(LinearLayout) inflater.inflate(R.layout.cell_call35, null);
		TextView nametv=(TextView) layout.findViewById(R.id.name);
		TextView numbertv=(TextView) layout.findViewById(R.id.number);
		nametv.setText(lists.get(arg0).getName());
		numbertv.setText(lists.get(arg0).getNumber());
		return layout;
	}

}
