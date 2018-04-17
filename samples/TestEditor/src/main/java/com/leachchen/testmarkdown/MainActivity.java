package com.leachchen.testmarkdown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //markdown
    //https://blog.csdn.net/u014608640/article/details/53080027(原生解析) https://www.jianshu.com/p/b63d65556ca2
    //richeditor
    //https://blog.csdn.net/ding_gc/article/details/52839316

    private RecyclerView rv_recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_recycleview = (RecyclerView) this.findViewById(R.id.rv_recycleview);

        new SimpleRecycleViewHelp(this, rv_recycleview, new ArrayList<Class>() {{
            add(Markdown1Activity.class);
            add(Markdown2Activity.class);
            add(RichEditorActivity.class);
        }}, new ArrayList<String>() {{
            add("Markdown 编辑器及WebView解析");
            add("Markdown 原生解析");
            add("富文本编辑器");
        }});


    }
}
