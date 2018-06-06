package com.lecheng.hello.thirdapp.ActivityList;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.RippleBackground;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//https://github.com/skyfishjy/android-ripple-background
public class Aty009View extends AppCompatActivity {


    @Bind(R.id.ripplebg)
    RippleBackground ripplebg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty009);
        ButterKnife.bind(this);
        try {//获取hashkey
            int i = 0;
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                i++;
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String KeyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                EditText edid = (EditText) findViewById(R.id.etid);
                edid.setText(KeyHash);
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }


    @OnClick({R.id.button4, R.id.iv1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                ripplebg.stopRippleAnimation();
                break;
            case R.id.iv1:
                ripplebg.startRippleAnimation();
                break;
        }
    }


}
