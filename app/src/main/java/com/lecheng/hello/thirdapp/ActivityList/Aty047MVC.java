package com.lecheng.hello.thirdapp.ActivityList;
/*http://www.2cto.com/kf/201506/405766.html*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty047MVC extends AppCompatActivity implements Aty047MVC_Listener {

    @Bind(R.id.editText3)
    EditText editText3;
    @Bind(R.id.textView10)
    TextView textView10;
    @Bind(R.id.textView11)
    TextView textView11;
    @Bind(R.id.textView12)
    TextView textView12;
    @Bind(R.id.textView13)
    TextView textView13;
    @Bind(R.id.textView14)
    TextView textView14;
    @Bind(R.id.textView15)
    TextView textView15;
    @Bind(R.id.textView16)
    TextView textView16;
    @Bind(R.id.textView17)
    TextView textView17;
    @Bind(R.id.textView18)
    TextView textView18;
    @Bind(R.id.textView19)
    TextView textView19;
    @Bind(R.id.aty047_mvc)
    LinearLayout aty047Mvc;
    private Aty047MVC_ModelImpl weatherModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty047_mvc);
        ButterKnife.bind(this);
        weatherModel = new Aty047MVC_ModelImpl();
    }

    private void displayWeather(String strJson) {
        new MyToast(this, strJson, 3333);
        textView10.setText(strJson);
    }

    @Override
    public void onSuccess(String strJson) {
        displayWeather(strJson);
    }

    @Override
    public void onError() {

    }

    @OnClick(R.id.button2)
    public void onClick() {
        weatherModel.getWeather(editText3.getText().toString(), this);
    }
}
