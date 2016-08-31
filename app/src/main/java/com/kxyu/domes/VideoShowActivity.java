package com.kxyu.domes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.kxyu.domes.callback.OnItemClickListener;
import com.kxyu.domes.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;

public class VideoShowActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MSG_REFLASH_VIDEO = 0;

    WebView mWebView;
    VideoDataEntry mVideoDateEntry;
    TextView mVideoTitle;
    ProgressBar mVideoProgress;
    ImageView  mVideoMask;

    VideosListRecyclerAdapter adapter;

    RecyclerView mRecyclerView;
    boolean mIsError =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_show);
       initView();
        Log.i("kxyu","1");
        getVideo();
    }

    private void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(this.getResources().getDrawable(R.mipmap.action_bar_back));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mVideoTitle = (TextView) findViewById(R.id.video_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mVideoProgress = (ProgressBar) findViewById(R.id.video_progress);
        mVideoMask = (ImageView) findViewById(R.id.video_mask);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        //设置增加或删除条目的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mVideoMask.setVisibility(View.GONE);
        mVideoProgress.setVisibility(View.GONE);

        mVideoTitle.setOnClickListener(this);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_REFLASH_VIDEO) {
                adapter = new VideosListRecyclerAdapter(getApplication(),mVideoDateEntry);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Toast.makeText(getApplication(),".....",Toast.LENGTH_SHORT).show();
                        if(position == -1 ) return;

                        adapter.notifyItemChanged(position);
                        Intent  intent = new Intent(VideoShowActivity.this,VideoShowActivity.class);
                        intent.putExtra("video_url", mVideoDateEntry.videosDataEntryList.get(position).detailUrl);
                        //intent.putExtra("")
                        ActivityCompat.startActivity(VideoShowActivity.this, intent,null);

//                        intent = new Intent(VideoNewsDetailsActivity.this,VideoNewsDetailsActivity.class);
//                        intent.putExtra("id", mAdapter.getData().get(position).id);
//                        intent.putExtra("type",mAdapter.getData().get(position).type);
//                        intent.putExtra("news",JsonUtil.parseObjectToJsonString(mAdapter.getData().get(position)));
//                        intent.putExtra("detail_url",mAdapter.getData().get(position).detail_url);
//                        intent.putExtra("title",mAdapter.getData().get(position).content);
//                        ActivityCompat.startActivity(VideoNewsDetailsActivity.this, intent, options.toBundle());
                    }
                });
                initWebView();
            }
        }
    };

    public void initWebView()
    {
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setBackgroundColor(0);
        mWebView.setBackgroundResource(R.drawable.item_image_default);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.getSettings().setUseWideViewPort(true);

        mVideoTitle.setText(mVideoDateEntry.videosDataEntryList.get(0).content);
        mWebView.loadUrl(mVideoDateEntry.videosDataEntryList.get(0).detailUrl);
        Log.i("kxyu","set");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url)
            {
           Log.i("kxyu","finish");
                    mVideoMask.setVisibility(View.GONE);
                    mVideoProgress.setVisibility(View.GONE);
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                mIsError = true;
                Log.i("kxyu","error");
                mVideoMask.setVisibility(View.VISIBLE);
                mVideoProgress.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);
                mVideoMask.setVisibility(View.VISIBLE);
                mVideoProgress.setVisibility(View.VISIBLE);
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
                Log.d("kxyu", "response = " + response.toString());
                Gson gson = new Gson();
                mVideoDateEntry = gson.fromJson(response, VideoDataEntry.class);
                Log.d("kxyu", "newsCardDataEntry = " + mVideoDateEntry.videosDataEntryList.toString());
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

            if(v.getId() == R.id.video_title){

                Intent intent = new Intent(this,WebdetailActivity.class);
                startActivity(intent);
            }

    }
}
