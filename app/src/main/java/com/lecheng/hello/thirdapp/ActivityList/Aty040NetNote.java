package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;
import com.lecheng.hello.thirdapp.Bean.Aty040.BeanList;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.GsonUtil;
import com.lecheng.hello.thirdapp.Utils.MyApplication;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import org.json.JSONArray;
import org.json.JSONException;

/*聚云云后端实现
* http://v2.mashupcloud.cn/dashboard-bo.html
* create by 乐城  17-2-25 9.28
* 获取token请求：
* http://v2.mashupcloud.cn/developer/auth.do?appkey=hgNILAxvbneJTTzIdyJTkJNmwzuoRWGm&appsecret=FrKeMDxkYBrMsrTlFyByuNkdVdSLFbZN
* token：（状态，token，appid）
* 返回结果：["OK","URHFweESyASCiRbxzbcOVdjGmwGzwAzG",233]
*
* */

public class Aty040NetNote extends AppCompatActivity {

//    @BindView(R.id.tvTitle)
//    EditText tvTitle;
//    @BindView(R.id.tvContent)
//    EditText tvContent;
//    @BindView(R.id.lvNoteList)
//    ListView lvNoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity040);
    }

    private void volleyGet() {
        String url = "http://v2.mashupcloud.cn/LIST/NetModel/";
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        resolveJson(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        new MyToast(getApplicationContext(), "失败", 3000);
                    }
                });
        request.setTag("cancelGet");
        MyApplication.getHttpQue().add(request);

    }

    private void resolveJson(String s) {
        new MyToast(getApplicationContext(), s, 3000);

        try {
            JSONArray jsonArray = new JSONArray(s);
            String str = (String) jsonArray.get(1);
        } catch (JSONException e) {
            new MyToast(getApplicationContext(), e + "", 3000);
        }
//        BeanList beanList = GsonUtil.GsonToBean(s, BeanList.class);

    }

//    @OnClick({R.id.btnAdd, R.id.btnEdit, R.id.btnDelete})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnAdd:
//                break;
//            case R.id.btnEdit:
//                break;
//            case R.id.btnDelete:
//                volleyGet();
//                new MyToast(getApplicationContext(), "请求数据中...", 3000);
//                break;
//
//        }
//    }
}
