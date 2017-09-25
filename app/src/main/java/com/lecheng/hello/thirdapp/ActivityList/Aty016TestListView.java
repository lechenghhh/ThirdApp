package com.lecheng.hello.thirdapp.ActivityList;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.lecheng.hello.thirdapp.Adapter.Adpt016;
import com.lecheng.hello.thirdapp.R;

public class Aty016TestListView extends AppCompatActivity {
    private Adpt016 adapter;
    private SwipeMenuListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty016);
        lv1 = (SwipeMenuListView) findViewById(R.id.lv_16);
        adapter = new Adpt016(this, 10);
        lv1.setAdapter(adapter);
        findViewById(R.id.btn_round).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Aty016TestListView.this, "简易版FAB", Toast.LENGTH_SHORT).show();
            }
        });
        lv1.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {//set SwipeListener
            @Override
            public void onSwipeStart(int position) {
            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {        // step 1.  MenuCreator
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());//create "delete" item
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));//set item background
                deleteItem.setWidth(dp2px(90));                // set item width
                deleteItem.setIcon(R.drawable.ic_delete);                // set a icon
                menu.addMenuItem(deleteItem);                // add to menu
            }
        };
        lv1.setMenuCreator(creator);        // set creator
        lv1.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {//index指的是每一行的第几个选项
                /*删除操作*/
                adapter.notifyDataSetChanged();
                lv1.invalidate();
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
