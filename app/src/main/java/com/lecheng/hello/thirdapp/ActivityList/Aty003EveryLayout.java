package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.ImgUtil;
import com.lecheng.hello.thirdapp.Utils.NetUtils;

import java.io.IOException;


public class Aty003EveryLayout extends Activity {
    private static final int CHOOSE_PHOTO = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty003);

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.append(NetUtils.getIPAddress(this));
        findViewById(R.id.btnstartFL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Aty003EveryLayout.this, Aty003EveryLayout_Frame.class));
            }
        });
        findViewById(R.id.btnSetWallPaper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //图片选择器教程：http://blog.csdn.net/wbin233/article/details/50954868
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent, CHOOSE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = null;
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统使用这个方法处理图片
                        bitmap = ImgUtil.handleImageOnKitKat(this, data);        //ImgUtil是自己实现的一个工具类
                    } else {
                        //4.4以下系统使用这个方法处理图片
                        bitmap = ImgUtil.handleImageBeforeKitKat(this, data);
                    }
//                    ImageView view = (ImageView) findViewById(R.id.personal_info_header_img);
//                    view.setImageBitmap(bitmap);
                    WallpaperManager wallpaperManager = WallpaperManager.getInstance(Aty003EveryLayout.this);
                    try {
                        wallpaperManager.setBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
