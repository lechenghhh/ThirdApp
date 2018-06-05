package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MySP;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty050CrashTest extends AppCompatActivity {
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty050);
        ButterKnife.bind(this);
        init();
        MySP.loadData(this, "Aty050CrashTest", 1);
    }

    private void init() {

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                System.out.println(str.equals("any string"));
                break;
            case R.id.btn2:
                int[] ia = {1, 1, 1, 1, 1, 1, 1};
                for (int i = 1; i < 10; i++)
                    System.out.println(ia[i] + "");
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
        }
    }
}
