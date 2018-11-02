package com.lecheng.hello.thirdapp.ActivityList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyLruCache;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty075Bitmap extends ActionBarActivity {
    public static final String LOGTAG = "Aty075Bitmap";

    @Bind(R.id.iv1)
    ImageView iv1;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    private Bitmap bitmap;
    private int drawableId = R.drawable.bg_047;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty075_bitmap);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeResource(getResources(), drawableId, options);//330x655
        tv1.setText("btimap.options:\n" +
                "inDensity: " + options.inDensity + "\n"
                + "inTargetDensity:" + options.inTargetDensity + "\n"
                + "outHeight:" + options.outHeight + "\n"
                + "outWidth:" + options.outWidth + "\n"
                + "outMimeType:" + options.outMimeType + "\n"// 图片的mimeType
        );
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                MyLruCache<Bitmap> myLruCache = MyLruCache.getInstance();

                Bitmap thatBitmap = myLruCache.get("123456");
                if (thatBitmap == null) {
                    thatBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_047);
                    myLruCache.put("123456", thatBitmap);
                    Log.v(getClass().getName(), "myLruCache-put");
                } else {
                    Log.v(getClass().getName(), "myLruCache-get");
                }
                iv1.setImageBitmap(thatBitmap);
                break;
            case R.id.btn2:
                Glide.with(this).load(drawableId).into(iv1);
                break;
            case R.id.btn3:
                decodeResource(view);
                break;
            case R.id.btn4:
                int[] colors = {256};
                Bitmap bm = Bitmap.createBitmap(colors, 1, 1, Bitmap.Config.RGB_565);
                iv1.setImageBitmap(bm);
//                Glide.with(this).load(bm).into(iv1);
                break;
        }
    }

    public void decodeResource(View view) {
        Bitmap bm = decodeBitmapFromResource();
        iv1.setImageBitmap(bm);
    }

    private Bitmap decodeBitmapFromResource() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), drawableId, options);
        options.inSampleSize = calculateSampleSize(options, 300, 300);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), drawableId, options);
    }

    // 计算合适的采样率(当然这里还可以自己定义计算规则)，reqWidth为期望的图片大小，单位是px
    private int calculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        Log.i("========", "calculateSampleSize reqWidth:" + reqWidth + ",reqHeight:" + reqHeight);
        int width = options.outWidth;
        int height = options.outHeight;
        Log.i("========", "calculateSampleSize width:" + width + ",height:" + height);
        int inSampleSize = 1;
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        while ((halfWidth / inSampleSize) >= reqWidth && (halfHeight / inSampleSize) >= reqHeight) {
            inSampleSize *= 2;
            Log.i("========", "calculateSampleSize inSampleSize:" + inSampleSize);
        }
        return inSampleSize;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null && !bitmap.isRecycled()) {            // 回收并且置为null
            bitmap.recycle();
            bitmap = null;
        }
        System.gc();
    }
}
