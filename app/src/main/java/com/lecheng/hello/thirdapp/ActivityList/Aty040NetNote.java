package com.lecheng.hello.thirdapp.ActivityList;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lecheng.hello.thirdapp.Adapter.ListViewUnitAdpt.LvUnitAdpt;
import com.lecheng.hello.thirdapp.Adapter.ListViewUnitAdpt.ViewHolder;
import com.lecheng.hello.thirdapp.Bean.Aty040.BeanList;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyApplication;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Bind(R.id.etTitle)
    EditText etTitle;
    @Bind(R.id.etContent)
    EditText etContent;
    @Bind(R.id.btnAdd)
    Button btnAdd;
    @Bind(R.id.btnDelete)
    Button btnDelete;
    @Bind(R.id.lvNoteList)
    PullToRefreshListView lvNoteList;
    private String token = "qfnByOQJGXoAUeHGfXfAbtZUxBqDiTEv";
    private int item = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty040);
        ButterKnife.bind(this);
        volleyList();
    }

    private void volleyList() {
        String url =
//                "http://v2.mashupcloud.cn/LIST/NetNote/?appid=233&token=kGhMkHHEPyYrMCNIpAvBBxSiPEUHsvtx";
                "http://v2.mashupcloud.cn/LIST/NetNote/" +
                        "?token=" + token +
                        "&appid=233" +
                        "&pageSize=100";
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        resolveJson(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        new MyToast(Aty040NetNote.this, "失败", 3000);
                    }
                });
        request.setTag("volleyList");
        MyApplication.getHttpQue().add(request);
    }

    private void volleyAdd() {
        String url =
                "http://v2.mashupcloud.cn/ADD/NetNote/" +
                        "?token=" + token +
                        "&appid=233" +
                        "&title=" + etTitle.getText() +
                        "&content=" + etContent.getText() +
                        "&notetime=" + getTime();
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        new MyToast(Aty040NetNote.this, s, 3000);
                        displayLv();
                        volleyList();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        new MyToast(Aty040NetNote.this, "失败", 3000);
                    }
                });
        request.setTag("getList");
        MyApplication.getHttpQue().add(request);
    }

    private void volleyDelete() {
        String url =
                "http://v2.mashupcloud.cn/DELETE/NetNote/" +
                        "?token=" + token +
                        "&appid=233" +
                        "&id=" + item;
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        volleyList();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        new MyToast(Aty040NetNote.this, "失败", 3000);
                    }
                });
        request.setTag("volleyList");
        MyApplication.getHttpQue().add(request);
    }

    private void volleyEdit() {
        String url =
                "http://v2.mashupcloud.cn/DELETE/NetNote/" +
                        "?token=" + token +
                        "&appid=233" +
                        "&id=" + item +
                        "&title=" + etTitle.getText() +
                        "&content=" + etContent.getText();
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        new MyToast(Aty040NetNote.this, s, 3000);
                        displayLv();
                        volleyList();
                        item = -1;
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        new MyToast(Aty040NetNote.this, "失败", 3000);
                    }
                });
        request.setTag("volleyList");
        MyApplication.getHttpQue().add(request);
    }

    private void resolveJson(String s) {
//        new MyToast(this, s, 3000);
        //对string进行操作
        int i = indexOf(s, "]") + 2;
        System.out.println("开头截取的位置：" + i);
        String sChange = "{\"data\":" + s.substring(i, s.length() - 1) + "}";
        System.out.println("修改后的Json：" + sChange);
//        final BeanList beanList = GsonUtil.GsonToBean(sChange, BeanList.class);
        BeanList beanList = JSON.parseObject(sChange,BeanList.class);
        lvNoteList.setAdapter(new LvUnitAdpt<BeanList.DataBean>(Aty040NetNote.this, beanList.getData(), R.layout.item006_sqlite) {
            @Override
            public void convert(ViewHolder helper, BeanList.DataBean item) {
                helper.setText(R.id.textView4, item.getTitle());
                helper.setText(R.id.textView5, item.getContent());
                helper.setText(R.id.textView6, item.getNotetime());
            }
        });
        lvNoteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etTitle.setText(beanList.getData().get(position).getTitle());
                etContent.setText(beanList.getData().get(position).getContent());
                hideLv();
                item = position;

            }
        });
    }

    private String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    private void hideLv() {//隐藏lv，显示et
        lvNoteList.setVisibility(View.GONE);
        etTitle.setVisibility(View.VISIBLE);
        etContent.setVisibility(View.VISIBLE);
        btnAdd.setText("保存");
        btnDelete.setVisibility(View.VISIBLE);
    }

    private void displayLv() {//显示lv，隐藏et
        lvNoteList.setVisibility(View.VISIBLE);
        etTitle.setVisibility(View.GONE);
        etContent.setVisibility(View.GONE);
        btnAdd.setText("添加");
        btnDelete.setVisibility(View.GONE);
    }

    @OnClick({R.id.btnAdd, R.id.btnDelete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                if (lvNoteList.getVisibility() == View.VISIBLE) {
                    hideLv();
                } else if (item == -1) {
                    volleyAdd();
                } else {
                    volleyEdit();
                }
                break;
            case R.id.btnDelete:
                volleyDelete();
                new MyToast(this, "请求数据中...", 3000);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (lvNoteList.getVisibility() == View.VISIBLE) {
            super.onBackPressed();
        } else {
            new AlertDialog.Builder(this).setMessage("未保存，是否退出？")
                    .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            displayLv();
                        }
                    })
                    .setNegativeButton("取消", null).show();
        }
    }

}
