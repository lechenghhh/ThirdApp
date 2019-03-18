package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.SeekBar;

import com.lecheng.hello.thirdapp.Interface.OnResponseListener;
import com.lecheng.hello.thirdapp.Interface.HttpVolley;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;
import com.lecheng.hello.thirdapp.Widgets.SlideButton.SlideButton;
import com.lecheng.hello.thirdapp.Widgets.SudokuView.SudokuView;
import com.lecheng.hello.thirdapp.Widgets.VerificationSeekBar;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/* 超精准计步器：http://blog.csdn.net/linglongxin24/article/details/52868803*/
public class Aty055View extends ActionBarActivity implements OnResponseListener {

    @Bind(R.id.sv9)
    SudokuView sv9;
    @Bind(R.id.sbtn1)
    SlideButton sbtn1;
    @Bind(R.id.vsb1)
    VerificationSeekBar vsb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty055_view);
        ButterKnife.bind(this);

        sv9.setOnViewClick(new SudokuView.onViewClick() {
            @Override
            public void onClick(String text) {
                new MyToast(Aty055View.this, text, 1);
            }
        });
        sbtn1.setOnSlideListner(new SlideButton.SlideListner() {
            @Override
            public void slideOver() {
                new MyToast(Aty055View.this, "滑动完成", 1);
            }

            @Override
            public void slideRestart() {
                new MyToast(Aty055View.this, "滑动取消", 1);
            }
        });

        vsb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() != seekBar.getMax()) {
                    seekBar.setProgress(0);
                } else {                    // todo 做滑动到最右的操作.
                    new MyToast(Aty055View.this, "滑动完成", 1);
                }
            }
        });
    }

    @OnClick(R.id.btn2)
    public void onViewClicked() {

        HashMap<String, String> map = new HashMap<String, String>();
        new HttpVolley().Post(this, "http://10.0.110.134:8084/gallery", map, this);
    }

    @Override
    public void onSuccess(String strJson) {
        new MyToast(this, strJson, 1);
    }
}
