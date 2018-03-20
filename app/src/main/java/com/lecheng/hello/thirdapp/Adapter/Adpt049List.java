package com.lecheng.hello.thirdapp.Adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Bean.Gson.Bean049;
import com.lecheng.hello.thirdapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Cheng on 2016/8/9.
 */

public class Adpt049List extends BaseAdapter {
    private Context context;

    private ArrayList<Bean049> listb = new ArrayList<Bean049>();
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private Bean049 bean049;

    class ViewHolder {                                                  //把控件放在vh内部
        TextView tv1, tv2, title1, title2, title3, st1, st2, st3;
        TextView tv3;
        ImageView iv1, iv2, iv3;
        ListView lv1;
        LinearLayout ll1;

    }

    public Adpt049List(Context context, Bean049 bean049) {
        this.context = context;
        this.bean049 = bean049;
    }


    @Override
    public int getCount() {
        return bean049.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return bean049.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;                                           //局部类声明
        return myAdapterTypeSelector(vh, position, convertView, parent);
    }

    private View myAdapterTypeSelector(ViewHolder vh, int position, View convertView, ViewGroup parent) {    //自定义列表样式选择器
        if (convertView == null) {                                      //只有在没有convertview的时候
            convertView = View.inflate(context, R.layout.item049_metro, null); //找到布局
            vh = new ViewHolder();                                      //实例化vh
            //找到控件
            vh.tv1 = (TextView) convertView.findViewById(R.id.tv1);
            vh.tv2 = (TextView) convertView.findViewById(R.id.tv2);
            vh.ll1 = (LinearLayout) convertView.findViewById(R.id.ll049);
            convertView.setTag(vh);                                     //设置标题
        } else {
            vh = (ViewHolder) convertView.getTag();                     //当存在convertview时候直接获取tag
        }
        //在这里控件任意设定
        vh.tv1.setText(bean049.getList().get(position).getTitle());
        vh.tv2.setText(bean049.getList().get(position).getTime());

        vh.ll1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final TranslateAnimation ta2 = new TranslateAnimation(4, 4, 4, 4);
                ta2.setDuration(1000);
                v.startAnimation(ta2);
                return false;
            }
        });
        return convertView;
    }


}
