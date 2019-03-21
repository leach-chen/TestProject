package com.leachchen.testijkplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leachchen.testijkplayer.media.IjkVideoView;

public class MainActivity extends AppCompatActivity {

    private IjkVideoView mVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView = (IjkVideoView)findViewById(R.id.ijkplayer);

        //ijkplayer.setVideoPath("http://192.168.1.254:8192");

        mVideoView.setVideoURI(Uri.parse("http://192.168.1.254:8192"));
        mVideoView.start();

    }
}
