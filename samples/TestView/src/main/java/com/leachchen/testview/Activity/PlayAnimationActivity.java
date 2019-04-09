package com.leachchen.testview.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.leachchen.testview.R;
import com.leachchen.testview.View.PlayAnimationView;

public class PlayAnimationActivity extends AppCompatActivity {


    private PlayAnimationView playview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_animation);
        playview = (PlayAnimationView)findViewById(R.id.playview);
    }

    public void start(View view)
    {
        playview.startPlayAnima();
    }
}
