package com.kxyu.domes;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by kxyu on 16-8-10.
 */
public class WebdetailActivity extends AppCompatActivity {


    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.web_detail_activity);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.setBackgroundColor(Color.parseColor("#000000"));
        mWebView.loadUrl("http://newsapi.revanow.com/articles/?id=57d7030ee2645073d58b8ba3&language=english&type=youtube_video");

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url)
            {
                Log.i("kxyu","finish--onPageFinished");
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                Log.i("kxyu","error---ReceivedError");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);


                Log.i("kxyu","start-----");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //　activity 销毁时　webview播放的视频　继续播放
        mWebView.reload();
    }
}
