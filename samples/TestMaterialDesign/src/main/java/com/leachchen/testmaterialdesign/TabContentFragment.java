package com.leachchen.testmaterialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ClassName:   TabContentFragment.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/3 10:33
 **/

public class TabContentFragment extends Fragment {

    private static final String EXTRA_CONTENT = "content";

    public static TabContentFragment newInstance(String content){
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        TabContentFragment tabContentFragment = new TabContentFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_tab_content, null);
        //((TextView)contentView.findViewById(R.id.tv_content)).setText(getArguments().getString(EXTRA_CONTENT));
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.rv_tab_content);


        new SimpleRecycleViewHelp(getContext(),recyclerView,
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
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                    add("test");
                }});

        return contentView;
    }
}