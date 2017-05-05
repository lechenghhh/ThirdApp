package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lecheng.hello.thirdapp.Adapter.Common.UnityAdpt;
import com.lecheng.hello.thirdapp.Adapter.Common.ViewHolder;
import com.lecheng.hello.thirdapp.R;

import java.util.Arrays;
import java.util.LinkedList;

import android.os.AsyncTask;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lecheng.hello.thirdapp.Utils.MyToast;

/*tagflowlayout
* tagflow教程地址：http://blog.csdn.net/lmj623565791/article/details/48393217
* http://blog.csdn.net/hantangsongming/article/details/42490277*/
public class Aty054PullToRefresh extends ActionBarActivity {
    private String[] mStrings = {"John", "Michelle", "Amy", "Kim", "Mary",
            "David", "Sunny", "James", "Maria", "Michael", "Sarah", "Robert",
//            "Lily", "William", "Jessica", "Paul", "Crystal", "Peter",
//            "Jennifer", "George", "Rachel", "Thomas", "Lisa", "Daniel", "Elizabeth",
            "Kevin"};
    private PullToRefreshListView ptrLv1;
    private LinkedList<String> mListItems;
    private ArrayAdapter<String> mAdapter;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty054);

        // Set a listener to be invoked when the list should be refreshed.
        ptrLv1 = (PullToRefreshListView) findViewById(R.id.ptrLv1);
//        ptrLv1.setRefreshing();//自动刷新一次
        ptrLv1.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                /*// 更新刷新的时间
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);*/
                // 做更新的操作
                new MyToast(Aty054PullToRefresh.this, "正在下拉刷新", 0);
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new MyToast(Aty054PullToRefresh.this, "正在上拉加载更多", 0);
                //线程
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void v) {
                        ptrLv1.onRefreshComplete();
                    }
                }.execute();
            }
        });
//        ptrLv1.setOnRefreshListener(new OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                /*// 更新刷新的时间
//                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
//                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
//                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);*/
//                // 做更新的操作
//                new MyToast(Aty054PullToRefresh.this, "正在刷新", 0);
//                new GetDataTask().execute();
//            }
//        });

        ListView actualListView = ptrLv1.getRefreshableView();
        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
        actualListView.setAdapter(mAdapter);
      /*  ptrLv1.setAdapter(new UnityAdpt<String>(getApplicationContext(), mListItems, R.layout.cell049_metro) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv1, item);
            }
        });*/
        ptrLv1.setMode(PullToRefreshBase.Mode.BOTH);
    }

    //下拉刷新线程
    private class GetDataTask extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {
            mListItems.addFirst("No." + i + " Added after refresh... ");
            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            ptrLv1.onRefreshComplete();
            i++;
            super.onPostExecute(result);
        }
    }
}