package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Http.Http047;
import com.lecheng.hello.thirdapp.Interface.I047Listener;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;

//http://blog.csdn.net/xyz_lmn/article/details/48055919
//http://www.open-open.com/lib/view/open1474856702239.html
//Api地址：
//http://gank.io/api/search/query/listview/category/%E7%A6%8F%E5%88%A9/count/10/page/1
public class Aty053Coordinator extends ActionBarActivity implements I047Listener {
    private final String URL = "http://gank.io/api/search/query/listview/category/%E7%A6%8F%E5%88%A9/count/10/page/1";
    @Bind(R.id.tv1)
    TextView tv1;
    private String json = "\n" +
            "{\n" +
            "  \"count\": 10, \n" +
            "  \"error\": false, \n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"desc\": \"10.27\", \n" +
            "      \"ganhuo_id\": \"56cc6d1d421aa95caa70755f\", \n" +
            "      \"publishedAt\": \"2015-10-27T02:43:16.906000\", \n" +
            "      \"readability\": \"\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://ww2.sinaimg.cn/large/7a8aed7bjw1exfffnlf2gj20hq0qoju9.jpg\", \n" +
            "      \"who\": \"\\u5f20\\u6db5\\u5b87\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"desc\": \"5.20\\u3002\\n520\\u7231\\u4f60\\uff0c\\u5c31\\u7ed9\\u4f60\\u751c\\u751c\\u7684\\u7b11\\u3002\\u4eca\\u65e5\\u7279\\u63a8\\uff01~~\\uff08\\u3065\\uffe33\\uffe3\\uff09\\u3065\\u256d\\u2764\\uff5e\", \n" +
            "      \"ganhuo_id\": \"56cc6d1d421aa95caa7075c5\", \n" +
            "      \"publishedAt\": \"2015-05-21T10:05:06.527000\", \n" +
            "      \"readability\": \"\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://ww1.sinaimg.cn/large/7a8aed7bgw1esahpyv86sj20hs0qomzo.jpg\", \n" +
            "      \"who\": \"\\u5f20\\u6db5\\u5b87\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"desc\": \"6.10\\u2014\\u2014\\uff082\\uff09\", \n" +
            "      \"ganhuo_id\": \"56cc6d1d421aa95caa7076df\", \n" +
            "      \"publishedAt\": \"2015-06-10T04:12:03.656000\", \n" +
            "      \"readability\": \"\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://ww1.sinaimg.cn/large/7a8aed7bgw1esxxi1vbq0j20qo0hstcu.jpg\", \n" +
            "      \"who\": \"\\u5f20\\u6db5\\u5b87\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"desc\": \"6.10\\u2014\\u2014\\uff081\\uff09\", \n" +
            "      \"ganhuo_id\": \"56cc6d1d421aa95caa7076e1\", \n" +
            "      \"publishedAt\": \"2015-06-10T04:12:04.272000\", \n" +
            "      \"readability\": \"\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://ww1.sinaimg.cn/large/7a8aed7bgw1esxxiw20rej20qo0hstcp.jpg\", \n" +
            "      \"who\": \"\\u5f20\\u6db5\\u5b87\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"desc\": \"6.18\", \n" +
            "      \"ganhuo_id\": \"56cc6d1d421aa95caa7076f8\", \n" +
            "      \"publishedAt\": \"2015-06-18T03:50:59.419000\", \n" +
            "      \"readability\": \"\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://ww1.sinaimg.cn/large/7a8aed7bgw1et80fw2p80j20qo0hsdj1.jpg\", \n" +
            "      \"who\": \"\\u5f20\\u6db5\\u5b87\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"desc\": \"7.17\", \n" +
            "      \"ganhuo_id\": \"56cc6d1d421aa95caa707701\", \n" +
            "      \"publishedAt\": \"2015-07-17T03:43:22.394000\", \n" +
            "      \"readability\": \"\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://ww3.sinaimg.cn/large/7a8aed7bgw1eu5jpf3lamj20f00mitck.jpg\", \n" +
            "      \"who\": \"\\u5f20\\u6db5\\u5b87\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"desc\": \"5.19\\n\", \n" +
            "      \"ganhuo_id\": \"56cc6d1d421aa95caa707751\", \n" +
            "      \"publishedAt\": \"2015-05-20T03:57:51.477000\", \n" +
            "      \"readability\": \"\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://ww2.sinaimg.cn/large/7a8aed7bgw1es8c7ucr0rj20hs0qowhl.jpg\", \n" +
            "      \"who\": \"\\u5f20\\u6db5\\u5b87\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"desc\": \"7.3\", \n" +
            "      \"ganhuo_id\": \"56cc6d1d421aa95caa70774e\", \n" +
            "      \"publishedAt\": \"2015-07-03T04:12:02.419000\", \n" +
            "      \"readability\": \"\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://ww1.sinaimg.cn/large/7a8aed7bgw1etpfol394kj20qo0hsdiw.jpg\", \n" +
            "      \"who\": \"\\u5f20\\u6db5\\u5b87\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"desc\": \"8.18\\u2014\\u2014\\uff081\\uff09\", \n" +
            "      \"ganhuo_id\": \"56cc6d1d421aa95caa70779f\", \n" +
            "      \"publishedAt\": \"2015-08-18T03:58:47.771000\", \n" +
            "      \"readability\": \"\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://ww1.sinaimg.cn/large/7a8aed7bgw1ev6jgvbt8ij20qo0hrdil.jpg\", \n" +
            "      \"who\": \"\\u5f20\\u6db5\\u5b87\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"desc\": \"8.18\\u2014\\u2014\\uff082\\uff09\", \n" +
            "      \"ganhuo_id\": \"56cc6d1d421aa95caa7077a0\", \n" +
            "      \"publishedAt\": \"2015-08-19T03:56:32.545000\", \n" +
            "      \"readability\": \"\", \n" +
            "      \"type\": \"\\u798f\\u5229\", \n" +
            "      \"url\": \"http://ww1.sinaimg.cn/large/7a8aed7bgw1ev6jh1hbsgj20hr0qoq5s.jpg\", \n" +
            "      \"who\": \"\\u5f20\\u6db5\\u5b87\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty053);
        ButterKnife.bind(this);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyToast(Aty053Coordinator.this, "HelloWorld!", 1);
            }
        });
        tv1.setText(json);


//        Bean053FethG bean = GsonUtil.GsonToBean(json, Bean053FethG.class);
//        lv.setAdapter(new UnityAdpt<Bean053FethG.ResultsBean>(this, bean.getResults(), R.layout.cell053_fethg) {
//            @Override
//            public void convert(ViewHolder helper, Bean053FethG.ResultsBean item) {
//                helper.setImageByUrl(R.id.iv, item.getUrl());
//                helper.setText(R.id.tv1, item.getWho());
//                helper.setText(R.id.tv2, item.getPublishedAt());
//            }
//        });
        Http047 http047 = new Http047();
        http047.http047Get(this, URL, this);
    }

    @Override
    public void onSuccess(String strJson) {
        tv1.setText(strJson);
    }
}
