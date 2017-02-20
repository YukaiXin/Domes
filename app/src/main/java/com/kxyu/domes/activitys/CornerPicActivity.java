package com.kxyu.domes.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.kxyu.domes.Common.GRoundCornersTransformation;
import com.kxyu.domes.R;

/**
 * Created by kxyu on 16-9-8.
 */
public class CornerPicActivity extends AppCompatActivity{
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        initView();
    }

    public void initView(){

        imageView = (ImageView) findViewById(R.id.ad_iv);
        textView = (TextView) findViewById(R.id.ad_tv);
        RequestManager requestManager = Glide.with(CornerPicActivity.this);

        requestManager.load("http://pic1.sc.chinaz.com/files/pic/pic9/201702/zzpic1323.jpg").
                bitmapTransform(new GRoundCornersTransformation(this, 50, 0, GRoundCornersTransformation.CornerType.BOTTOM)).placeholder(R.drawable.item_image_default).into(imageView);

    }
}
