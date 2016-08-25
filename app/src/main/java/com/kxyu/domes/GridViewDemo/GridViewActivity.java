package com.kxyu.domes.GridViewDemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kxyu.domes.R;

import java.util.ArrayList;

/**
 * Created by lewa on 16-8-23.
 */
public class GridViewActivity extends AppCompatActivity {

    RecyclerView gridView1;
    RecyclerView gridView2;
    ArrayList<Card> CardList = new ArrayList<>();
    ArrayList<Card> UnSelect_CarList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gridview_main_layout);

        setData();

        initRecyclerView();
        initRecyclerView2();
    }


    public  void initRecyclerView(){
        gridView1 = (RecyclerView) findViewById(R.id.grid);
        gridView1.setLayoutManager(
                new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        //  gridView1.addItemDecoration(new BaseSpacesItemDecoration(MeasureUtil.dip2px(this, 8)));


//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        gridView1.setLayoutManager(layoutManager);
       // LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        gridView1.setItemAnimator(new DefaultItemAnimator());
        gridView1.getItemAnimator().setAddDuration(250);
        gridView1.getItemAnimator().setMoveDuration(250);
        gridView1.getItemAnimator().setChangeDuration(250);
        gridView1.getItemAnimator().setRemoveDuration(250);

        gridView1.setAdapter(new GridAdapter(this, CardList));
    }

    public  void initRecyclerView2(){
        gridView2 = (RecyclerView) findViewById(R.id.grid2);
//
//        gridView2.setLayoutManager(
//                new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        //  gridView1.addItemDecoration(new BaseSpacesItemDecoration(MeasureUtil.dip2px(this, 8)));
        gridView2.setItemAnimator(new DefaultItemAnimator());
        gridView2.getItemAnimator().setAddDuration(250);
        gridView2.getItemAnimator().setMoveDuration(250);
        gridView2.getItemAnimator().setChangeDuration(250);
        gridView2.getItemAnimator().setRemoveDuration(250);

        gridView2.setAdapter(new GridAdapter(this, UnSelect_CarList));
    }

    public void setData(){
        CardList.add(new Card(this, "Game", Color.BLUE));
        CardList.add(new Card(this, "PHOTO", Color.BLUE));
        CardList.add(new Card(this, "PICTRUE", Color.BLUE));
        CardList.add(new Card(this, "NEWS", Color.BLUE));
        CardList.add(new Card(this, "VIDEOS", Color.BLUE));
    }
}
