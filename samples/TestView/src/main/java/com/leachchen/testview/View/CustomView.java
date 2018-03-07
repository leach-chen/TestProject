package com.leachchen.testview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * ClassName:   MyScrollView.java
 * Description:
 * Author :     leach.chen
 * Date:        2017/2/21 16:11
 **/

public class CustomView extends LinearLayout {

    private Scroller mScroller;
    private GestureDetector mGestureDetector;


    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
        setLongClickable(true);
        init(context);
    }

    private void init(Context context)
    {
        mScroller = new Scroller(context);
        mGestureDetector = new GestureDetector(context,new CustomGestureListener());
    }


    private void smoothScrollTo(int fx,int fy)
    {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx,dy);
    }

    private void smoothScrollBy(int dx,int dy)
    {
        mScroller.startScroll(mScroller.getFinalX(),mScroller.getFinalY(),dx,dy);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset())
        {
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                smoothScrollTo(0, 0);
                break;
            default:
                return mGestureDetector.onTouchEvent(event);
        }

        return super.onTouchEvent(event);
    }



    class CustomGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            int disX = (int)((v-0.5)/2);
            int disY = (int)((v1-0.5)/2);
            smoothScrollBy(disX, disY);
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    }


}
