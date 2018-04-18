package com.leachchen.testrecycleview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leachchen.testrecycleview.DragHelper.ItemDragHelperCallback;
import com.leachchen.testrecycleview.DragHelper.OnItemMoveListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class DragSwipList2Activity extends AppCompatActivity {

    private RecyclerView rv_recycleview;
    private List<String> mDataList = new ArrayList<>();
    private ItemTouchHelper mItemTouchHelper;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_swip_list2);
        rv_recycleview = (RecyclerView) this.findViewById(R.id.rv_recycleview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_recycleview.setLayoutManager(linearLayoutManager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        callback.setDragSwipe(true);
        callback.setDragLong(true);
        callback.setDragRemove(true);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rv_recycleview);
        rv_recycleview.setHasFixedSize(true);
        rv_recycleview.setNestedScrollingEnabled(false);

        rv_recycleview.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(this, R.color.divider_color)));


        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");
        mDataList.add("aa");


        mMyAdapter = new MyAdapter();
        rv_recycleview.setAdapter(mMyAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements OnItemMoveListener {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag_touch, parent, false));

            viewHolder.iv_touch.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mItemTouchHelper.startDrag(viewHolder);
                    return true;
                }
            });

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvTitle.setText("position:"+position);
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        @Override
        public void onItemMove(int fromPosition, int toPosition) {
           // String toBean = mDataList.get(toPosition);
            String item = mDataList.get(fromPosition);
            mDataList.remove(fromPosition);
            mDataList.add(toPosition, item);
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onItemRemove(int position) {

        }

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView tvTitle;
            ImageView iv_touch;

            public ViewHolder(View itemView) {
                super(itemView);
                tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
                iv_touch = (ImageView) itemView.findViewById(R.id.iv_touch);
            }

            public void setData(String title) {
                this.tvTitle.setText(title);
            }
        }

    }


}
