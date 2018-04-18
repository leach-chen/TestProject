package com.leachchen.commonbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:   BaseRecycleActivity.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/18 12:15
 **/

public class BaseRecycleActivity extends AppCompatActivity {

    protected SimpleRecycleViewHelp mSimpleRecycleViewHelp;
    private List<Class> mClassList = new ArrayList<>();
    private List<String> mShowTextList = new ArrayList<>();
    protected RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        mRecyclerView = (RecyclerView)this.findViewById(R.id.rv_recycleview);
        mSimpleRecycleViewHelp = new SimpleRecycleViewHelp(this,mRecyclerView,mClassList,mShowTextList);
    }
}
