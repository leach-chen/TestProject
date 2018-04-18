package com.leachchen.testrecycleview;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.Collections;

public class DragSwipGridActivity extends BaseDragActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView.setItemViewSwipeEnabled(false); //控制不可以侧滑删除
        mRecyclerView.setLongPressDragEnabled(true); // 长按拖拽，默认关闭。
        mRecyclerView.setItemViewSwipeEnabled(false); // 滑动删除，默认关闭。
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new GridLayoutManager(this, 2);
    }

    @Override
    protected OnItemMoveListener getItemMoveListener() {
        // 监听拖拽和侧滑删除，更新UI和数据源。
        return new OnItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                // 不同的ViewType不能拖拽换位置。
                if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) return false;

                // 真实的Position：通过ViewHolder拿到的position都需要减掉HeadView的数量。
                int fromPosition = srcHolder.getAdapterPosition() - mRecyclerView.getHeaderItemCount();
                int toPosition = targetHolder.getAdapterPosition() - mRecyclerView.getHeaderItemCount();

                if (fromPosition < toPosition)
                    for (int i = fromPosition; i < toPosition; i++)
                        Collections.swap(mDataList, i, i + 1);
                else
                    for (int i = fromPosition; i > toPosition; i--)
                        Collections.swap(mDataList, i, i - 1);

                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;// 返回true表示处理了，返回false表示你没有处理。
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
                int adapterPosition = srcHolder.getAdapterPosition();
                int position = adapterPosition - mRecyclerView.getHeaderItemCount();

                if (mRecyclerView.getHeaderItemCount() > 0 && adapterPosition == 0) { // HeaderView。
                    //mRecyclerView.removeHeaderView(mHeaderView);
                    //Toast.makeText(DragGridActivity.this, "HeaderView被删除。", Toast.LENGTH_SHORT).show();
                } else { // 普通Item。
                    mDataList.remove(position);
                    mAdapter.notifyItemRemoved(position);
                    Toast.makeText(DragSwipGridActivity.this, "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}
