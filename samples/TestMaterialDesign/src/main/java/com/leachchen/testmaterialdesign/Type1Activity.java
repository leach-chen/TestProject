package com.leachchen.testmaterialdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Type1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type1);

        RecyclerView recyclerView = (RecyclerView)this.findViewById(R.id.recyclerView);

        new SimpleRecycleViewHelp(this,recyclerView,
                new ArrayList<Class>(){{
                    add(Type1Activity.class);
                    add(Type1Activity.class);
                }},
                new ArrayList<String>(){{
                    add("样式1");
                    add("样式2");
                }});
    }
}
