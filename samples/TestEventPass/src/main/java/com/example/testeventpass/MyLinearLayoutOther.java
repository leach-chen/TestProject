package com.example.testeventpass;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ClassName:   MyLinearLayout.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/8 11:08
 **/

public class MyLinearLayoutOther extends LinearLayout {

    public MyLinearLayoutOther(Context context) {
        super(context);
    }

    public MyLinearLayoutOther(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayoutOther(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("mytestt","..........MyLinearLayoutOther dispatchTouchEvent run");
        boolean value =  super.dispatchTouchEvent(ev);
        Log.e("mytest","MyLinearLayoutOther dispatchTouchEvent:"+value);
        return value;

/*                                                                       (Activity)onTouchEvent(false)
                                                                                        ↑
        (LinearLayout)dispatchTouchEvent(false)-->onInterceptTouchEvent(false)---->onTouchEvent(false)
                                                                  ↓                    ↑
        (LinearLayout)dispatchTouchEvent(false)-->onInterceptTouchEvent(false)---->onTouchEvent(false)
                                                                  ↓                    ↑
            (TextView)dispatchTouchEvent(false)-->onInterceptTouchEvent(false)---->onTouchEvent(false)*/
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("mytestt","..........MyLinearLayoutOther onInterceptTouchEvent run");
        boolean value =  super.onInterceptTouchEvent(ev);
        Log.e("mytest","MyLinearLayoutOther onInterceptTouchEvent:"+value);
        return value;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("mytestt","..........MyLinearLayoutOther onTouchEvent run");
        boolean value =  super.onTouchEvent(event);
        Log.e("mytest","MyLinearLayoutOther onTouchEvent:"+value);
        return true;
    }

}
