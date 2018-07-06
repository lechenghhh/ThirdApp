package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import java.util.HashMap;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//http://blog.csdn.net/ccorg/article/details/50805125
public class Aty062Text2Speech extends Activity implements TextToSpeech.OnInitListener {
    /** Called when the activity is first created. */
    /**
     * default state ： CHECK_VOICE_DATA_FAIL = 0;
     */
    private static final int REQ_TTS_STATUS_CHECK = 0;
    private static final String TAG = "TTS Demo";
    private TextToSpeech mTts;
    @Bind(R.id.et5)
    EditText et5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty062);
        ButterKnife.bind(this);

        try {//"这里注意，如果本机tts没有安装，这里就会抓获到异常的。tts使用的时候也要注意非空验证"
//            Intent checkIntent = new Intent();            // 检查TTS数据是否已经安装并且可用
//            checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);// 检查TTS状态
//            PackageManager pm = getPackageManager();
//            ResolveInfo resolveInfo = pm.resolveActivity(checkIntent, PackageManager.MATCH_DEFAULT_ONLY);
//            if (resolveInfo == null) new MyToast(this, "语音引擎不可用-100\n" + "找不到语音引擎", 1);
//            else startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);
            mTts = new TextToSpeech(this, this);

        } catch (ActivityNotFoundException e) {
            new MyToast(this, "语音引擎不可用-100\n" + e.fillInStackTrace(), 1);
        }
    }

    @OnClick({R.id.btn6, R.id.btn7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn6:
                try {
                    mTts.speak(et5.getText().toString(), TextToSpeech.QUEUE_ADD, new HashMap<String, String>());
                } catch (Exception e) {
                    System.out.println("语音未启用");
                }
                break;
            case R.id.btn7:

                break;
        }
    }

    @Override// 实现TTS初始化接口
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {        // TTS Engine初始化完成
            int result = mTts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {            // 判断语言是否可用
                new MyToast(this, "语音不可用！", 1);
            } else {
                mTts.speak("语音已启用", TextToSpeech.QUEUE_ADD, new HashMap<String, String>());
            }
        }
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQ_TTS_STATUS_CHECK) {
//            switch (resultCode) {
//                case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS: {// 这个返回结果表明TTS Engine可以用
//                    mTts = new TextToSpeech(this, this);
//                    Log.v(TAG, "语音引擎可用");
//                }
//                break;
//                case TextToSpeech.Engine.CHECK_VOICE_DATA_BAD_DATA:// 需要的语音数据已损坏
//                case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:// 缺少需要语言的语音数据
//                case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_VOLUME: {// 缺少需要语言的发音数据
//                    Log.v(TAG, "Need language stuff:" + resultCode);//这三种情况都表明数据有错,重新下载安装需要的数据
//                    Intent dataIntent = new Intent();
//                    dataIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
//                    startActivity(dataIntent);
//                }
//                break;
//                case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:                    // 检查失败
//                default:
//                    new MyToast(this, "语音不可用-102", 1);
//                    break;
//            }
//        } else {
//        }
//    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTts != null) mTts.stop();// activity暂停时也停止TTS
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTts != null) mTts.shutdown();// 释放TTS的资源
    }
}
