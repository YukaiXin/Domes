package com.kxyu.domes.Adapter;

/**
 * Created by yuki_cool on 2017/2/15.
 */


        import android.content.Context;
        import android.graphics.Color;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.widget.helper.ItemTouchHelper;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.kxyu.domes.R;

        import java.util.List;


public class ChannelAdapter extends BaseRecyclerAdapter<String>  implements ItemTouchHelperAdapter
{
    final String TAG = "ChannelAdapter";
    private PickUpAnimationHelper pickUpAnimationHelper;
    private boolean mIsSelected = false;
    private List<String> mDataList;

    public ChannelAdapter(Context context, List<String> data, boolean useAnimation, boolean isSelected) {
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
//        if (mClickListener != null) {
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mClickListener.onItemClick(v, holder.getLayoutPosition());
//                }
//
//            });
//        }

        return holder;
    }


    public void setOnChangeViewHolderListener(PickUpAnimationHelper pickUpAnimationHelper) {
        this.pickUpAnimationHelper = pickUpAnimationHelper;
    }

    @Override
    public void bindData(final BaseRecyclerViewHolder holder, int position,  String str) {
       String title =  str;

            if(!TextUtils.isEmpty(title)){
               if(!((NewsChannelHolder)holder).mIsLongClick)
                holder.getView(R.id.channel_item_background).setBackgroundColor(ContextCompat.getColor(mContext,R.color.cadetblue));
                holder.getTextView(R.id.channel_item_title).setText(title);
                holder.getTextView(R.id.channel_item_title).setTextColor(Color.BLACK);
            }



        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((NewsChannelHolder)holder).mIsLongClick = true;
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

    public  List<String> getDataList(){
        return mDataList;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

//        if (toPosition == 0) {
//            return;
//        }
        int form = fromPosition;
        int to = toPosition;
        String prev = mDataList.remove(fromPosition);
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

        public void setLongClick(){
            mIsLongClick = true;
        }
        public NewsChannelHolder(Context context, View itemView) {
            super(context, itemView);
        }


        @Override
        public void onItemSelected() {
            mIsLongClick = true;
            Log.i(TAG,"onItemSelected");
            this.getView(R.id.channel_item_background).setBackgroundColor(ContextCompat.getColor(mContext,R.color.ghostwhite));
        }

        @Override
        public void onItemClear() {
            mIsLongClick = false;
            Log.i(TAG,"onItemClear");
            this.getView(R.id.channel_item_background).setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
        }
    }
}

