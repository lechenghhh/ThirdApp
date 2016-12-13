package com.lecheng.hello.thirdapp.ActivityListItem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.Adapter.Adapter16;
import com.lecheng.hello.thirdapp.R;

public class Aty016TestListView extends AppCompatActivity {
    Adapter16 adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view16);
        lv = (ListView) findViewById(R.id.lv_16);
        adapter = new Adapter16(this, 10);
        lv.setAdapter(adapter);
        findViewById(R.id.btn_round).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Aty016TestListView.this, "简易版FAB", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
