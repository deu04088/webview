package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    WebView wView;
    ProgressBar bar;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wView = findViewById(R.id.wView);
        bar = findViewById(R.id.bar);
        bar.setVisibility(View.GONE);

        initWebView();

        edit = findViewById(R.id.edit);
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int action, KeyEvent keyEvent) {
                if(action == EditorInfo.IME_ACTION_DONE) {
                    wView.loadUrl("https://"+edit.getText().toString()+"");
                }
                return false;
            }
        });
    }

    public void initWebView() {
        wView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                bar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                bar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        WebSettings ws = wView.getSettings();
        ws.setJavaScriptEnabled(true);
        wView.loadUrl("http://naver.com");
    }

    @Override
    public void onBackPressed() {
        if(wView.canGoBack()) {
            wView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}