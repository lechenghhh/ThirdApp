package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty041MVP_View extends AppCompatActivity {

    Aty041MVP_Presenter presenter = new Aty041MVP_Presenter();
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.et1)
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty041);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_save, R.id.btn_load})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                presenter.saveData(et1.getText().toString());
                new MyToast(this, "保存成功", 3000);
                break;
            case R.id.btn_load:
                tv1.setText(presenter.loadData().toString());
                new MyToast(this, "读取成功", 3000);
                break;
        }
    }

}
