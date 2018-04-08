package com.leachchen.testview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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

public class ScrollByScrollerView extends LinearLayout {

    private Scroller mScroller;
    private GestureDetector mGestureDetector;


    public ScrollByScrollerView(Context context) {
        this(context, null);
    }

    public ScrollByScrollerView(Context context, AttributeSet attrs) {
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
            smoothScrollBy(0, disY);
            Log.e("mytest","disY:"+disY);
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




   /* private int mLastY;
    *//**
     * 容器中的组件
     *//*
    private View convertView;
    *//**
     * 如果容器中的组件为RecyclerView
     *//*
    private RecyclerView recyclerView;
  *//*  @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                *//**//**
                 * 下面两个判断来自于 BGARefreshLayout 框架中的判断，github 上搜索 BGARefreshLayout
                 *//**//*
                if (convertView instanceof RecyclerView) {
                    if (y - mLastY > 0) {
                        if (isCanPullDown()) {
                            //Log.d(TAG, "滑倒顶部时时间拦截成功");
                            return true;
                        }
                    }

                    if (y - mLastY < 0) {
                        if (isCanPullUp()) {
                            //Log.d(TAG, "滑倒底部时时间拦截成功");
                            return true;
                        }
                    }
                }
                break;
        }

        return false;
    }*//*

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new RuntimeException(ScrollListByScrollerView.class.getSimpleName() + "只能有一个子控件");
        }
        convertView = getChildAt(0);
        //TODO 可以拓展ListView等可滑动的组件
        if (convertView instanceof RecyclerView) {
            recyclerView = (RecyclerView) convertView;
        }
    }

    *//**
     * 判断是否可以下拉
     *
     * @return
     *//*
    private boolean isCanPullDown() {

        final RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (null == adapter) {
            return true;
        }
        //spf  ========================================================
        final int firstVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisiblePosition != 0 && adapter.getItemCount() != 0) {
            return false;
        }
        //===================================================================================
        int mostTop = (recyclerView.getChildCount() > 0) ? (recyclerView.getChildAt(0).getTop()) : 0;
        return mostTop >= 0;
    }


    *//**
     * 判断是否可以上拉
     *
     * @return
     *//*
    private boolean isCanPullUp() {
        final RecyclerView.Adapter adapter = recyclerView.getAdapter();

        if (null == adapter) {
            return true;
        }

        final int lastItemPosition = adapter.getItemCount() - 1;
        final int lastVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

        if (lastVisiblePosition >= lastItemPosition) {
            final int childIndex = lastVisiblePosition - ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            final int childCount = recyclerView.getChildCount();
            final int index = Math.min(childIndex, childCount - 1);
            final View lastVisibleChild = recyclerView.getChildAt(index);
            if (lastVisibleChild != null) {
                return lastVisibleChild.getBottom() <= recyclerView.getBottom() - recyclerView.getTop();
            }
        }

        return false;
    }*/


}
