package com.leachchen.testsystemalbum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SystemAlbumHelp.getInstance(this).getPhoneImage();
        //SystemAlbumHelp.getInstance(this).getPhoneImageThumb();
        //SystemAlbumHelp.getInstance(this).getPhoneVideo();
        //SystemAlbumHelp.getInstance(this).getPhoneVideoThumb();
        SystemAlbumHelp.getInstance(this).getPhoneAlbumClassify();
    }
}
