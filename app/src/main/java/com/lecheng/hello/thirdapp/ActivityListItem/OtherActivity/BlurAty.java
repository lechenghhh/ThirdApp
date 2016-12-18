package com.lecheng.hello.thirdapp.ActivityListItem.OtherActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.ActivityListItem.Aty031Blur;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.FastBlur;

public class BlurAty extends Activity {

    private ImageView mImageView;
    private LinearLayout layout;
    private TextView textView;
    private Button btn1;
    private BlurAty blurAty = this;
//    private int statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty031_blur2);

        layout = (LinearLayout) findViewById(R.id.aty31_ll);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new AlertDialog.Builder(BlurAty.this).setTitle("系统提示")//设置对话框标题
                .setMessage(getIntent().getStringExtra("blur_content"))//设置显示的内容
                .setPositiveButton("返回上一个Aty", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        finish();
                    }
                }).setNegativeButton("关闭窗口", new DialogInterface.OnClickListener() {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件
                Toast.makeText(BlurAty.this, "关闭了", Toast.LENGTH_SHORT).show();
            }
        }).show();//在按键
        //给layout添加毛玻璃效果
        blurAty.getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        new FastBlur(getApplicationContext(), layout, getWindowManager(),blurAty);
    }
}