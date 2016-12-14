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
    private int mWidth;
    private int mHeight;
    private TextView textView;
    private Button btn1;
    private int statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty032_blur2);

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

        //启动毛玻璃效果
        blur1(layout);
    }

    private void blur1(final LinearLayout ll) {
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;
        Rect frame = new Rect();
        Aty031Blur.instance.getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        ///////////////////////////////

        int radius = 10;
        float scaleFactor = 8;

        View view = Aty031Blur.instance.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        Bitmap mBitmap = view.getDrawingCache();

        if (mBitmap == null) {
            return;
        }
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        Bitmap overlay = Bitmap.createBitmap((int) (width / scaleFactor),
                (int) (height / scaleFactor), Bitmap.Config.ARGB_8888); //对上一个activity的截图进行处理，要不然加载会很慢  
        Canvas canvas = new Canvas(overlay);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        view.setDrawingCacheEnabled(false);
        final Bitmap blurBitmap = FastBlur.fastblur(this, overlay, radius);
        ll.post(new Runnable() {
            @Override
            public void run() {
                ll.setBackgroundDrawable(new BitmapDrawable(blurBitmap));
            }
        });
    }
}  