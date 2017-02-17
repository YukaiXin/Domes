package com.kxyu.domes.recyclerviewAdapter;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by chyang on 16-8-24.
 */
public class PickUpAnimationHelper {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private DefaultItemAnimator mItemAnimator;
    private SimpleItemTouchHelperCallback mItemTouchCallback;
    private ItemTouchHelper mItemTouchHelper;
   // private Card mCurrentClickCard;
    private boolean isChange = false;

    public  PickUpAnimationHelper(RecyclerView recyclerView ,RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        mRecyclerView = recyclerView;

        //Animator
        mItemAnimator = new PoliteItemAnimator();
        mItemAnimator.setSupportsChangeAnimations(false);
        mRecyclerView.setItemAnimator(mItemAnimator);

        //Touch Helper
        mItemTouchCallback = new PoliteItemTouchHelperCallback((ItemTouchHelperAdapter) adapter);
        mItemTouchHelper = new ItemTouchHelper(mItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }


    public ItemTouchHelper getItemTouchHelper() {
        return mItemTouchHelper;
    }

 //   public void onChangeViewHolder(RecyclerView.ViewHolder viewHolder) {
//        if(viewHolder instanceof RecyclerListAdapter.ItemViewHolder) {
//            RecyclerListAdapter.ItemViewHolder  itemViewHolder = (RecyclerListAdapter.ItemViewHolder) viewHolder;
////            itemViewHolder.cardView.setUpChangeAnimation(isChange, mCurrentClickCard);
//        }
//    }
//

    public void startChange(boolean isChange) {
        this.isChange = isChange;

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyItemRangeChanged(1, mAdapter.getItemCount());
            }
        }, 10);

    }

//    public void startChange(boolean isChange) {
//        this.isChange = isChange;
//        mRecyclerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mAdapter.notifyItemRangeChanged(1, mAdapter.getItemCount());
//            }
//        }, 10);
//
//    }

    public void  doRecycleWaste() {
        if(mAdapter != null) mAdapter = null;
        if(mRecyclerView != null) mRecyclerView = null;
        if(mItemAnimator != null) mItemAnimator = null;
        if(mItemTouchHelper != null) mItemTouchHelper = null;
        if(mItemTouchCallback != null) mItemTouchCallback = null;
    }

    class PoliteItemAnimator extends DefaultItemAnimator {
    }

    class PoliteItemTouchHelperCallback extends SimpleItemTouchHelperCallback{

        public PoliteItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
            super(adapter);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            startChange(false );
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
            return super.onMove(recyclerView, source, target);
        }
    }
}
