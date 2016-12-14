package com.lecheng.hello.thirdapp.ActivityListItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lecheng.hello.thirdapp.ActivityListItem.OtherActivity.BlurAty;
import com.lecheng.hello.thirdapp.R;

public class Aty031Blur extends Activity implements View.OnClickListener {
    private EditText et;
    public static Aty031Blur instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty032_blur);
        et = (EditText) findViewById(R.id.aty32_et);
        findViewById(R.id.aty32_btn1).setOnClickListener(this);

        instance = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.aty32_btn1) {
            startActivity(new Intent(this, BlurAty.class)
                    .putExtra("blur_content", et.getText() + ""));
        }
    }
    //hello 2016-12-13 22:18
}