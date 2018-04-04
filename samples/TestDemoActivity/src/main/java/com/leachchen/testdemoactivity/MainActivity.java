package com.leachchen.testdemoactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv_recycleview = (RecyclerView)this.findViewById(R.id.rv_recycleview);
        new SimpleRecycleViewHelp(this,rv_recycleview,new ArrayList<Class>(){{
            add(BaseActivity.class);
            add(BottomNavigationActivity.class);
            add(FullscreenActivity.class);
            add(ItemListActivity.class);
            add(LoginActivity.class);
            add(NavigationDrawerActivity.class);
            add(ScrollingActivity.class);
            add(SettingsActivity.class);
            add(TabActivity.class);
        }},new ArrayList<String>(){{
            add("BaseActivity");
            add("BottomNavigationActivity");
            add("FullscreenActivity");
            add("ItemListActivity");
            add("LoginActivity");
            add("NavigationDrawerActivity");
            add("ScrollingActivity");
            add("SettingsActivity");
            add("TabActivity");
        }});
    }
}
