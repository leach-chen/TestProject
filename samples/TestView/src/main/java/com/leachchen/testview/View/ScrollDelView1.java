package com.leachchen.testview.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.leachchen.testview.R;

/**
 * ClassName:   ScrollDelView.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/12 15:02
 **/

public class ScrollDelView1 extends LinearLayout {

    private int mLastX;
    private int distance;
    private int touchSlop;
    private LinearLayout ll_scroll;
    private FrameLayout fl_delpart;
    private int mDelPartWidth;

    public ScrollDelView1(Context context) {
        this(context,null);
    }

    public ScrollDelView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);

        View view = LayoutInflater.from(context).inflate(R.layout.scroll_del_linearlayout, null);
        this.addView(view);

        ll_scroll = (LinearLayout)this.findViewById(R.id.ll_scroll);
        fl_delpart = (FrameLayout) this.findViewById(R.id.fl_delpart);

        ViewConfiguration configuration = ViewConfiguration.get(context);
        touchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);

        fl_delpart.post(new Runnable() {
            @Override
            public void run() {
                mDelPartWidth = fl_delpart.getWidth();
            }
        });

    }

    public ScrollDelView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int x = (int)e.getX();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) e.getX() - mLastX;
                distance += dx;

                if(Math.abs(distance) > mDelPartWidth)
                {
                    distance = -mDelPartWidth;
                }

                if(distance > 0)
                {
                    distance = 0;
                }

                if(distance <= 0) {
                    ll_scroll.scrollTo(-distance, 0);
                }

                break;
            case MotionEvent.ACTION_UP:

                if(Math.abs(distance) >= mDelPartWidth /2)
                {
                    distance = -mDelPartWidth;
                    ll_scroll.scrollTo(-distance, 0);
                    Log.e("mytest","aaa");
                }else
                {
                    distance = 0;
                    ll_scroll.scrollTo(distance, 0);
                    Log.e("mytest","bbb");
                }

                Log.e("mytest","distance:"+distance);
                break;
        }
        mLastX = x;
        return super.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }


/*    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return true;
    }*/
}
