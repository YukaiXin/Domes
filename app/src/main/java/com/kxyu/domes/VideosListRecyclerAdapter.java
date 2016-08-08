package com.kxyu.domes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kxyu.domes.callback.OnItemClickListener;


/**
 * Created by kxyu on 16-8-8.
 */
public class VideosListRecyclerAdapter extends RecyclerView.Adapter<VideosListRecyclerAdapter.MyViewHolder> {


    private Context mContext;
    private LayoutInflater inflater;
    private VideoDataEntry mVideoDateEntry;
    private OnItemClickListener mClickListener;
    public VideosListRecyclerAdapter(Context context,VideoDataEntry mVideoDataEntry){
        this. mContext=context;
        this.mVideoDateEntry = mVideoDataEntry;
        inflater=LayoutInflater. from(mContext);
    }

    @Override
    public int getItemCount() {

        if (mVideoDateEntry.videosDataEntryList.size()-1<5)
        return mVideoDateEntry.videosDataEntryList.size()-1;
        return 5;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.mTitle.setText(mVideoDateEntry.videosDataEntryList.get(position + 1).content);
        Glide.with(mContext).load(mVideoDateEntry.videosDataEntryList.get(position + 1).imgInfoList.get(0).thumb).asBitmap()
                .placeholder(R.drawable.item_image_default).into(holder.mImage);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        final View view = inflater.inflate(R.layout.item_news_video,parent, false);
        final MyViewHolder holder= new MyViewHolder(view);


        final MyViewHolder finalHolder = holder;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v, finalHolder.getLayoutPosition(),viewType);
                }
            });


        return holder;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
//
        TextView mTitle;
        ImageView mImage;


        public MyViewHolder(View view) {
            super(view);
            mTitle=(TextView) view.findViewById(R.id.tv_news_article_title);
            mImage = (ImageView) view.findViewById(R.id.iv_news_video);
        }

    }
}

