package com.leachchen.testrecycleview;

import android.os.Bundle;

import com.leachchen.commonbase.BaseRecycleActivity;

import java.util.ArrayList;

public class MainActivity extends BaseRecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSimpleRecycleViewHelp.setData(new ArrayList<Class>(){{add(SlideDelActivity.class);
            add(DragSwipListActivity.class);
            add(DragSwipList1Activity.class);
            add(DragSwipList2Activity.class);
            add(DragSwipGridActivity.class);
        }},new ArrayList<String>(){{add("滑动删除");
            add("List拖动交换位置");
            add("List拖动交换位置1");
            add("List拖动交换位置2");
            add("Grid拖动交换位置");
        }});
    }
}
