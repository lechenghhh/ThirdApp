package com.lecheng.hello.thirdapp.ActivityList;
/*http://www.2cto.com/kf/201506/405766.html*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Utils.Interface.HttpVolley;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty019MVC extends AppCompatActivity implements HttpVolley.OnResponseListener {

    @Bind(R.id.editText3)
    EditText editText3;
    @Bind(R.id.textView10)
    TextView textView10;
    @Bind(R.id.image1)
    ImageView image1;
    ImageLoader imageLoader = ImageLoader.getInstance();
    private HttpVolley weatherModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty019_mvc);
        ButterKnife.bind(this);
        weatherModel = new HttpVolley(this);
    }

    private void displayWeather(String strJson) {
        new MyToast(this, strJson, 3333);
        textView10.setText(strJson);
    }

    @Override
    public void onSuccess(String strJson) {
        displayWeather(strJson);
    }

    @OnClick({R.id.button2, R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                weatherModel.Get(editText3.getText().toString(), this);
                break;
            case R.id.button3:
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("keyfrom", "951827860");
                map.put("key", "123087914");
                map.put("type", "data");
                map.put("doctype", "json");
                map.put("version", "1.1");
                map.put("q", editText3.getText() + "");
                String url2 = "http://fanyi.youdao.com/openapi.do?";
                weatherModel.Post(url2, map, this);
                break;
        }
    }
}
