package com.kxyu.domes.activitys;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.kxyu.domes.R;

/**
 * Created by yuki_cool on 2016/11/14.
 */

public class SollBanderActivity extends Activity {

    private RollPagerView rollPagerView;
    final  int SLIP_MIN_DISTANCE = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
//
        rollPagerView = (RollPagerView) findViewById(R.id.rollpagerview);
        //设置透明度
        rollPagerView.setAnimationDurtion(5000);
        //设置适配器
        rollPagerView.setAdapter(new testAdapter(rollPagerView));
        //播放时间间隔
        // / rollPagerView.setPlayDelay();
        rollPagerView.setHintView(new ColorPointHintView(this, Color.WHITE, Color.GRAY));
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }


    private class testAdapter extends LoopPagerAdapter {

        private int[] images = {R.drawable.p1, R.drawable.p2, R.drawable.p3};
        private int count = images.length;

        public testAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {

            final int picNo = position + 1;
            final ImageView imageView = new ImageView(container.getContext());
            imageView.setImageResource(images[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            imageView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Toast.makeText(SollBanderActivity.this,"第"+picNo+"张图片", Toast.LENGTH_LONG).show();
                }

            });

            imageView.setOnTouchListener(new View.OnTouchListener() {
                float startY;
                float  endY;
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN :
                            startY = event.getY();
                            return true;
                        case MotionEvent.ACTION_MOVE:
                            endY = event.getY();
                            if(endY - startY > SLIP_MIN_DISTANCE || endY - startY < - SLIP_MIN_DISTANCE)
                            {
                                Log.i("kxyu_touch"," false  "+"Distance :  "+(endY - startY));
                                imageView.getParent().getParent().requestDisallowInterceptTouchEvent(false);
                            }else {
                                Log.i("kxyu_touch"," true  "+"Distance :  "+(endY - startY));
                                imageView.getParent().getParent().requestDisallowInterceptTouchEvent(true);
                            }
                            return true;
                    }
                    return false;
                }
            });
            return imageView;
        }

        @Override
        public int getRealCount() {
            return count;
        }
    }
}
