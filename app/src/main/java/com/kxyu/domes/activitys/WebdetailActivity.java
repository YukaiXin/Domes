package com.kxyu.domes.activitys;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kxyu.domes.R;

/**
 * Created by kxyu on 16-8-10.
 * 学习webview
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
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);
            }
        });
    }

/**webview  在android不同版本，内核不同．
 * bug 当时用webview播放视频时，按HOME键退出，视屏仍然播放
 * 按以下的修改，在不同版本中是不同的．
 */
    @Override
    protected void onPause() {
        super.onPause();
        if(mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            ((ViewGroup)mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

}
