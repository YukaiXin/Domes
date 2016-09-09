package com.kxyu.domes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kxyu.domes.GridViewDemo.GridViewActivity;

/**
 * Created by kxyu on 16-8-23.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    Button mGridViewBtn;
    Button mVideoBtn;
    Button mAdBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

       initView();
    }

    public void initView(){
        mGridViewBtn = (Button) findViewById(R.id.button);
        mVideoBtn = (Button) findViewById(R.id.button2);
        mGridViewBtn.setOnClickListener(this);
        mVideoBtn.setOnClickListener(this);
        mAdBtn = (Button) findViewById(R.id.ad_btn);
        mAdBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button){
            Intent intent = new Intent(this, GridViewActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.button2){
            Intent intent = new Intent(this, VideoShowActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.ad_btn){
            Intent intent = new Intent(this, AdActivity.class);
            startActivity(intent);
        }

    }
}
