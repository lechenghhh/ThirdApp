package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.lecheng.hello.thirdapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class Aty022UniversalImgLoader extends AppCompatActivity {

    @Bind(R.id.iv1)
    ImageView iv1;
    @Bind(R.id.iv2)
    ImageView iv2;
    @Bind(R.id.iv3)
    ImageView iv3;
    @Bind(R.id.iv4)
    ImageView iv4;
    @Bind(R.id.iv5)
    ImageView iv5;
    @Bind(R.id.iv6)
    ImageView iv6;
    private ImageLoader im = ImageLoader.getInstance();
    private String IMG_URL = "https://wx1.sinaimg.cn/mw690/00669kengy1fmq6qsfrjhj31rt2gfb2a.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty022);
        ButterKnife.bind(this);

        im.displayImage(IMG_URL, iv1);

        Glide.with(this).load(IMG_URL).bitmapTransform(new BlurTransformation(this, 15)).into(iv6);
        Glide.with(this).load(IMG_URL).bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this)).into(iv3);
        Glide.with(this).load(IMG_URL).bitmapTransform(new CropCircleTransformation(this)).into(iv4);
//        Glide.with(this).load(IMG_URL).crossFade(1000).into(iv2);
//        //https://github.com/wasabeef/glide-transformations
//        Glide.with(this).load(IMG_URL).bitmapTransform(new CropCircleTransformation(this)).crossFade(1000).into(iv3);
//        Glide.with(this).load(IMG_URL).bitmapTransform(new BlurTransformation(this, 25)).crossFade(1000).into(iv4);
//        Glide.with(this).load(IMG_URL).bitmapTransform(new BlurTransformation(this, 25), new CropCircleTransformation(this)).crossFade(1000).into(iv5);
//        Glide.with(this).load(IMG_URL).bitmapTransform(new RoundedCornersTransformation(this, 30, 0, RoundedCornersTransformation.CornerType.BOTTOM)).crossFade(1000).into(iv6);

//        MultiTransformation multi = new MultiTransformation(
//                new BlurTransformation(25),
//                new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));
//        Glide.with(this).load(R.drawable.ic_broken_sky)
//                .apply(bitmapTransform(multi))
//                .into((ImageView) findViewById(R.id.image));
    }
}
