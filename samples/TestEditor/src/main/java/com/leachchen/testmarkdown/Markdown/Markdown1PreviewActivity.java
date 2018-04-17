package com.leachchen.testmarkdown.Markdown;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.leachchen.testmarkdown.R;

public class Markdown1PreviewActivity extends AppCompatActivity {

    public static final String PREVIEW_MARKDOWN = "preview_markdown";
    private MarkdownPreviewView pre_markdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markdown1_preview);

        pre_markdown = (MarkdownPreviewView)this.findViewById(R.id.pre_markdown);
        final String str = this.getIntent().getStringExtra(PREVIEW_MARKDOWN);
        Log.e("mytest",str);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pre_markdown.parseMarkdown(str,true);
            }
        },1000);
    }
}
