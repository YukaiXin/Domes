package com.kxyu.domes.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.kxyu.domes.Common.GRoundCornersTransformation;
import com.kxyu.domes.Common.GlideRoundTransform;
import com.kxyu.domes.R;
import com.lewa.advert.api.AdData;
import com.lewa.advert.api.AdImageInfo;
import com.lewa.advert.api.AdLoader;
import com.lewa.advert.api.AdNativeLayout;
import com.lewa.advert.api.AdTextInfo;

/**
 * Created by kxyu on 16-9-8.
 */
public class AdActivity extends AppCompatActivity{
    AdNativeLayout adNativeLayout;
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
     //   adNativeLayout = (AdNativeLayout) findViewById(R.id.ad);
        initView();
    }

    public void initView(){

        imageView = (ImageView) findViewById(R.id.ad_iv);
        textView = (TextView) findViewById(R.id.ad_tv);


        RequestManager requestManager = Glide.with(AdActivity.this);

        requestManager.load("http://pic1.sc.chinaz.com/files/pic/pic9/201702/zzpic1323.jpg").
                bitmapTransform(new GRoundCornersTransformation(this, 50, 0, GRoundCornersTransformation.CornerType.BOTTOM)).placeholder(R.drawable.item_image_default).into(imageView);

//        GlideRoundTransform glideRoundTransform = new GlideRoundTransform(AdActivity.this, 2);
//        requestManager.load("https://pic2.zhimg.com/eadbaf1ee2e4367af2b097404fa686dd_b.jpg").
//        bitmapTransform(new RoundedCornersTransformation(this, 50, 0, RoundedCornersTransformation.CornerType.BOTTOM))
//                .placeholder(R.mipmap.black_mask).
//                into(imageView);
//        AdLoader adLoader = new AdLoader.Builder(this, "bihoMKqsjyds")
//                .setListener(new AdLoader.AdListener() {
//                    @Override
//                    public void onNativeDataSucceeded(AdData adData) {
//
//                        for (AdImageInfo adImageInfo : adData.getAdImageInfos()) {
//                            RequestManager requestManager = Glide.with(AdActivity.this);
//
//                            GlideRoundTransform glideRoundTransform = new GlideRoundTransform(AdActivity.this, 2);
//                            requestManager.load(adImageInfo.getUri()).transform(glideRoundTransform).placeholder(R.mipmap.black_mask).
//                                    into(imageView);
//                        }
//
//                        for (AdTextInfo adTextInfo : adData.getAdTextInfos()) {
//                            textView.setText(adTextInfo.getText());
//                        }
//                    }
//
//                    @Override
//                    public void onNativeFailed() {
//                        Log.i("kxyu_ad", "Fail !!!");
//                    }
//                }).build();
//        adLoader.startLoad();
    }
}
