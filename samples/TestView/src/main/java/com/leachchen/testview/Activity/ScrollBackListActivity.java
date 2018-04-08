package com.leachchen.testview.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.leachchen.testview.R;

import java.util.ArrayList;

public class ScrollBackListActivity extends AppCompatActivity {

    /**
     * 三种方式onlayout，scroller，margintop
     */
    private RecyclerView rv_recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_back_list);

        rv_recycle = (RecyclerView)this.findViewById(R.id.rv_recycle);

        new SimpleRecycleViewHelp(this,rv_recycle,
                new ArrayList<Class>(){{
                }},
                new ArrayList<String>(){{
                    add("aaa");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                }});
    }
}
