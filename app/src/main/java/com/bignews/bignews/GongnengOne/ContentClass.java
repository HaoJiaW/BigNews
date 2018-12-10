package com.bignews.bignews.GongnengOne;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bignews.bignews.R;

public class ContentClass extends AppCompatActivity{
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content);
        initView();

        Intent intent=getIntent();
        url= intent.getStringExtra("url");

        WebView webView= (WebView) findViewById(R.id.content_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }

    public  void initView(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.content_toolbar);
        setSupportActionBar(toolbar);
    }
}