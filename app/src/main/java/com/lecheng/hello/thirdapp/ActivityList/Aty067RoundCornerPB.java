package com.lecheng.hello.thirdapp.ActivityList;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.lecheng.hello.thirdapp.R;

//https://github.com/akexorcist/Android-RoundCornerProgressBar
public class Aty067RoundCornerPB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty067_roundcornerpb);

        RoundCornerProgressBar progress1 = (RoundCornerProgressBar) findViewById(R.id.progress_1);
        progress1.setProgressColor(Color.parseColor("#ed3b27"));
        progress1.setProgressBackgroundColor(Color.parseColor("#808080"));
        progress1.setMax(70);
        progress1.setProgress(15);

        int progressColor1 = progress1.getProgressColor();
        int backgroundColor1 = progress1.getProgressBackgroundColor();
        float max1 = progress1.getMax();
//        int progress1 = progress1.getProgress();

        RoundCornerProgressBar progress2 = (RoundCornerProgressBar) findViewById(R.id.progress_1);
        progress2.setProgressColor(Color.parseColor("#56d2c2"));
        progress2.setProgressBackgroundColor(Color.parseColor("#757575"));
//        progress2.setIconBackgroundColor(Color.parseColor("#38c0ae"));
        progress2.setMax(550);
        progress2.setProgress(147);
//        progress2.setIconImageResource(imageResource);

        int progressColor2 = progress2.getProgressColor();
        int backgroundColor2 = progress2.getProgressBackgroundColor();
//        int headerColor2 = progress2.getColorIconBackground();
        float max2 = progress2.getMax();
//        int progress2 = progress2.getProgress();
    }
}
