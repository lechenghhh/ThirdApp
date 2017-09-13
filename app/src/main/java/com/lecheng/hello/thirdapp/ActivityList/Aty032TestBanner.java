package com.lecheng.hello.thirdapp.ActivityList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.lecheng.hello.thirdapp.R;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

//教程地址：http://blog.csdn.net/rjgcszlc/article/details/52780118?locationNum=5&fps=1
public class Aty032TestBanner extends AppCompatActivity {

    @Bind(R.id.cb1)
    ConvenientBanner cb1;
    private String[] strImgs = {
            "http://ww2.sinaimg.cn/mw690/d0a5385bgw1f864oduizdj21kw13a10v.jpg",
            "http://ww1.sinaimg.cn/mw690/d0a5385bgw1f864okpn0pj21kw11x47s.jpg",
            "http://www.pp3.cn/uploads/201510/2015102409.jpg",
            "http://imga1.pic21.com/bizhi/131223/05601/s22.jpg",
            "http://ww4.sinaimg.cn/mw1024/d0a5385bjw1faesqbybmcj215o0rsk04.jpg",
            "http://ww1.sinaimg.cn/mw1024/d0a5385bjw1faesqasq7nj20rs15o0zi.jpg",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty032);
        ButterKnife.bind(this);
        List<String> imgList = Arrays.asList(strImgs);
        cb1.setPages(new CBViewHolderCreator<ImageViewHolder>() {
            @Override
            public ImageViewHolder createHolder() {
                return new ImageViewHolder();
            }
        }, imgList)
                .setPageIndicator(new int[]
                        {R.drawable.unit_dot_n, R.drawable.unit_dot_p}) //设置两个点作为指示器
                .setPageIndicatorAlign
                        (ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL); //设置指示器的方向水平  居中

        cb1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(Aty032TestBanner.this, "点击了条目" + position, Toast.LENGTH_LONG).show();
            }
        });
//        cb1.setPageIndicator(int[]) // 这个是设置指示器的方法
//        cb1.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign)// 设置指示器方向
        cb1.startTurning(2000);      //设置开始轮播以及轮播时间  建议在onResume方法中设置
        cb1.stopTurning();              //停止轮播  建议在onPause方法中设置
        cb1.setManualPageable(false);//设置不能手动影响  默认是手指触摸 轮播图不能翻页
        cb1.setCanLoop(true);  //默认true,设置轮播图是否轮播
    }

    class ImageViewHolder implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
}