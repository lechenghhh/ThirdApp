package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.flowlayout.FlowLayout;
import com.lecheng.hello.thirdapp.Widgets.flowlayout.TagAdapter;
import com.lecheng.hello.thirdapp.Widgets.flowlayout.TagFlowLayout;

import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*tagflowlayout
* 教程地址：http://blog.csdn.net/lmj623565791/article/details/48393217*/
public class Aty054 extends ActionBarActivity {

    //    @Bind(R.id.tfl)
//    TagFlowLayout tfl;
    PullToRefreshListView pullToRefreshListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty054);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                break;
            case R.id.btn2:
                break;
        }
    }
}
