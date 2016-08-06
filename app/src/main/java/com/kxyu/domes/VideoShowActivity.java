package com.kxyu.domes;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kxyu.domes.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;

public class VideoShowActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MSG_REFLASH_VIDEO = 0;

    WebView mWebView;
    ImageView image;
    VideoDataEntry mVideoDateEntry;
    TextView mLayout;
    ProgressBar mVideoProgress;
    ImageView  mVideoMask;

    boolean mIsError =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        android.support.v7.app.ActionBar bar = getSupportActionBar();
//        bar.setHomeAsUpIndicator(R.drawable.common_full_open_on_phone);
//        bar.setDisplayHomeAsUpEnabled(true);


        mLayout = (TextView) findViewById(R.id.expandLayout);

        image = (ImageView) findViewById(R.id.video_image);
        mVideoProgress = (ProgressBar) findViewById(R.id.video_progress);
        mVideoMask = (ImageView) findViewById(R.id.video_mask);

        findViewById(R.id.imageButton).setOnClickListener(this);




        getVideo();

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_REFLASH_VIDEO) {

                initWebView();

                Glide.with(getApplicationContext())
                        .load(mVideoDateEntry.videosDataEntryList.get(0).imgInfoList.get(0).thumb).placeholder(R.drawable.item_image_default).into(image);


            }
        }
    };


    public void initWebView()
    {

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.getSettings().setUseWideViewPort(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url)
            {
                if(!mIsError) {
                    mVideoMask.setVisibility(View.GONE);
                    mVideoProgress.setVisibility(View.GONE);
                }
//                mIsWebviewLoadFinish = true;
//                if(DeviceUtil.isLoadImage(VideoNewsDetailsActivity.this)){
//                    mWebView.loadUrl("javascript:callJsFuc_SetImageMode()");
//                }
//                else {
//                    mWebView.loadUrl("javascript:callJsFuc_SetNoImageMode()");
//                }

                mWebView.loadUrl(mVideoDateEntry.videosDataEntryList.get(0).detailUrl);
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                mIsError = true;
                mVideoMask.setVisibility(View.VISIBLE);
                mVideoProgress.setVisibility(View.GONE);
            }

        });

    }

    public void getVideo() {

        HashMap<String, String> parm = new HashMap<>();
        parm.put("categories", "youtubes");
        parm.put("action", "next");
        parm.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFuZHJvaWQ6bGV3YV8xMjQiLCJhY2NvdW50X3R5cGUiOjIsInVzZXJfaWQiOiI1NzNiZDY2Y2NkZmZiYzc3NDY1NjZiYjAiLCJleHAiOjE0NzEzMTU1MzR9.6ZTYeceNLsuQt_suGV9tH_pfbGLptE0pggL0pM3H2Ic");
        parm.put("read_tag", "");
        Gson gson = new Gson();
        HttpClientUtil.useOkHttpPost(gson.toJson(parm), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("shunlidata", "response = " + response.toString());
                Gson gson = new Gson();
                mVideoDateEntry = gson.fromJson(response, VideoDataEntry.class);
                Log.d("shunlidata", "newsCardDataEntry = " + mVideoDateEntry.videosDataEntryList.toString());
                mHandler.sendEmptyMessage(MSG_REFLASH_VIDEO);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    Log.i("kxyu","dsadasdsad");
        if(v.getId() == R.id.imageButton)
        {

            Log.i("kxyu","1");
            if(mLayout.getVisibility() == View.VISIBLE)
            mLayout.setVisibility(View.GONE);
            else
            mLayout.setVisibility(View.VISIBLE);
        }
    }
}
