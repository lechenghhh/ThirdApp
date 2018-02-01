package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.RootShellCmd;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//adb -s 8d68ecc37ce3 shell input text 12345
public class Aty070Root extends Activity {
    @Bind(R.id.et1)
    EditText et1;
    private RootShellCmd rsc = new RootShellCmd();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty070_root);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnRoot, R.id.btnShell, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRoot:
                rsc.exec("");
                break;
            case R.id.btnShell:
                rsc.exec(et1.getText().toString());
                break;
            case R.id.btn1:
                rsc.simulateKey(24);
                break;
            case R.id.btn2:
                rsc.simulateKey(25);
                break;
            case R.id.btn3:
                rsc.simulateTap(540, 540);
                break;
            case R.id.btn4:
                rsc.exec("input text Third App Test\n");
                break;
        }
    }
}
