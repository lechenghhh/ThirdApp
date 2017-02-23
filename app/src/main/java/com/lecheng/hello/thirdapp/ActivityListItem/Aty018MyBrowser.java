package com.lecheng.hello.thirdapp.ActivityListItem;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.lecheng.hello.thirdapp.R;

public class Aty018MyBrowser extends Activity implements OnClickListener {
    private WebView webView;
    private EditText urlBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity018);
        init();
    }

    private void init() {

        setProgressBarVisibility(true);
        urlBox = (EditText) findViewById(R.id.house_et_url);
//		Intent ig = this.getIntent();
		urlBox.setText("http://fanyi.baidu.com/?aldtype=16047#en/zh/");

        webView = (WebView) findViewById(R.id.house_webview);

        webView.setWebViewClient(new WebViewClient() {
            // Load opened URL in the application instead of standard browser
            // application
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            // Set progress bar during loading
            public void onProgressChanged(WebView view, int progress) {
                Aty018MyBrowser.this.setProgress(progress * 100);
            }
        });

        // Enable some feature like Javascript and pinch zoom
        WebSettings websettings = webView.getSettings();
        websettings.setJavaScriptEnabled(true);                        // Warning! You can have XSS vulnerabilities!
        websettings.setBuiltInZoomControls(true);

        urlBox.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            webView.loadUrl(urlBox.getText().toString());
                            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(
                                    urlBox.getWindowToken(), 0);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        //监听事件响应
        findViewById(R.id.webview_backhomeaty).setOnClickListener(this);
        findViewById(R.id.webview_back).setOnClickListener(this);
        findViewById(R.id.webview_refresh).setOnClickListener(this);
        findViewById(R.id.webview_stop).setOnClickListener(this);
        findViewById(R.id.webview_forward).setOnClickListener(this);
        findViewById(R.id.webview_goto).setOnClickListener(this);
        //加载指定的居家网主页
        webView.loadUrl(urlBox.getText().toString());
    }

    @Override
    public void onBackPressed() {
//        webView.goBack();//浏览器返回
        finish();//浏览器退出关闭
    }

    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView.destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.webview_backhomeaty:
                finish();
                break;
            case R.id.webview_back:
                webView.goBack();
                break;
            case R.id.webview_refresh:
                webView.reload();
                break;
            case R.id.webview_stop:
                webView.stopLoading();
                break;
            case R.id.webview_forward:
                webView.goForward();
                break;
            case R.id.webview_goto:
                webView.loadUrl("http://" + urlBox.getText().toString());
                break;
        }
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }
}
