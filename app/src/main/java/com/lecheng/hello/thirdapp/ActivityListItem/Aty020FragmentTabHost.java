package com.lecheng.hello.thirdapp.ActivityListItem;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.fragment.Fragment2Fragment;
import com.lecheng.hello.thirdapp.fragment.FragmentAnothers;
import com.lecheng.hello.thirdapp.R;


/**
 * @author zqy
 */
public class Aty020FragmentTabHost extends FragmentActivity {
    /**
     * FragmentTabhost
     */
    private FragmentTabHost mTabHost;

    /**
     * 布局填充器
     */
    private LayoutInflater mLayoutInflater;

    /**
     * Fragment数组界面
     */
    private Class mFragmentArray[] = {Fragment2Fragment.class, Fragment2Fragment.class,
            FragmentAnothers.class, Fragment2Fragment.class, FragmentAnothers.class};
    /**
     * 存放图片数组
     */
    private int mImageArray[] = {R.drawable.ic_bag,
            R.drawable.ic_round, R.drawable.ic_round,
            R.drawable.ic_round, R.drawable.ic_round};

    /**
     * 选修卡文字
     */
    private String mTextArray[] = {"首页", "消息", "好友", "搜索", "更多"};

    /**
     *
     *
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity020);

        initView();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);

        // 找到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.style_btn_green);
        }
    }

    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.fragment_tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);

        return view;
    }

}