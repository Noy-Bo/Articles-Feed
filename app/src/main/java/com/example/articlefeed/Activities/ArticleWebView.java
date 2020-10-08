package com.example.articlefeed.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.articlefeed.R;

public class ArticleWebView extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.web_view);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Bundle bundle = getIntent().getExtras();
        loadAnUrl(bundle.getString("url"));




    }
    public void loadAnUrl(String url)
    {
        webView.loadUrl(url);
    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
        super.onBackPressed();

        }
    }
}