package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.Http.Http047;
import com.lecheng.hello.thirdapp.Interface.I047Listener;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyApplication;

import java.util.HashMap;
import java.util.Map;

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
    private Http047 weatherModel;

    private String string = "{\n" +
            "    \"tel\": {  \n" +
            "            \"nationcode\": \"86\", //国家码\n" +
            "            \"phone\": \"13788888888\" //手机号码\n" +
            "      },\n" +
            "    \"type\": \"0\", //0:普通短信;1:营销短信（强调：要按需填值，不然会影响到业务的正常使用）\n" +
            "    \"msg\": \"你的验证码是1234\", //验证码,utf8编码    \n" +
            "    \"sig\": \"fdba654e05bc0d15796713a1a1a2318c\", //app凭证，具体计算方式见下注\n" +
            "    \"extend\": \"\", //可选字段，默认没有开通(需要填空)。通道扩展码，\n" +
            "      //在短信回复场景中，腾讯server会原样返回，开发者可依此区分是哪种类型的回复\n" +
            "    \"ext\": \"\" //可选字段，不需要就填空。用户的session内容，腾讯server回包中会原样返回\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty046_msgverify);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                break;
            case R.id.btn2:
                break;
        }
    }

    @Override
    public void onSuccess(String strJson) {

    }
}
