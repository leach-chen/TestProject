package com.leachchen.testjni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,testMethod("test"),Toast.LENGTH_SHORT).show();
    }

    static {
        System.loadLibrary("testjni");
    }

    public native String testMethod(String name);
}
