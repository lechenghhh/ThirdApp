package com.lecheng.hello.thirdapp.ActivityList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.lecheng.hello.thirdapp.Adapter.Adapter17;
import com.lecheng.hello.thirdapp.R;

public class Aty017TestLvAndCheckBox extends AppCompatActivity {

    private ListView listView;
    private Adapter17 adapter;
    private String[] beans = new String[]{"1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity017);

        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        Log.i("htp", "beans.size:" + beans.length);
        listView = (ListView) findViewById(R.id.lv_17);
        adapter = new Adapter17(Aty017TestLvAndCheckBox.this, beans);
        listView.setAdapter(adapter);
    }
}
