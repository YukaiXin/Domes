package com.kxyu.domes.GridViewDemo;

/**
 * Created by kxyu on 16-8-23.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kxyu.domes.R;
import com.kxyu.domes.defineView;

import java.util.ArrayList;

public class GridAdapter extends  RecyclerView.Adapter<GridAdapter.MyViewHolder> {


    private ArrayList<Card> mDrawableList = new ArrayList<Card>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = mInflater.inflate(R.layout.gridview_item, parent, false);
        final MyViewHolder holder= new MyViewHolder(view);

        final MyViewHolder finalHolder = holder;

        return finalHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.dfw.setData("NEWS", Color.RED);

    }


    @Override
    public int getItemCount() {
        return mDrawableList.size();
    }

    public GridAdapter(Context context, ArrayList<Card> cardsList) {
        mDrawableList = cardsList;
        mContext = context;
        mInflater = LayoutInflater.from(context);

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
    }

//        viewTag.card = mDrawableList.get(position);
//        return convertView;
//    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        defineView dfw;TextView tv;
        public MyViewHolder(View view) {
            super(view);
            dfw = (defineView) view.findViewById(R.id.i);
        }

    }

}
