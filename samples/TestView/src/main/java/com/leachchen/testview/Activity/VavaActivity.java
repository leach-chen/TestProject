package com.leachchen.testview.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.leachchen.testview.R;
import com.leachchen.testview.View.VavaView;

public class VavaActivity extends AppCompatActivity {

    private VavaView vava_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vava);

        vava_view = (VavaView)findViewById(R.id.vava_view);
    }

    public void start(View view)
    {
        vava_view.startAnimation();
    }

    public void stop(View view)
    {
        vava_view.stopAnimation();
    }
}
