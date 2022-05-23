package com.leachchen.testview.View.Test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("mytest", "MyView dispatchTouchEvent Custom: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("mytest", "MyView dispatchTouchEvent Custom: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("mytest", "MyView dispatchTouchEvent Custom: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("mytest", "MyView dispatchTouchEvent Custom: ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(ev);//默认也是FALSE
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("mytest", "MyView onTouchEvent Custom: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("mytest", "MyView onTouchEvent Custom: ACTION_MOVE");
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("mytest", "MyView onTouchEvent Custom: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("mytest", "MyView onTouchEvent Custom: ACTION_CANCEL");
                break;
        }
        return true;//默认也是FALSE
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //setMeasuredDimension(100,100);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        int result = specSize;
        if (specMode == MeasureSpec.AT_MOST) {
            result = 500;
        }
        setMeasuredDimension(result, result);
    }

}
