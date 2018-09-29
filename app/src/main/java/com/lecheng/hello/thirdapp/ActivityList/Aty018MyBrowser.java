package com.lecheng.hello.thirdapp.ActivityList;

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
    private EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty018);

        init();
    }

    private void init() {
        setProgressBarVisibility(true);

        webView = (WebView) findViewById(R.id.webView);
        etUrl = (EditText) findViewById(R.id.etUrl);

        String url = getIntent().getStringExtra("url");
        if (url != null && !url.equals(""))
            etUrl.setText(url);
        findViewById(R.id.ivClose).setOnClickListener(this);
        findViewById(R.id.ivBack).setOnClickListener(this);
        findViewById(R.id.ivSync).setOnClickListener(this);
        findViewById(R.id.tvForward).setOnClickListener(this);
        findViewById(R.id.tvGoto).setOnClickListener(this);

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {            // Set progress bar during loading
            public void onProgressChanged(WebView view, int progress) {
                Aty018MyBrowser.this.setProgress(progress * 100);
            }
        });

        WebSettings websettings = webView.getSettings();        // Enable some feature like Javascript and pinch zoom
        websettings.setJavaScriptEnabled(true);                        // Warning! You can have XSS vulnerabilities!
        websettings.setBuiltInZoomControls(true);

        etUrl.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            String thisUrl = completionUrl(etUrl.getText().toString());
                            webView.loadUrl(thisUrl);
                            etUrl.setText(thisUrl);
                            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(etUrl.getWindowToken(), 0);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        webView.loadUrl(completionUrl(etUrl.getText().toString()));
    }

    private String completionUrl(String thisUrl) {
        if (!thisUrl.contains("http"))
            thisUrl = "http://" + thisUrl;
        return thisUrl;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivClose:
                finish();
                break;
            case R.id.ivBack:
                if (webView.canGoBack()) webView.goBack(); // goBack()表示返回WebV
                else finish();
                break;
            case R.id.ivSync:
                webView.reload();
                break;
            case R.id.tvForward:
                webView.goForward();
                break;
            case R.id.tvGoto:
                webView.loadUrl(etUrl.getText().toString());
                break;
            /*case R.id.webview_stop:
                webView.stopLoading();
                break;*/
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack(); // goBack()表示返回WebV
        else finish();
    }

    @Override//防止内存泄漏
    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView.destroy();
    }

    @Override//防止内存泄漏
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        webView.stopLoading();
        webView.getSettings().setJavaScriptEnabled(false);
        webView.clearHistory();
        webView.removeAllViews();
        webView.destroy();
        super.finish();
    }
}
