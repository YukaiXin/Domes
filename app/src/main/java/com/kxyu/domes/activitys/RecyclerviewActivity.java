package com.kxyu.domes.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.kxyu.domes.recyclerviewAdapter.BaseSpacesItemDecoration;
import com.kxyu.domes.recyclerviewAdapter.ChannelAdapter;
import com.kxyu.domes.recyclerviewAdapter.PickUpAnimationHelper;
import com.kxyu.domes.R;
import com.kxyu.domes.Utils.MeasureUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuki_cool on 2017/2/15.
 */

public class RecyclerviewActivity extends Activity {
    final int ITEM_TOUCH_DISPALY_SIZE = 20;

    RecyclerView mSelectRecyclerView;
    ChannelAdapter mSelectAdapter;
    List<String> mSelectlist = new ArrayList<>() ;
    PickUpAnimationHelper mPickUpAnimationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        setDatasList();
        initView();
    }

    public void setDatasList(){
        String tempStr;
        for (int i = 0 ; i < 20 ; i++){
            tempStr = "item "+ String.valueOf(i);
            mSelectlist.add(tempStr);
        }
    }

    public void initView(){

        mSelectAdapter = new ChannelAdapter(this, mSelectlist, true, false);

        mSelectRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_one);
        mSelectRecyclerView.setLayoutManager(
                new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        mSelectRecyclerView.addItemDecoration(new BaseSpacesItemDecoration( MeasureUtil.dip2px(this, 6)));
        mSelectRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mSelectRecyclerView.setAdapter(mSelectAdapter);
        mSelectRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            float endY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        endY = event.getY();
                        if (endY - mSelectRecyclerView.getHeight() > ITEM_TOUCH_DISPALY_SIZE || endY < - ITEM_TOUCH_DISPALY_SIZE ) {
                            mSelectRecyclerView.setLayoutFrozen(true);
                        } else {
                            mSelectRecyclerView.setLayoutFrozen(false);
                        }
                    case MotionEvent.ACTION_CANCEL:
                        mSelectRecyclerView.setLayoutFrozen(false);
                        break;
                }
                return false;
            }
        });

        mPickUpAnimationHelper = new PickUpAnimationHelper(mSelectRecyclerView, mSelectAdapter);
        mSelectAdapter.setOnChangeViewHolderListener(mPickUpAnimationHelper);

    }
}
