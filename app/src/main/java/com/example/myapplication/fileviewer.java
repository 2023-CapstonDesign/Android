package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class fileviewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_viewr);

        final WebView webview;

        webview = (WebView) findViewById(R.id.webview1);

        webview.getSettings().setJavaScriptEnabled(true);

        webview.loadUrl("https://docs.google.com/gview?url=https://docs.google.com/document/d/1DZZ4O9Xxm17ASm8xCSQW7eu6-OiBEPO-9cFcf1mGcik/export?format=pdf&embedded=true");
    }
}