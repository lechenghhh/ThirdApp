package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.thirdapp.Adapter.Common.CommonAdapter;
import com.lecheng.hello.thirdapp.Adapter.Common.ViewHolder;
import com.lecheng.hello.thirdapp.Bean.Aty040.BeanList;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.GsonUtil;
import com.lecheng.hello.thirdapp.Utils.MyApplication;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import org.json.JSONArray;
import org.json.JSONException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.text.TextUtils.indexOf;

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

    @Bind(R.id.tvTitle)
    EditText tvTitle;
    @Bind(R.id.tvContent)
    EditText tvContent;
    @Bind(R.id.lvNoteList)
    ListView lvNoteList;
    @Bind(R.id.btnAdd)
    Button btnAdd;
    @Bind(R.id.btnEdit)
    Button btnEdit;
    @Bind(R.id.btnDelete)
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity040);
        ButterKnife.bind(this);
    }

    private void volleyGet() {
        String url = "http://v2.mashupcloud.cn/LIST/NetNote/?appid=233&token=kGhMkHHEPyYrMCNIpAvBBxSiPEUHsvtx";
//                "http://v2.mashupcloud.cn/LIST/NetModel/?" +
//                "token=URHFweESyASCiRbxzbcOVdjGmwGzwAzG" +
//                "appid=233";
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
        //对string进行操作
        int i = indexOf(s, "]") + 2;
        System.out.println("开头截取的位置：" + i);
        String sChange = "{\"data\":" + s.substring(i, s.length() - 1) + "}";
        System.out.println("修改后的Json：" + sChange);
        BeanList beanList = GsonUtil.GsonToBean(sChange, BeanList.class);

        lvNoteList.setAdapter(new CommonAdapter<BeanList.DataBean>(Aty040NetNote.this, beanList.getData(), R.layout.cell006_sqlite) {
            @Override
            public void convert(ViewHolder helper, BeanList.DataBean item) {
                helper.setText(R.id.textView4, item.getTitle());
                helper.setText(R.id.textView5, item.getContent());
            }
        });

    }

    @OnClick({R.id.btnAdd, R.id.btnEdit, R.id.btnDelete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                break;
            case R.id.btnEdit:
                break;
            case R.id.btnDelete:
                volleyGet();
                new MyToast(getApplicationContext(), "请求数据中...", 3000);
                break;
        }
    }
}
