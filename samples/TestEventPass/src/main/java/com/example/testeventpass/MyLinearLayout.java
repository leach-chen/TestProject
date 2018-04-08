package com.example.testeventpass;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * ClassName:   MyLinearLayout.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/8 11:08
 **/

public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("mytestt","..........MyLinearLayout dispatchTouchEvent run");
        boolean value =  super.dispatchTouchEvent(ev);
        Log.e("mytest","MyLinearLayout dispatchTouchEvent:"+value);
        return value;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("mytestt","..........MyLinearLayout onInterceptTouchEvent run");
        boolean value =  super.onInterceptTouchEvent(ev);
        Log.e("mytest","MyLinearLayout onInterceptTouchEvent:"+value);
        return value;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("mytestt","..........MyLinearLayout onTouchEvent run");
        boolean value =  super.onTouchEvent(event);
        Log.e("mytest","MyLinearLayout onTouchEvent:"+value);
        return value;
    }

}
