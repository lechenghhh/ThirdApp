package com.lecheng.hello.thirdapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

public class Adpt016 extends BaseAdapter {
    private Context context;
    private int i, count = 1;
    private LinearLayout ly;
    private TextView tv;
    private EditText et;

    public Adpt016(Context context, int i) {
        this.context = context;
        this.i = i;
    }

    @Override
    public int getCount() {
        return i;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ly = (LinearLayout) inflater.inflate(R.layout.cell016, null);
        tv = (TextView) ly.findViewById(R.id.tv_16);
        et = (EditText) ly.findViewById(R.id.et_16);
        tv.setText(count + "");
        count++;

        return ly;
    }

}
