package com.lecheng.hello.thirdapp.ActivityListItem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.lecheng.hello.thirdapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Aty022UniversalImgLoader extends AppCompatActivity {

    private ImageView iv;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_img_loader22);

        imageLoader = ImageLoader.getInstance();
        iv = (ImageView) findViewById(R.id.universalimgloader_iv);
        imageLoader.displayImage("http://fanyi.baidu.com/static/translation/img/header/logo_cbfea26.png", iv);


    }
}
