package com.example.testeventpass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("mytestt","..........MainActivity onTouchEvent");
        boolean value =  super.onTouchEvent(event);
        Log.e("mytest","MainActivity onTouchEvent:"+value);
        return value;
    }
}
