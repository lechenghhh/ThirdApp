package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lecheng.hello.thirdapp.Adapter.Common.UnityAdpt;
import com.lecheng.hello.thirdapp.Adapter.Common.ViewHolder;
import com.lecheng.hello.thirdapp.R;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*tagflowlayout
* 教程地址：http://blog.csdn.net/lmj623565791/article/details/48393217*/

public class Aty054 extends ActionBarActivity {
//    @Bind(R.id.ptrLv1)
    PullToRefreshListView ptrLv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty054_lib);
        String[] arrayGv = {"帐户名细", "个人资料管理", "银行卡管理",};
        List<String> list1 = Arrays.asList(arrayGv);
        ptrLv1= (PullToRefreshListView) findViewById(R.id.ptrLv1);
        ptrLv1.setAdapter(new UnityAdpt<String>(getApplicationContext(), list1, R.layout.cell049_metro) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv1, item);
            }
        });
    }

//    @OnClick({R.id.btn1, R.id.btn2})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn1:
//                break;
//            case R.id.btn2:
//                break;
//        }
//    }
}
