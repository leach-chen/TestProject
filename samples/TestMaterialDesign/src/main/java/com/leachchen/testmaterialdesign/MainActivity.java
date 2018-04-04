package com.leachchen.testmaterialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv_material_design = (RecyclerView)this.findViewById(R.id.rv_material_design);

       new SimpleRecycleViewHelp(this,rv_material_design,
                new ArrayList<Class>(){{
                    add(Type1Activity.class);
                    add(Type2Activity.class);
                    add(Type3Activity.class);
                    add(Type4Activity.class);
                }},
                new ArrayList<String>(){{
                    add("样式1");
                    add("样式2");
                    add("样式3");
                    add("样式4");
                }});
    }
}
