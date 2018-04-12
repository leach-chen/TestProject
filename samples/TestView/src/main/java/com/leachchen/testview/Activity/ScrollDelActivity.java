package com.leachchen.testview.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.leachchen.testview.R;
import com.leachchen.testview.View.ItemRemoveRecyclerView;
import com.leachchen.testview.View.ScrollDelView;

import java.util.ArrayList;
import java.util.List;

import static com.leachchen.testview.R.id.rv_testview;

public class ScrollDelActivity extends AppCompatActivity {
    //https://blog.csdn.net/u010523832/article/details/53261502
    //刻度尺 http://w4lle.com/2016/05/15/Android%E8%87%AA%E5%AE%9A%E4%B9%89View%E4%B9%8B%E5%88%BB%E5%BA%A6%E5%B0%BA/
    //http://ipjmc.iteye.com/blog/1615828
    //switch button

    private RecyclerView rv_recycle;

    private List<String>mStringList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_del);

/*        rv_recycle = (RecyclerView)this.findViewById(R.id.rv_recycle);

        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");
        mStringList.add("aa");

        MyAdapter myAdapter = new MyAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_recycle.setLayoutManager(layoutManager);
        rv_recycle.setAdapter(myAdapter);*/

    }





    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHoler>
    {
        @Override
        public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

            ScrollDelView scrollDelView = new ScrollDelView(parent.getContext());
            return new ViewHoler(scrollDelView);
        }

        @Override
        public void onBindViewHolder(ViewHoler holder, int position) {
        }

        @Override
        public int getItemCount() {
            return mStringList.size();
        }

        public class ViewHoler extends RecyclerView.ViewHolder
        {
            private TextView tv_go;
            public ViewHoler(View itemView) {
                super(itemView);
            }
        }


    }








}
