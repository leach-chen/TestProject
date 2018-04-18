package com.leachchen.testrecycleview.DragHelper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;


public class ItemDragHelperCallback extends ItemTouchHelper.Callback {

    private RecyclerView mRecyclerView;
    //是否支持滑动删除
    private boolean isDragRemove;
    //是否支持拖拽排序
    private boolean isDragSwipe;
    //是否支持长按拖拽
    private boolean isDragLong;


    public void setDragRemove(boolean dragRemove) {
        isDragRemove = dragRemove;
    }

    public void setDragSwipe(boolean dragSwipe) {
        isDragSwipe = dragSwipe;
    }

    public void setDragLong(boolean dragLong) {
        isDragLong = dragLong;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        this.mRecyclerView = recyclerView;
        int dragFlags = 0;
        if (isDragSwipe) {
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
        }
        // 如果想支持滑动(删除)操作, swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END
        int swipeFlags = 0;
        if (isDragRemove) {
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // 不同Type之间不可移动
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            // return false;
        }

        if (recyclerView.getAdapter() instanceof OnItemMoveListener) {
            OnItemMoveListener listener = ((OnItemMoveListener) recyclerView.getAdapter());
            listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        OnItemMoveListener listener = (OnItemMoveListener)mRecyclerView.getAdapter();
        listener.onItemRemove(direction);
    }

    //是否支持长按拖拽功能
    @Override
    public boolean isLongPressDragEnabled() {
        if(isDragLong)
        {
            return true;
        }else
        {
            super.isLongPressDragEnabled();
        }
        return false;
    }

    //是否支持滑动删除功能
    @Override
    public boolean isItemViewSwipeEnabled() {
        if(isDragRemove)
        {
            return true;
        }else
        {
            return super.isItemViewSwipeEnabled();
        }
    }


    //当item拖拽开始时调用
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);//拖拽时设置背景色为灰色
        }
    }

    //当item拖拽完成时调用
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.WHITE);//拖拽停止时设置背景色为白色


        //OnItemMoveListener listener = (OnItemMoveListener)mRecyclerView.getAdapter();
        //listener.clearView(recyclerView, viewHolder);
    }

    //当item视图变化时调用
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //仅对侧滑状态下的效果做出改变
        if (actionState ==ItemTouchHelper.ACTION_STATE_SWIPE){
            //OnItemMoveListener listener = (OnItemMoveListener)mRecyclerView.getAdapter();
            //listener.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }else {
            //拖拽状态下不做改变，需要调用父类的方法
            super.onChildDraw(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
        }

    }

}
