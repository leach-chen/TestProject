package com.leachchen.testmaterialdesign;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 * ClassName:   SimpleRecycleViewHelp.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/2 11:49
 **/

public class SimpleRecycleViewHelp {

    private Context mContext;
    private List<Class> mClassList;
    private List<String> mStringList;
    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;


    public SimpleRecycleViewHelp(Context context,RecyclerView recyclerView,List<Class> classList,List<String> stringList)
    {
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        this.mClassList = classList;
        this.mStringList = stringList;

        mMyAdapter = new MyAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMyAdapter);
    }



    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHoler>
    {
        @Override
        public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHoler(new RecycleItemView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(ViewHoler holder, int position) {
            holder.tv_go.setText(mStringList.get(position));
        }

        @Override
        public int getItemCount() {
            return mClassList.size();
        }

        public class ViewHoler extends RecyclerView.ViewHolder
        {
            private TextView tv_go;
            public ViewHoler(RecycleItemView itemView) {
                super(itemView);
                tv_go = itemView.getTextView();
                tv_go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getLayoutPosition();
                        if(position < mClassList.size()) {
                            Intent intent = new Intent(mContext, mClassList.get(position));
                            mContext.startActivity(intent);
                        }
                    }
                });
            }
        }
    }

    class RecycleItemView extends LinearLayout
    {
        TextView mTextView;

        public RecycleItemView(Context context) {
            super(context);
            this.setOrientation(LinearLayout.VERTICAL);
            mTextView = new TextView(context);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,100);
            //params.gravity = Gravity.CENTER;
            //params.leftMargin = 40;
            mTextView.setLayoutParams(params);

            mTextView.setPadding(40,0,0,0);
            mTextView.setGravity(Gravity.CENTER | Gravity.LEFT);

            View view = new View(context);
            LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,1);
            view.setLayoutParams(viewParams);
            view.setBackgroundColor(Color.parseColor("#666666"));

            addView(mTextView);
            addView(view);
        }

        public TextView getTextView()
        {
            return mTextView;
        }
    }
}
