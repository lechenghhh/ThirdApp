package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;


public class Aty041MVP_View extends AppCompatActivity  {

//    @BindView(R.id.tv1)
//    TextView tv1;
//    @BindView(R.id.et1)
//    EditText et1;
    Aty041MVP_Presenter presenter = new Aty041MVP_Presenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity041);
    }

//    @OnClick({R.id.btn_save, R.id.btn_load})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_save:
//                presenter.saveData(et1.getText().toString());
//                new MyToast(getApplicationContext(), "保存成功", 3000);
//                break;
//            case R.id.btn_load:
//                tv1.setText(presenter.loadData().toString());
//                new MyToast(getApplicationContext(), "读取成功", 3000);
//                break;
//        }
//    }
//
//    @Override
//    public String getEt1() {
//        return et1.getText().toString();
//    }
//
//    @Override
//    public void settv1(String str) {
//        tv1.setText(str);
//    }
}
