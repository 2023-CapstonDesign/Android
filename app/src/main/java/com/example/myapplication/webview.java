package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true); // JavaScript 사용 설정
        webView.setWebViewClient(new WebViewClient()); // 웹뷰에서 페이지를 로드하기 위해 클라이언트 설정
        webView.loadUrl("http://www.google.com/"); // 로드할 웹사이트 주소 입력

    }


}