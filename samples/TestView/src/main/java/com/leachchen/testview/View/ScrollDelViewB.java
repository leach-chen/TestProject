package com.leachchen.testview.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.leachchen.testview.R;

/**
 * ClassName:   ScrollDelView.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/12 15:02
 **/

public class ScrollDelViewB extends LinearLayout {


    //检测手指在滑动过程中的速度
    private VelocityTracker mVelocityTracker;
    private Scroller mScroller;
    private int mLastX;



    public ScrollDelViewB(Context context) {
        this(context,null);
    }

    public ScrollDelViewB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);


        mScroller = new Scroller(context, new LinearInterpolator());
        mVelocityTracker = VelocityTracker.obtain();

        View view = LayoutInflater.from(context).inflate(R.layout.scroll_del_linearlayout, null);
        this.addView(view);
    }

    public ScrollDelViewB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset())
        {
            Log.e("mytestt","mScroller.getCurrX():"+mScroller.getCurrX()+" mScroller.getFinalY():"+mScroller.getFinalY());
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        mVelocityTracker.clear();
        mVelocityTracker.addMovement(e);

        int x = (int)e.getX();

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) e.getX() - mLastX;
                mScroller.startScroll(mScroller.getFinalX(),mScroller.getFinalY(),-dx,0);
                //mScroller.fling(mScroller.getFinalX(),mScroller.getFinalY(),-(int)xVelocity,(int)yVelocity,-1000,1000,0,0);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:

/*              mVelocityTracker.computeCurrentVelocity(1000);//计算手指滑动的速度
                float xVelocity = mVelocityTracker.getXVelocity();//水平方向速度（向左为负）
                float yVelocity = mVelocityTracker.getYVelocity();//垂直方向速度
                mScroller.fling(mScroller.getFinalX(),mScroller.getFinalY(),-(int)xVelocity,(int)yVelocity,-1000,1000,0,0);*/

                mScroller.startScroll(mScroller.getFinalX(),mScroller.getFinalY(),0 - mScroller.getFinalX(),0,1000);
                invalidate();
                break;
        }
        mLastX = x;
        return super.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }

}
