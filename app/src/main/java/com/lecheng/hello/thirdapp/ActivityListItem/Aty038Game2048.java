package com.lecheng.hello.thirdapp.ActivityListItem;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;


public class Aty038Game2048 extends Activity {

    private int score = 0;
    private TextView tvScore;
    private static Aty038Game2048 mainActivity = null;

    public Aty038Game2048() {
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity038);
        tvScore = (TextView) findViewById(R.id.tvScore);
        findViewById(R.id.tvstart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*在此处添加视图*/
            }
        });
    }

    public void clearScore() {
        score = 0;
    }

    public void showScore() {
        tvScore.setText(score + "");
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }

    public static Aty038Game2048 getMainActivity() {
        return mainActivity;
    }
}
