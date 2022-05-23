package com.leachchen.testview.View.Other;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class MyViewGroup extends FrameLayout {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("mytest", "MyViewGroup dispatchTouchEvent Custom: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("mytest", "MyViewGroup dispatchTouchEvent Custom: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("mytest", "MyViewGroup dispatchTouchEvent Custom: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("mytest", "MyViewGroup dispatchTouchEvent Custom: ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(ev);//默认也是FALSE
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("mytest", "MyViewGroup onInterceptTouchEvent Custom: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("mytest", "MyViewGroup onInterceptTouchEvent Custom: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("mytest", "MyViewGroup onInterceptTouchEvent Custom: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("mytest", "MyViewGroup onInterceptTouchEvent Custom: ACTION_CANCEL");
                break;
        }
        return super.onInterceptTouchEvent(ev);//默认也是FALSE
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("mytest", "MyViewGroup onTouchEvent Custom: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("mytest", "MyViewGroup onTouchEvent Custom: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("mytest", "MyViewGroup onTouchEvent Custom: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("mytest", "MyViewGroup onTouchEvent Custom: ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(ev);//默认也是FALSE
    }
}


/*override fun onTouchEvent(ev: MotionEvent): Boolean {
    when (ev.action) {
    MotionEvent.ACTION_DOWN -> Log.d(
    "mytest",
    "MainActivity onTouchEvent Custom: ACTION_DOWN"
    )
    MotionEvent.ACTION_MOVE -> Log.d(
    "mytest",
    "MainActivity onTouchEvent Custom: ACTION_MOVE"
    )
    MotionEvent.ACTION_UP -> Log.d("mytest", "MainActivity onTouchEvent Custom: ACTION_UP")
    MotionEvent.ACTION_CANCEL -> Log.d(
    "mytest",
    "MainActivity onTouchEvent Custom: ACTION_CANCEL"
    )
    }
    return super.onTouchEvent(ev) //默认也是FALSE
    }*/