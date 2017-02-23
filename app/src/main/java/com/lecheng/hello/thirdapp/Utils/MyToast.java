package com.lecheng.hello.thirdapp.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Cheng on 2016/8/15.
 */
public class MyToast {
    private static Toast mToast;

    public MyToast(Context c, String text, int duration) {

        if (mToast == null) {
            mToast = Toast.makeText(c, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }
}
