package com.lecheng.hello.thirdapp.ActivityList;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.lecheng.hello.thirdapp.R;

/**
 * Dmeo说明:
 * 当WebView加载网页时获取该网页中的内容.
 * 参考资料:
 * http://www.maxters.net/2012/02/android-webview-get-html-source/
 */
//教程地址：http://blog.csdn.net/z82367825/article/details/52187921
public class Aty059WebViewPlus extends ActionBarActivity {
    private LinearLayout llContainer;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty059_webviewplus);
        init();
    }

    private void init() {
        llContainer = (LinearLayout) findViewById(R.id.llContainer);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setPluginsEnabled(true);
        webView.requestFocus();
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl("http://baidu.com");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
                        + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
//                view.loadData("javascript:alert('Hello JavaScript !')");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄漏
        llContainer.removeView(webView);
        webView.stopLoading();
        webView.getSettings().setJavaScriptEnabled(false);
        webView.clearHistory();
        webView.removeAllViews();
        webView.destroy();
    }

    final class InJavaScriptLocalObj {//开启javascript的通信接口

        @JavascriptInterface
        public void showSource(String html) {
            System.out.println("====>html=" + html);
        }
    }
}
