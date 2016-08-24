package com.kxyu.domes.GridViewDemo;

/**
 * Created by kxyu on 16-8-23.
 */

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.kxyu.domes.R;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private ArrayList<Card> mDrawableList = new ArrayList<Card>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;

    public GridAdapter(Context context, ArrayList<Card> cardsList) {
        mDrawableList = cardsList;
        mContext = context;
        mInflater = LayoutInflater.from(context);

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
    }

    public int getCount() {
        return mDrawableList.size();
    }

    public Object getItem(int position) {
        return mDrawableList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewTag viewTag;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.gridview_item, null);

            // construct an item tag
            viewTag = new ItemViewTag((Card) convertView.findViewById(R.id.card));
            convertView.setTag(viewTag);
        } else
        {
            viewTag = (ItemViewTag) convertView.getTag();
        }


        viewTag.card = mDrawableList.get(position);
        return convertView;
    }

    class ItemViewTag
    {
        protected Card card;


        public ItemViewTag(Card card)
        {
            this.card = card;
        }
    }

}
