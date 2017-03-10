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
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyApplication;

import java.util.HashMap;
import java.util.Map;

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
    String string = "{\n" +
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
//                map.put("method", "alibaba.aliqin.fc.sms.num.send");
//                map.put("app_key", "23667794");
//                map.put("sign_method", "md5");
//                map.put("sign", "");//签名算法
//                map.put("timestamp", "2017-03-08 17:26:00");
//                map.put("format", "json");
//                map.put("v", "2.0");
//                map.put("sms_type", "normal");
//                map.put("sms_free_sign_name", "Third应用");
//                map.put("rec_num", "18606940624");
//                map.put("sms_template_code", "{name:'haha',code:'1234'}");
                return map;
            }
        };
        request2.setTag("cancelPost046");
        MyApplication.getHttpQue().add(request2);
    }

    private void volleyPost2() {
        String url2 = "https://yun.tim.qq.com/v3/tlssmssvr/sendsms?sdkappid=xxxxx&random=xxxx";
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
                map.put("tel", "{\n" +
                        "    \"nationcode\": \"86\", \n" +
                        "    \"phone\": \"13788888888\"\n" +
                        "}");
                map.put("type","0");
                map.put("msg","你的验证码是1234");
                map.put("sig","fdba654e05bc0d15796713a1a1a2318c");
                map.put("extend","");
                map.put("ext","0");
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
//                volleyPost2();
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
