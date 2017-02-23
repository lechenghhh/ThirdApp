package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.Adapter.MyPreviewAdp;
import com.lecheng.hello.thirdapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class Aty028PicView extends AppCompatActivity implements View.OnClickListener {
    private WebView webView;
    private EditText urlBox;
    String[] _imgUrlArray = {
            "http://ww2.sinaimg.cn/mw690/d0a5385bgw1f864oduizdj21kw13a10v.jpg",
            "http://ww1.sinaimg.cn/mw690/d0a5385bgw1f864okpn0pj21kw11x47s.jpg",
            "http://www.pp3.cn/uploads/201510/2015102409.jpg",
            "http://imga1.pic21.com/bizhi/131223/05601/s22.jpg",
            "http://ww4.sinaimg.cn/mw1024/d0a5385bjw1faesqbybmcj215o0rsk04.jpg",
            "http://ww1.sinaimg.cn/mw1024/d0a5385bjw1faesqasq7nj20rs15o0zi.jpg",
//            "http://p4.gexing.com/shaitu/20130227/2226/512e179ab717e.jpg",
    };
    PhotoView[] _pvArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity028);

        init();
    }

    private void init() {
//        volley_Get("http://www.58.com/changecity.aspx");
//        loadImageCycleView();
        photoViewwithViewPager();

    }

    private void photoViewwithViewPager() {
        final TextView mTvTittle = (TextView) findViewById(R.id.test_title);
        ViewPager _vp;                                              //viewpagers
        _pvArray = new PhotoView[_imgUrlArray.length];                     //每个view承载photoview数组
        List<View> mViews1 = new ArrayList<View>();                 //vp承载的view数组
        ImageLoader imageLoader = ImageLoader.getInstance();
        LayoutInflater inflater = LayoutInflater.from(Aty028PicView.this);

        for (int i = 0; i < _imgUrlArray.length; i++) {
            mViews1.add(inflater.inflate(R.layout.cell028_picview, null));      //添加view，
            _pvArray[i] = (PhotoView) mViews1.get(i).findViewById(R.id.testcell_img);  //对每个view内承载的photoview实例化
            imageLoader.displayImage(_imgUrlArray[i], _pvArray[i]);                    //图片加载
        }

        //vp实例化并设置适配器
        _vp = (ViewPager) findViewById(R.id.test_vp);
        MyPreviewAdp mVPAdp1 = new MyPreviewAdp(mViews1, Aty028PicView.this);
        _vp.setAdapter(mVPAdp1);
//        _vp.setCurrentItem(imgPosition);

        //设置监听函数
        _vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                PhotoViewAttacher pa = new PhotoViewAttacher(_pvArray[position]);//给一个连接器
                mTvTittle.setText("图片预览(" + (position + 1) + "/" + _imgUrlArray.length + ")");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //给标题填入数据
        mTvTittle.setText("图片预览(" + 1 + "/" + _imgUrlArray.length + ")");
    }


//    private void volley_Get(String url) {                   //get网络请求方法
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                new MyToast(Aty028PicView.this, s, 4000);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
//        request.setTag("abcGet");
//        MyApplication.getHttpQue().add(request);
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                break;
        }
    }
}
