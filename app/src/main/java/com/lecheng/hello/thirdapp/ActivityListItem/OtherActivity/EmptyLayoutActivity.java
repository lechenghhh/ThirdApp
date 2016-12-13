package com.lecheng.hello.thirdapp.ActivityListItem.OtherActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Cheng on 2015/7/25.
 */
public class EmptyLayoutActivity extends Activity{

    private LinearLayout root;
    private Button btnClickMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);


        for (int i=0;i<5;i++) {
            btnClickMe = new Button(this);
            btnClickMe.setText("按我吧");
        //   root.addView(btnClickMe);
            root.addView(btnClickMe, LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }
}
