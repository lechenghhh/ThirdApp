package com.lecheng.hello.thirdapp.ActivityList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;



import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty048Test extends AppCompatActivity {

    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etCode)
    EditText etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty048_webservice);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSend, R.id.btnCheck})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:

                break;
            case R.id.btnCheck:
                break;
        }
    }
}
