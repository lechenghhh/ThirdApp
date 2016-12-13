package com.lecheng.hello.thirdapp.ActivityListItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.lecheng.hello.thirdapp.R;

public class Aty018TestMiniBrowser extends Activity implements OnClickListener{
	EditText edithttp;
	Button btngo;
    WebView wvBrowser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_minibrowser);
		btngo=(Button)findViewById(R.id.btngo);
		edithttp=(EditText)findViewById(R.id.edithttp);
		wvBrowser=(WebView)findViewById(R.id.wvBrowser);
		btngo.setOnClickListener(this);
//		wvBrowser.setWebViewClient(new ourClient());
		wvBrowser.setWebChromeClient(new WebChromeClient());
		try{
		    wvBrowser.loadUrl("http://wap.baidu.com");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.btngo:
			String website=edithttp.getText().toString();
			wvBrowser.loadUrl(website);
			break;
		}
	}
    
}
