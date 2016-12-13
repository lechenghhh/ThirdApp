package com.lecheng.hello.thirdapp.ActivityListItem;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

public class Aty031WoolGlass extends AppCompatActivity {
    ImageView image;
    TextView statusText;
    LinearLayout aty31_controls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty031_wool_glass);
        image = (ImageView) findViewById(R.id.aty31_picture);
        statusText = (TextView)findViewById(R.id.aty31_text);
        aty31_controls = (LinearLayout) findViewById(R.id.aty31_controls);
        blur2();
    }

    private void blur2() {

    }
//    private void applyBlur() {
//        image.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//
//            @Override
//            public boolean onPreDraw() {
//                image.getViewTreeObserver().removeOnPreDrawListener(this);
//                image.buildDrawingCache();
//                Bitmap bmp = image.getDrawingCache();
//                blur(bmp, aty31_controls);
//                return true;
//            }
//        });
//    }
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
//    private void blur(Bitmap bkg, View view) {
//        long startMs = System.currentTimeMillis();
//        float radius = 20;
//
//        Bitmap overlay = Bitmap.createBitmap((int)(view.getMeasuredWidth()), (int)(view.getMeasuredHeight()), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(overlay);
//        canvas.translate(-view.getLeft(), -view.getTop());
//        canvas.drawBitmap(bkg, 0, 0, null);
//
//        RenderScript rs = RenderScript.create(Aty031WoolGlass.this);
//
//        Allocation overlayAlloc = Allocation.createFromBitmap(rs, overlay);
//        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, overlayAlloc.getElement());
//        blur.setInput(overlayAlloc);
//        blur.setRadius(radius);
//        blur.forEach(overlayAlloc);
//        overlayAlloc.copyTo(overlay);
//        view.setBackground(new BitmapDrawable(getResources(), overlay));
//        rs.destroy();
//
//        statusText.setText("cost " + (System.currentTimeMillis() - startMs) + "ms");
//    }
}
