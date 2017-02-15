package com.kxyu.domes.Adapter;

/**
 * Created by yuki_cool on 2017/2/15.
 */


        import android.content.Context;
        import android.graphics.Color;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.widget.helper.ItemTouchHelper;
        import android.text.TextUtils;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.kxyu.domes.R;


public class ChannelAdapter extends BaseRecyclerAdapter<CardInfo>  implements ItemTouchHelperAdapter
{

    private PickUpAnimationHelper pickUpAnimationHelper;
    private boolean mIsSelected = false;
    private List<CardInfo> mDataList;

    public ChannelAdapter(Context context, List<CardInfo> data, boolean useAnimation, boolean isSelected) {
        super(context, data, useAnimation);
        this.mIsSelected = isSelected;
        this.mDataList = data;
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.channel_item;
    }

    @Override
    public NewsChannelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final NewsChannelHolder holder = new NewsChannelHolder(mContext,
                mInflater.inflate(getItemLayoutId(viewType), parent, false));
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v, holder.getLayoutPosition());
                }

            });
        }

        if (pickUpAnimationHelper != null) {
            pickUpAnimationHelper.onChangeViewHolder(holder);
        }
        return holder;
    }


    public void setOnChangeViewHolderListener(PickUpAnimationHelper pickUpAnimationHelper) {
        this.pickUpAnimationHelper = pickUpAnimationHelper;
    }

    @Override
    public void bindData(final BaseRecyclerViewHolder holder, int position, CardInfo cardInfo) {
//        String title = null;
//
//        if(cardInfo != null)
//            if (!TextUtils.isEmpty(cardInfo.showTitleName)) {
//                String[] mShowTitleNameArray = cardInfo.showTitleName.split(",");
//                if (Constant.LANGUAGE_INDEX < mShowTitleNameArray.length) {
//                    title = mShowTitleNameArray[Constant.LANGUAGE_INDEX] ;
//                } else {
//                    title = mShowTitleNameArray[0];
//                }
//            } else {
//                if(cardInfo.type == RecyclerListAdapter.CARD_TYPE_PHOTOS || cardInfo.type == RecyclerListAdapter.CARD_TYPE_VIDEOS ||cardInfo.type == RecyclerListAdapter.CARD_TYPE_NEWS){
//                    if(Constant.SYSTEM_LANGUAGE.equals("hi"))
//                        title = NewsCard.getShowName(mContext, cardInfo.cardName);
//                    else
//                        title = cardInfo.cardName;
//                }else {
//                    title = cardInfo.cardName;
//                }
//            }
//
//        if(mIsSelected){
//            if(!TextUtils.isEmpty(title)){
//                if(!((NewsChannelHolder)holder).mIsLongClick)
//                    holder.getView(R.id.channel_item_background).setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
//                holder.getTextView(R.id.channel_item_title).setText(title);
//                holder.getTextView(R.id.channel_item_title).setTextColor(Color.BLACK);
//
//
//            }else {
//                holder.getTextView(R.id.channel_item_title).setText("");
//                holder.getView(R.id.channel_item_background).setBackgroundColor(ContextCompat.getColor(mContext,R.color.channel_item_background_color));
//                holder.getImageView(R.id.channel_item_background_rectangle).setBackgroundResource(R.mipmap.channel_rectangle);
//                holder.getView(R.id.channel_item_background).setBackgroundColor(ContextCompat.getColor(mContext,R.color.channel_item_background_color));
//            }
//        }else {
//            if(!TextUtils.isEmpty(title)) {
//                holder.getTextView(R.id.channel_item_title).setText(title);
//                holder.getTextView(R.id.channel_item_title).setTextColor(ContextCompat.getColor(mContext,R.color.channel_item_title_color));
//                holder.getView(R.id.channel_item_background).setBackgroundColor(ContextCompat.getColor(mContext, R.color.channel_item_background_color));
//                holder.getImageView(R.id.channel_item_background_rectangle).setBackgroundResource(R.mipmap.rectangle_real_background);
//            }else {
//
//                holder.getTextView(R.id.channel_item_title).setText("");
//                holder.getImageView(R.id.channel_item_background_rectangle).setBackgroundResource(R.mipmap.channel_virtual_rectangle);
//                holder.getView(R.id.channel_item_background).setBackgroundColor(ContextCompat.getColor(mContext,R.color.channel_item_background_color));
//            }
//        }


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (pickUpAnimationHelper != null) {
                    //start pickUp mode
                    ItemTouchHelper mItemTouchHelper = pickUpAnimationHelper.getItemTouchHelper();
                    if (mItemTouchHelper != null) {
                        pickUpAnimationHelper.startChange(true);
                        mItemTouchHelper.startDrag(holder);
                    }
                }
                return false;
            }
        });

    }

    public  List<CardInfo> getDataList(){
        return mDataList;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

//        if (toPosition == 0) {
//            return;
//        }
        int form = fromPosition;
        int to = toPosition;
        CardInfo prev = mDataList.remove(fromPosition);
        mDataList.add(toPosition, prev);
        notifyItemMoved(form, to);
    }

    @Override
    public void onItemDismiss(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }


    private class NewsChannelHolder extends BaseRecyclerViewHolder implements ItemTouchHelperViewHolder
    {
        public boolean mIsLongClick = false;
        @Override
        public TextView getTextView(int viewId) {
            return super.getTextView(viewId);
        }

        public boolean IsLongClick(){
            return mIsSelected;
        }

        public NewsChannelHolder(Context context, View itemView) {
            super(context, itemView);
        }


        @Override
        public void onItemSelected() {
            mIsLongClick = true;
            this.getView(R.id.channel_item_background).setBackgroundColor(ContextCompat.getColor(mContext,R.color.transparent));
        }

        @Override
        public void onItemClear() {
            mIsLongClick = false;
            this.getView(R.id.channel_item_background).setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
        }
    }

}

