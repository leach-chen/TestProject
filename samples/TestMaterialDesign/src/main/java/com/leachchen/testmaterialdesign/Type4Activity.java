package com.leachchen.testmaterialdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Type4Activity extends AppCompatActivity {

    private float originalX;
    private float originalY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type4);

        findViewById(R.id.btn).post(new Runnable() {
            @Override
            public void run() {
                originalX = findViewById(R.id.btn).getX();
                originalY = findViewById(R.id.btn).getY();
            }
        });

        findViewById(R.id.btn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    view.setX(motionEvent.getRawX()-view.getWidth()/2);
                    view.setY(motionEvent.getRawY()-view.getHeight()/2);
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    view.setX(originalX);
                    view.setY(originalY);
                }
                return true;
            }
        });
    }
}
