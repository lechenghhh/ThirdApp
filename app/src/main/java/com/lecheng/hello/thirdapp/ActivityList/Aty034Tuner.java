package com.lecheng.hello.thirdapp.ActivityList;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.lecheng.hello.thirdapp.ActivityList.fragment.Frag034Metronome;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.FragmentUtil;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

public class Aty034Tuner extends AppCompatActivity implements View.OnClickListener {
    private Switch s;
    private static MediaPlayer mp;
    private static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty034);
        mp = MediaPlayer.create(this, R.raw.sound_e2);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btnmet).setOnClickListener(this);
        s = (Switch) findViewById(R.id.switch1);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    i = 1;
                } else {
                    i = 0;
                    mp.stop();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                mp.stop();
                mp = MediaPlayer.create(this, R.raw.sound_e2);
                CyclePlay();
                break;
            case R.id.btn2:
                mp.stop();
                mp = MediaPlayer.create(this, R.raw.sound_b);
                CyclePlay();
                break;
            case R.id.btn3:
                mp.stop();
                mp = MediaPlayer.create(this, R.raw.sound_g);
                CyclePlay();
                break;
            case R.id.btn4:
                mp.stop();
                mp = MediaPlayer.create(this, R.raw.sound_d);
                CyclePlay();
                break;
            case R.id.btn5:
                mp.stop();
                mp = MediaPlayer.create(this, R.raw.sound_a);
                CyclePlay();
                break;
            case R.id.btn6:
                mp.stop();
                mp = MediaPlayer.create(this, R.raw.sound_e);
                CyclePlay();
                break;
            case R.id.btnmet:
                new MyToast(this, "hhaha", 1);
                FragmentUtil.startFragment(this, new Frag034Metronome());
                break;
        }
    }

    public void CyclePlay() {
        if (i == 1) {
            mp.setLooping(true);
        } else {
            mp.setLooping(false);
        }
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();
    }
}
