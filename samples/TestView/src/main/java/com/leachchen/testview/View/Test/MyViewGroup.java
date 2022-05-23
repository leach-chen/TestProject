package com.leachchen.testview.View.Test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i=0;i < getChildCount();i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            //childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //super.onLayout(changed,left,top,right,bottom);
        for(int i = 0; i < getChildCount(); i++)
        {
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            int mLeft = (right - width) / 2;
            int mTop = (bottom - height) / 2;
            child.layout(mLeft,mTop,mLeft+width,mTop+height);
        }
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