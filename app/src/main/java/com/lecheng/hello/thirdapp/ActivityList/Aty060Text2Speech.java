package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.lecheng.hello.thirdapp.R;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//http://blog.csdn.net/ccorg/article/details/50805125
public class Aty060Text2Speech extends Activity implements TextToSpeech.OnInitListener {
    /** Called when the activity is first created. */
    /**
     * default state ： CHECK_VOICE_DATA_FAIL = 0;
     */
    private static final int REQ_TTS_STATUS_CHECK = 0;
    private static final String TAG = "TTS Demo";
    @Bind(R.id.et5)
    EditText et5;
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty060_playerwav);
        ButterKnife.bind(this);
        try {//"这里注意，如果本机tts没有安装，这里就会抓获到异常的。tts使用的时候也要注意非空验证"
            // 检查TTS数据是否已经安装并且可用
            Intent checkIntent = new Intent();
            checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);// 检查TTS状态


            PackageManager pm = getPackageManager();
            ResolveInfo resolveInfo = pm.resolveActivity(checkIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if (resolveInfo == null) {
                // Not able to find the activity which should be started for this intent
                Log.e("----resolveInfo--------", "" + " Not able to find the activity which should be started for this intent");
            } else {
                startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);
            }

        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Oops! The function is not available in your device." + e.fillInStackTrace());

        }


        et5.setText("This is an example of speech synthesis.");
    }

    @OnClick({R.id.btn6, R.id.btn7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn6:
                mTts.speak(et5.getText().toString(), TextToSpeech.QUEUE_ADD, null);
                break;
            case R.id.btn7:

                break;
        }
    }

    @Override// 实现TTS初始化接口
    public void onInit(int status) {
        // TODO Auto-generated method stub
        // TTS Engine初始化完成
        if (status == TextToSpeech.SUCCESS) {
            int result = mTts.setLanguage(Locale.US);
            // 设置发音语言
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {            // 判断语言是否可用
                Log.v(TAG, "Language is not available");
//                speakBtn.setEnabled(false);
            } else {
                mTts.speak("This is an example of speech synthesis.", TextToSpeech.QUEUE_ADD, null);
//                speakBtn.setEnabled(true);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_TTS_STATUS_CHECK) {
            switch (resultCode) {
                case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS: {// 这个返回结果表明TTS Engine可以用
                    mTts = new TextToSpeech(this, this);
                    Log.v(TAG, "TTS Engine is installed!");
                }
                break;
                case TextToSpeech.Engine.CHECK_VOICE_DATA_BAD_DATA:// 需要的语音数据已损坏
                case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:// 缺少需要语言的语音数据
                case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_VOLUME: {// 缺少需要语言的发音数据
                    // 这三种情况都表明数据有错,重新下载安装需要的数据
                    Log.v(TAG, "Need language stuff:" + resultCode);
                    Intent dataIntent = new Intent();
                    dataIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(dataIntent);
                }
                break;
                case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:                    // 检查失败
                default:
                    Log.v(TAG, "Got a failure. TTS apparently not available");
                    break;
            }
        } else {
            // 其他Intent返回的结果
        }
    }

    @Override
    protected void onPause() {
        super.onPause();        // activity暂停时也停止TTS
        if (mTts != null) {
            mTts.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放TTS的资源
        if (mTts != null)
            mTts.shutdown();
    }
}
