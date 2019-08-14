package com.lecheng.hello.thirdapp.ActivityList;


import android.app.Service;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.ActivityList.fragment.Frag034Metronome;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.FragmentUtil;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

public class Aty034Tuner extends AppCompatActivity implements View.OnClickListener {
    private Switch s;
    private TextView tvSpeed;
    private static MediaPlayer mp;
    private static int i = 0;
    private Vibrator mVibrator;  //声明一个振动器对象
    private long num = 1l, time = 0l;//bpm tap 变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty034);
        mp = MediaPlayer.create(this, R.raw.sound_e2);
        tvSpeed = (TextView) findViewById(R.id.tvSpeed);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btnmet).setOnClickListener(this);
        findViewById(R.id.btnTap).setOnClickListener(this);
        findViewById(R.id.btnTap).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                tvSpeed.setText("0");
                num = 1l;
                time = 0l;
                new MyToast(Aty034Tuner.this, "清空测速器", 1);
                return true;
            }
        });
        //想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
        mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
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
            case R.id.btnTap:
                if (num == 1l) {
                    time = System.currentTimeMillis();
                } else {
                    long subTime = (System.currentTimeMillis() - time) / (num - 1);
                    float pjTime = subTime;
                    float bpm = 60 * 1000 / pjTime;
                    tvSpeed.setText(bpm + " bpm " + num + "次");
                    System.out.println("34tuner " + subTime + "--" + pjTime + "--" + bpm);
                }
                num++;

                mVibrator.vibrate(new long[]{0, 50, 0, 0}, -1);//停止1秒，开启震动10秒，然后又停止1秒，又开启震动10秒，-1不重复.
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
        mVibrator.cancel();
    }
}
