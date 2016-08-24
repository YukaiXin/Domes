package com.kxyu.domes.GridViewDemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.kxyu.domes.R;

import java.util.ArrayList;

/**
 * Created by lewa on 16-8-23.
 */
public class GridViewActivity extends AppCompatActivity {

    GridView gridView1;
    GridView gridView2;
    ArrayList<Card> CardList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gridview_main_layout);

        setData();
        gridView1 = (GridView) findViewById(R.id.grid);
        gridView2 = (GridView) findViewById(R.id.grid2);

        gridView1.setAdapter(new GridAdapter(this, CardList));
    }

    public void setData(){
        CardList.add(new Card(this, "Game", Color.BLUE));
        CardList.add(new Card(this, "PHOTO", Color.BLUE));
        CardList.add(new Card(this, "PICTRUE", Color.BLUE));
        CardList.add(new Card(this, "NEWS", Color.BLUE));
        CardList.add(new Card(this, "VIDEOS", Color.BLUE));
    }
}
