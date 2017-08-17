package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Net.HttpGo;
import com.lecheng.hello.thirdapp.Interface.I047Listener;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty046MsgVerify extends AppCompatActivity implements I047Listener {

    @Bind(R.id.et)
    EditText et;
    @Bind(R.id.et2)
    EditText et2;
    @Bind(R.id.tv)
    TextView tv;
    private HttpGo myAsyncHttp;
    private HashMap<String, String> map;
    private String url = "http://10.0.110.114:8090/Androidpacs/services/AndroidController?wsdl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty046_msgverify);
        ButterKnife.bind(this);
        //实例化
        myAsyncHttp = new HttpGo();
        map = new HashMap<String, String>();
    }

    @OnClick({R.id.btn, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                map.put("tel", et.getText() + "");
                myAsyncHttp.http047Post(this, url, map, this);
                break;
            case R.id.btn2:
                break;
        }
    }

    @Override
    public void onSuccess(String strJson) {
        new MyToast(this, strJson, 3333);
    }
}
