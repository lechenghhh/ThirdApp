package com.lecheng.hello.thirdapp.ActivityList.fragment;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.lecheng.hello.mylibrary.blurdialogfragment.BlurDialogEngine;
import com.lecheng.hello.thirdapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*使用说明  ：http://p.codekk.com/detail/Android/tvbarthel/BlurDialogFragment
* 在 fragment继承这几个类后，在oncreate方法中加入
*        this.debug(true);
*        this.setBlurRadius(4);
*        this.setDownScaleFactor(5.0f);*/

/**
 * Created by Cheng on 2017/3/5.
 */

public class Frag031Blur extends DialogFragment {

    @Bind(R.id.tvBack)
    TextView tvBack;
    /**
     * DialogFragment使用方法：
     * http://blog.csdn.net/lmj623565791/article/details/37815413/
     * <p>
     * <p>
     * Engine used to blur.         //引擎方式启动fragment
     */
    private BlurDialogEngine mBlurEngine;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment014_4, null, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBlurEngine = new BlurDialogEngine(getActivity());
//        mBlurEngine.debug(mDebugEnable);
        mBlurEngine.setBlurRadius(8);
        mBlurEngine.setDownScaleFactor(8f);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBlurEngine.onResume(getRetainInstance());
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mBlurEngine.onDismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBlurEngine.onDestroy();
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btnSend)
    public void onClick() {
        tvBack.setText("你好，世界！");
    }
}
