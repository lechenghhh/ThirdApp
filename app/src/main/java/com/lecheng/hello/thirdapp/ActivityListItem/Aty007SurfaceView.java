package com.lecheng.hello.thirdapp.ActivityListItem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lecheng.hello.thirdapp.Widgets.MySurfaceView;
import com.lecheng.hello.thirdapp.R;

public class Aty007SurfaceView extends AppCompatActivity {
    private MySurfaceView mv ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable7);
        mv = (MySurfaceView) findViewById(R.id.MyView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawable7, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_bar_clear7:
                mv.clear();
                break;
            case R.id.action_bar_1:
                mv.setMyView(1);
                break;
            case R.id.action_bar_2:
                mv.setMyView(2);
                break;
            case R.id.action_bar_3:
                mv.setMyView(3);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
