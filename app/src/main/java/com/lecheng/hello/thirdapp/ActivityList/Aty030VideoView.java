package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import com.lecheng.hello.thirdapp.R;

public class Aty030VideoView extends Activity implements MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    String viewURL = "http://www.tctu.cn/Uploads/Video/Admin/2016-10-30/58154e3371d7b.mp4";
    public static final String TAG = "VideoPlayer";
    private VideoView mVideoView;
    private EditText mEditText;
    private Button mButton;
    private Uri mUri;
    private int mPositionWhenPaused = -1;
    private MediaController mMediaController;
    private VideoView vv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity030);
        //Set the screen to landscape.
//        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        vv = (VideoView) findViewById(R.id.aty30_video_view2);
        mVideoView = (VideoView) findViewById(R.id.aty30_video_view);
        mEditText = (EditText) findViewById(R.id.aty30_testurl);
        mButton = (Button) findViewById(R.id.aty30_openurl);
//        findViewById(R.id.aty30_openatyurl).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viewURL)));
//                vv.setVideoURI(Uri.parse(viewURL));
//            }
//        });
        mMediaController = new MediaController(this);

        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //Video file
                Log.v("123", "123");

                String murl = mEditText.getText().toString();

                if ("".equalsIgnoreCase(murl) || null == murl) {
                    murl = "http://www.tctu.cn/Uploads/Video/Admin/2016-10-30/58154e3371d7b.mp4";
                }
                mUri = Uri.parse(murl);

                Log.v("url1", murl);
                //Create media controller
                mVideoView.setMediaController(mMediaController);
                mVideoView.setVideoURI(mUri);
                mVideoView.start();

            }

        });
    }

    public void onStart() {
        // Play Video

        super.onStart();
    }

    public void onPause() {
        // Stop video when the activity is pause.
        mPositionWhenPaused = mVideoView.getCurrentPosition();
        mVideoView.stopPlayback();
        Log.d(TAG, "OnStop: mPositionWhenPaused = " + mPositionWhenPaused);
        Log.d(TAG, "OnStop: getDuration  = " + mVideoView.getDuration());

        super.onPause();
    }

    public void onResume() {
        // Resume video player
        if (mPositionWhenPaused >= 0) {
            mVideoView.seekTo(mPositionWhenPaused);
            mPositionWhenPaused = -1;
        }

        super.onResume();
    }

    public boolean onError(MediaPlayer player, int arg1, int arg2) {
        return false;
    }

    public void onCompletion(MediaPlayer mp) {
        this.finish();
    }
}