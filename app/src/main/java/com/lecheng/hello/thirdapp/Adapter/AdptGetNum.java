package com.lecheng.hello.thirdapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Bean.PhoneInfo;
import com.lecheng.hello.thirdapp.R;

import java.util.List;

/**
 * Created by Cheng on 2017/1/18.
 */

public class AdptGetNum extends BaseAdapter {
    private Context context;
    private List<PhoneInfo> lists;

    public AdptGetNum(List<PhoneInfo> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {                                                  //把控件放在vh内部
        TextView tv1, tv2;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;                                           //局部类声明
        if (convertView == null) {                                      //只有在没有convertview的时候
            convertView = View.inflate(context, R.layout.cell035_call, null); //找到布局
            vh = new ViewHolder();                                      //实例化vh
            vh.tv1 = (TextView) convertView.findViewById(R.id.call35_name);    //找到控件
            vh.tv2 = (TextView) convertView.findViewById(R.id.call35_number);    //找到控件
            convertView.setTag(vh);                                     //设置标题
        } else {
            vh = (ViewHolder) convertView.getTag();                     //当存在convertview时候直接获取tag
        }
        //在这里控件任意设定
        vh.tv1.setText(lists.get(position).getName() + "");
        vh.tv2.setText(lists.get(position).getNumber() + "");

        return convertView;
    }

}
