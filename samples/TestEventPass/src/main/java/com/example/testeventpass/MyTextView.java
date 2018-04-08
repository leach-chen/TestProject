package com.example.testeventpass;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static android.R.attr.value;

/**
 * ClassName:   MyTextView.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/8 11:08
 **/

public class MyTextView extends android.support.v7.widget.AppCompatTextView{

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_dark));
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("mytestt","..........MyTextView dispatchTouchEvent");
        boolean value =  super.dispatchTouchEvent(ev);
        Log.e("mytest","MyTextView dispatchTouchEvent:"+value);
        return value;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("mytestt","..........MyTextView onTouchEvent:");
        boolean value =  super.onTouchEvent(event);
        Log.e("mytest","MyTextView onTouchEvent:"+value);
/*        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Log.e("mytesttt","MyTextView onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("mytesttt","MyTextView onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("mytesttt","MyTextView onTouchEvent ACTION_UP");
                break;
        }*/
        return value;
    }

}
