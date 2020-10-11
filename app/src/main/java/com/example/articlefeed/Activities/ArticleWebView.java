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
        //test
        webView.getSettings().setUserAgentString("");
        Bundle bundle = getIntent().getExtras();

        // checking if its haaretz to change view and url
        String url = bundle.getString("url");
        if (url.contains("www.haaretz") && url.contains("/.premium"))
        {
            webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
            int cutToheretIndex = url.indexOf(".il/") + 4;
            int cutFromHereIndex = url.indexOf("/.prem");

            String finalUrl = "";
            finalUrl += url.substring(0,cutToheretIndex);
            finalUrl += "misc/smartphone-article";
            finalUrl += url.substring(cutFromHereIndex);
            url = finalUrl;
        }


        loadAnUrl(url);




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