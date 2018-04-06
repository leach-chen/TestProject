package com.leachchen.testview.View;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * ClassName:   ScrollerBackListView.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/6 15:17
 **/
public class ScrollListByScrollerView extends LinearLayout {
    /**
     * 容器中的组件
     */
    private View convertView;
    /**
     * 如果容器中的组件为RecyclerView
     */
    private RecyclerView recyclerView;
    /**
     * 滚动结束
     */
    private int mStart;
    /**
     * 滚动结束
     */
    private int mEnd;
    /**
     * 上一次滑动的坐标
     */
    private int mLastY;
    /**
     * 滚动辅助类
     */
    private Scroller mScroller;


    public ScrollListByScrollerView(Context context) {
        this(context, null);
    }

    public ScrollListByScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

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

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            View view = getChildAt(0);
            view.layout(left, top, right, bottom);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        Log.e("mytest","y:"+y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();  //终止动画
                }
                Log.e("mytest","mLastY - y:"+(mLastY - y));
                scrollTo(0, (int) ((mLastY - y) * 0.4));
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                /**
                 * 回弹动画，第一二个参数为开始的x，y
                 * 第三个和第四个参数为滚动的距离（注意方向问题）
                 * 第五个参数是回弹时间
                 */
                mScroller.startScroll(0, mEnd, 0, -dScrollY, 1000);
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                Log.e("mytest","mLastY:"+mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                /**
                 * 下面两个判断来自于 BGARefreshLayout 框架中的判断，github 上搜索 BGARefreshLayout
                 */
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
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }

    /**
     * 判断是否可以下拉
     *
     * @return
     */
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


    /**
     * 判断是否可以上拉
     *
     * @return
     */
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
    }

}
