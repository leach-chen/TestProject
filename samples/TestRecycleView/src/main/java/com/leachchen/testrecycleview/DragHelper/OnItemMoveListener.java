package com.leachchen.testrecycleview.DragHelper;

/**
 * Created by 蔡小木 on 2016/4/28 0028.
 */
public interface OnItemMoveListener {
    /**
     * 拖拽移动的时候回调
     * @param fromPosition
     * @param toPosition
     */
    void onItemMove(int fromPosition, int toPosition);

    //滑动删除的时候回调
    void onItemRemove(int position);



    //void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder);
    //void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive);
}
