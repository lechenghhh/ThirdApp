package com.lecheng.hello.thirdapp.ActivityList.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class Frag034Metronome extends Fragment {
    Button button_start;
    Button button_stop;
    EditText edit_sudu;
    EditText edit_paishu;
    TextView result;
    SeekBar seekbar;
    Handler handler;
    Timer mytimer;

    public float tempo;
    public int section;
    public int pp;
    private SoundPool sndHigh;
    private SoundPool sndLow;
    private int hitOfHigh;
    private int hitOfLow;

    //声音控制
    private AudioManager audioManager;
    //声音变量
    private int volume = 0;
    //声音模式
    //private int mode;
    //是否有声音
    private int flag = 1;

    //内部类
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag034_met, container, false);
        pp = 1;

        final EditText edit_sudu = (EditText) root.findViewById(R.id.edit_sudu);
        final EditText edit_paishu = (EditText) root.findViewById(R.id.edit_paishu);
        final TextView result = (TextView) root.findViewById(R.id.result);

        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        result.setText(String.valueOf(pp));
                        if (pp == 1)
                            // play (int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
                            //播放音频，可以对左右音量分别设置，还可以设置优先级，循环次数以及速率
                            sndHigh.play(hitOfHigh, 1, 1, 0, 0, 1);
                        else
                            sndLow.play(hitOfLow, 1, 1, 0, 0, 1);
                        if (pp != section) {
                            pp++;
                        } else {
                            pp = 1;
                        }
                        break;
                }
                super.handleMessage(msg);
            }
        };

        button_start = (Button) root.findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tempo = Float.parseFloat(edit_sudu.getText().toString());
                section = Integer.parseInt(edit_paishu.getText().toString());
                mytimer = new Timer();
                float tempFloat = 60 / tempo * 1000;
                mytimer.schedule(new MyTimerTask(), 0, (long) tempFloat);
            }
        });

        button_stop = (Button) root.findViewById(R.id.button_stop);
        button_stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mytimer.cancel();
                sndHigh.pause(hitOfHigh);
                sndLow.pause(hitOfLow);
            }
        });

        final SeekBar seekbar = (SeekBar) root.findViewById(R.id.seekbar);
        //设置拖动条的初始值和文本框的初始值
        seekbar.setMax(7);
        seekbar.setProgress(5);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        //通过getStreamVolume获得当前音量大小
        volume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        //把当前音量的值 设置给进度条
        seekbar.setProgress(volume);

        sndHigh = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        //载入音频流
        hitOfHigh = sndHigh.load(getActivity(), R.raw.sound_e2, 0);//high

        sndLow = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        //载入音频流
        hitOfLow = sndLow.load(getActivity(), R.raw.sound_e, 0);//low


        speedTest(root);
        return root;
    }

//    ---------------------
//    作者：iteye_20755
//    来源：CSDN
//    原文：https://blog.csdn.net/iteye_20755/article/details/81990884
//    版权声明：本文为博主原创文章，转载请附上博文链接！

    /*以下是测速器*/
    private List<Long> times = new ArrayList<>();
    private Long firstTime = 0l;
    private int position = 0;

    private void speedTest(View root) {
        Button btnTest = (Button) root.findViewById(R.id.btnSpeedTest);
        btnTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Long thisTime = System.currentTimeMillis();
                        if (firstTime == 0l) firstTime = thisTime;
                        position++;

                        Long subTime = 0l;
                        float speed = 0f;
                        if (position != 0) {
                            subTime = thisTime - firstTime;
                            speed = subTime / 1000 / position;
                        }
                        System.out.println("firstTime = " + firstTime);
                        System.out.println("subTime = " + subTime);
                        System.out.println("position = " + position);

                        new MyToast(getActivity(), "speed = " + speed, 1);
                        break;
                }
                return false;
            }
        });
    }
}
