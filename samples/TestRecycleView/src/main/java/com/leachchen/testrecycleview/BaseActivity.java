package com.leachchen.testrecycleview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity  implements SwipeItemClickListener {

    protected SwipeMenuRecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected RecyclerView.ItemDecoration mItemDecoration;
    protected List<String> mDataList;
    protected BaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mRecyclerView = (SwipeMenuRecyclerView)this.findViewById(R.id.smr_slidedel);

        mLayoutManager = createLayoutManager();
        mItemDecoration = createItemDecoration();

        mDataList = createDataList();
        mAdapter = createAdapter();


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mItemDecoration);
        mRecyclerView.setSwipeItemClickListener(this);
    }


    protected BaseAdapter createAdapter() {
        return new MainAdapter(this);
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    protected RecyclerView.ItemDecoration createItemDecoration() {
        return new DefaultItemDecoration(ContextCompat.getColor(this, R.color.divider_color));
    }

    protected List<String> createDataList() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add("第" + i + "个Item");
        }
        return dataList;
    }

    @Override
    public void onItemClick(View itemView, int position) {
        Toast.makeText(this, "第" + position + "个", Toast.LENGTH_SHORT).show();
    }

}
