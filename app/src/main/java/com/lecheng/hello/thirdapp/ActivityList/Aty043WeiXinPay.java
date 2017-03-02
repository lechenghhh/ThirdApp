package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*lecheng 17-2-2 19.38*/
public class Aty043WeiXinPay extends AppCompatActivity {

    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.btnPay)
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity043);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv2, R.id.btnPay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv2:
                break;
            case R.id.btnPay:
                break;
        }
    }
}
