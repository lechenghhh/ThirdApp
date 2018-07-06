package com.lecheng.hello.thirdapp.ActivityList.fragment;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.blurfragment.BlurDialogEngine;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import butterknife.ButterKnife;

/*使用说明  ：http://p.codekk.com/detail/Android/tvbarthel/BlurDialogFragment
* 在 fragment继承这几个类后，在oncreate方法中加入
*        this.debug(true);
*        this.setBlurRadius(4);
*        this.setDownScaleFactor(5.0f);*/

/**
 * Created by Cheng on 2017/3/5.
 */

public class Frag031Blur extends DialogFragment {

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
        View root = inflater.inflate(R.layout.fragment031, null, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
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
        new MyToast(getActivity(), "未完成!", 3333);
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
