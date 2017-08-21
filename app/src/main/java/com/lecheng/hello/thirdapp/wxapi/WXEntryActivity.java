package com.lecheng.hello.thirdapp.wxapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.R;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lecheng.hello.thirdapp.wxapi.WXEntryActivity.SHARE_TYPE.Type_WXSceneSession;
import static com.lecheng.hello.thirdapp.wxapi.WXEntryActivity.SHARE_TYPE.Type_WXSceneTimeline;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

//教程：http://www.apkbus.com/forum.php?mod=viewthread&tid=139851&highlight=%E5%BE%AE%E4%BF%A1%E5%88%86%E4%BA%AB
//简书：http://www.jianshu.com/p/7100645fe1a8
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Bind(R.id.etURL)
    EditText etURL;
    @Bind(R.id.etContent)
    EditText etContent;
    @Bind(R.id.etTitle)
    EditText etTitle;
    private String APP_ID = "wx6f6c8158e08d8c10";
    private IWXAPI iwxapi;

    enum SHARE_TYPE {Type_WXSceneSession, Type_WXSceneTimeline}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_wxshare);
        ButterKnife.bind(this);
        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        iwxapi.handleIntent(getIntent(), this);
        iwxapi.registerApp(APP_ID);
    }

    private void share(SHARE_TYPE type) {
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = etURL.getText().toString();
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = etTitle.getText().toString();
        msg.description = etContent.getText().toString();
//        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_share_music);
//        msg.thumbData = bmpToByteArray(thumb, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("Req");
        req.message = msg;
        switch (type) {
            case Type_WXSceneSession://转发到聊天界面
                req.scene = WXSceneSession;
                break;
            case Type_WXSceneTimeline://转发到朋友圈
                req.scene = WXSceneTimeline;
                break;
        }
        iwxapi.sendReq(req);
        finish();
    }

    @OnClick({R.id.btnSS, R.id.btnWTL})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSS:
                share(Type_WXSceneSession);
                break;
            case R.id.btnWTL:
                share(Type_WXSceneTimeline);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp resp) {
        String result;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "分享成功";
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "取消分享";
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "分享被拒绝";
                break;
            default:
                result = "发送返回";
                break;
        }
        Toast.makeText(this, result + "\n" + resp.errCode + "\n" + resp.errStr, Toast.LENGTH_SHORT).show();
        finish();
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
