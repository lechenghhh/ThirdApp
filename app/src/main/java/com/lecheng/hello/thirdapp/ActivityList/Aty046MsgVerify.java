package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.provider.SyncStateContract;
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
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyApplication;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty046MsgVerify extends AppCompatActivity {

    @Bind(R.id.et)
    EditText et;
    @Bind(R.id.et2)
    EditText et2;
    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity046_msgverify);
        ButterKnife.bind(this);
    }

    private void volleyPost() {
        String url2 = "http://gw.api.taobao.com/router/rest";
        StringRequest request2 = new StringRequest
                (Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), "failed222", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("method", "alibaba.aliqin.fc.sms.num.send");
                map.put("app_key", "23667794");
                map.put("sign_method", "md5");
                map.put("sign", "");//签名算法
                map.put("timestamp", "2017-03-08 17:26:00");
                map.put("format", "json");
                map.put("v", "2.0");
                map.put("sms_type", "normal");
                map.put("sms_free_sign_name", "Third应用");
                map.put("rec_num", "18606940624");
                map.put("sms_template_code", "{name:'haha',code:'1234'}");
                return map;
            }
        };
        request2.setTag("cancelPost046");
        MyApplication.getHttpQue().add(request2);
    }

    @OnClick({R.id.btn, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                volleyPost();
                break;
            case R.id.btn2:
                break;
        }
    }
//    public static String signTopRequest(Map<String, String> params, String secret, String signMethod) throws IOException {
//        // 第一步：检查参数是否已经排序
//        String[] keys = params.keySet().toArray(new String[0]);
//        Arrays.sort(keys);
//
//        // 第二步：把所有参数名和参数值串在一起
//        StringBuilder query = new StringBuilder();
//        if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
//            query.append(secret);
//        }
//        for (String key : keys) {
//            String value = params.get(key);
//            if (StringUtils.areNotEmpty(key, value)) {
//                query.append(key).append(value);
//            }
//        }
//
//        // 第三步：使用MD5/HMAC加密
//        byte[] bytes;
//        if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
//            bytes = encryptHMAC(query.toString(), secret);
//        } else {
//            query.append(secret);
//            bytes = encryptMD5(query.toString());
//        }
//
//        // 第四步：把二进制转化为大写的十六进制
//        return byte2hex(bytes);
//    }
//
//    public static byte[] encryptHMAC(String data, String secret) throws IOException {
//        byte[] bytes = null;
//        try {
//            SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
//            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
//            mac.init(secretKey);
//            bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
//        } catch (GeneralSecurityException gse) {
//            throw new IOException(gse.toString());
//        }
//        return bytes;
//    }
//
//    public static byte[] encryptMD5(String data) throws IOException {
//        return encryptMD5(data.getBytes(Constants.CHARSET_UTF8));
//    }
//
//    public static String byte2hex(byte[] bytes) {
//        StringBuilder sign = new StringBuilder();
//        for (int i = 0; i < bytes.length; i++) {
//            String hex = Integer.toHexString(bytes[i] & 0xFF);
//            if (hex.length() == 1) {
//                sign.append("0");
//            }
//            sign.append(hex.toUpperCase());
//        }
//        return sign.toString();
//    }
}
