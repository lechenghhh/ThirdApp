package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty052CardView extends ActionBarActivity {

    @Bind(R.id.cv1)
    CardView cv1;
    @Bind(R.id.cv2)
    CardView cv2;
    @Bind(R.id.cv3)
    CardView cv3;
    @Bind(R.id.seek1)
    SeekBar seek1;
    @Bind(R.id.seek2)
    SeekBar seek2;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.iv1)
    ImageView iv1;
    private ImageLoader il = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty052);
        ButterKnife.bind(this);
        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    cv3.setCardElevation(i);//shadow
                    tv1.setText("阴影：\n     " + i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    cv3.setRadius(i);//圆角大小设置
                    tv2.setText("圆角：\n     " + i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @OnClick({R.id.cv1, R.id.cv2, R.id.cv3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv1:
                il.displayImage("https://img3.doubanio.com/f/sns/3ac664d558f14a44d5fd6a065029e69eba0ed062/pics/sns/index_douban_ad_4.png", iv1);
                break;
            case R.id.cv2:
                new MyToast(this, "HelloWorld!", 1);
                break;
            case R.id.cv3:
                new MyToast(this, "HelloWorld222!", 1);
                break;
        }
    }
}
