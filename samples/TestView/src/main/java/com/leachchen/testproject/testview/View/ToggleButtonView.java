package com.leachchen.testproject.testview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

import com.leachchen.testview.R;


/**
 * ClassName:   ToggleButton.java
 * Description:
 * Author :     leach.chen
 * Date:        2017/2/22 15:48
 **/

public class ToggleButtonView extends ViewGroup{

    private int mTouchSlop;
    private Scroller mScroller;
    private boolean isOpen;
    //是否是一次有效的开关操作
    private boolean isValidToggle;
    private int mLastX;


    public ToggleButtonView(Context context) {
        this(context,null);
    }

    public ToggleButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller  = new Scroller(context);
        ImageView slide = new ImageView(context);
        slide.setBackgroundResource(R.mipmap.slide);
        slide.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        setBackgroundResource(R.mipmap.background);
        addView(slide);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
            View view = getChildAt(0);
            view.layout(0,0,getMeasuredWidth() / 2,getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = true;
        int x = (int)ev.getX();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                if(!mScroller.isFinished())
                {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(mLastX - x) > mTouchSlop)
                {
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
        }
        mLastX = x;
        Log.d("aaaa","intercepted:"+intercepted);
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = mLastX - x;
                Log.d("aaaa","deltaX:"+deltaX+" getScrollX:"+getScrollX()+" deltaX+getScrollX():"+(deltaX+getScrollX()));
               if(deltaX+getScrollX() > 0)
                {
                    scrollTo(0,0);
                    return true;
                }else if(Math.abs(getScrollX()+deltaX) >= getMeasuredWidth() / 2)
                {
                    scrollTo(-getMeasuredWidth() / 2,0);
                    return true;
                }
                scrollBy(deltaX,0);
                break;
            case MotionEvent.ACTION_UP:
                smoothScroll();
                break;

        }
        mLastX = x;
        return super.onTouchEvent(event);
    }

    private void smoothScroll() {
        int deltaX = 0;
        if(getScrollX() >= -getMeasuredWidth()/4)
        {
            deltaX = -getScrollX();
        }

        if(getScrollX() < -getMeasuredWidth()/4)
        {
            deltaX = - getMeasuredWidth() / 2 - getScrollX();
        }
        mScroller.startScroll(getScrollX(),0,deltaX,0,500);
        invalidate();
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset())
        {
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }







}
