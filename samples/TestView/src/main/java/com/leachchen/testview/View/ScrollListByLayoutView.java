package com.leachchen.testview.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:   ScrollBackListView.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/6 13:21
 **/
public class ScrollListByLayoutView extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    //listview 或者recyclerview或者ScrollView
    private View mChildView;
    // 用于记录正常的布局位置
    private Rect originalRect = new Rect();
    //滚动时，移动的view和位置
    private List<View> mMoveViews = new ArrayList<View>();
    private List<Rect> mMoveRects = new ArrayList<Rect>();
    // 如果按下时不能上拉和下拉， 会在手指移动时更新为当前手指的Y值
    private float startY;

    // 在手指滑动的过程中记录是否移动了布局
    private boolean isMoved = false;
    /**
     * 滚动时间
     */
    private static final long ANIM_TIME = 400;

    //阻尼
    private static final float OFFSET_RADIO = 0.5f;
    private boolean isRecyclerReuslt = false;

    public ScrollListByLayoutView(Context context) {
        this(context, null);
    }

    public ScrollListByLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 加载布局后初始化,这个方法会在加载完布局后调用
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i) instanceof RecyclerView || getChildAt(i) instanceof ListView || getChildAt(i) instanceof ScrollView) {
                    if (mChildView == null) {
                        mChildView = getChildAt(i);
                    } else {
                        throw new RuntimeException("PullRecyclerViewGroup 中只能存在一个RecyclerView、ListView或者ScrollView");
                    }
                }
            }
        }

        if (mChildView == null) {
            throw new RuntimeException("PullRecyclerViewGroup 子容器中必须有一个RecyclerView、ListView或者ScrollView");
        }
        //布局重绘监听，比如华为屏幕键盘可以弹出和隐藏，改变布局，加监听就可以虽键盘弹出关闭的变化而变化
        getViewTreeObserver().addOnGlobalLayoutListener(this);
        super.onFinishInflate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        originalRect.set(mChildView.getLeft(), mChildView.getTop(), mChildView.getRight(), mChildView.getBottom());
        for (int i = 0; i < mMoveViews.size(); i++) {
            final View v = mMoveViews.get(i);
            v.addOnLayoutChangeListener(new OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    Rect rect = new Rect();
                    rect.set(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                    mMoveRects.add(rect);
                    v.removeOnLayoutChangeListener(this);
                }
            });
        }
    }

    /**
     * 事件分发
     */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mChildView == null) {
            return super.dispatchTouchEvent(ev);
        }

        boolean isTouchOutOfScrollView = ev.getY() >= originalRect.bottom || ev.getY() <= originalRect.top; //如果当前view的Y上的位置
        if (isTouchOutOfScrollView) {//如果不在view的范围内
            if (isMoved) {      //当前容器已经被移动
                recoverLayout();
            }
            return true;
        }
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //记录按下时的Y
                startY = ev.getY();
            case MotionEvent.ACTION_MOVE:
                float nowY = ev.getY();
                int scrollY = (int) (nowY - startY);
                if ((isCanPullDown() && scrollY > 0) || (isCanPullUp() && scrollY < 0) || (isCanPullDown() && isCanPullUp())) {
                    int offset = (int) (scrollY * OFFSET_RADIO);
                    mChildView.layout(originalRect.left, originalRect.top + offset, originalRect.right, originalRect.bottom + offset);
                    for (int i = 0; i < mMoveViews.size(); i++) {
                        if (mMoveViews.get(i) != null) {
                            mMoveViews.get(i).layout(mMoveRects.get(i).left, mMoveRects.get(i).top + offset, mMoveRects.get(i).right, mMoveRects.get(i).bottom + offset);
                        }
                    }
                    isMoved = true;
                    isRecyclerReuslt = false;
                    return true;
                } else {
                    startY = ev.getY();
                    isMoved = false;
                    isRecyclerReuslt = true;
                    recoverLayout();
                    return super.dispatchTouchEvent(ev);
                }
            case MotionEvent.ACTION_UP:

                if (isMoved) {
                    recoverLayout();
                }

                if (isRecyclerReuslt) {
                    return super.dispatchTouchEvent(ev);
                } else {
                    return true;
                }
            default:
                return true;
        }
    }

    /**
     * 位置还原
     */
    private void recoverLayout() {
        if (!isMoved) {
            return;//如果没有移动布局，则跳过执行
        }


        for (int i = 0; i < mMoveViews.size(); i++) {
            if (mMoveRects.get(i) != null) {
                TranslateAnimation anims = new TranslateAnimation(0, 0, mMoveViews.get(i).getTop(), mMoveRects.get(i).top);
                anims.setDuration(ANIM_TIME);
                mMoveViews.get(i).startAnimation(anims);
                mMoveViews.get(i).layout(mMoveRects.get(i).left, mMoveRects.get(i).top, mMoveRects.get(i).right, mMoveRects.get(i).bottom);
            }

        }

        TranslateAnimation anim = new TranslateAnimation(0, 0, mChildView.getTop() - originalRect.top, 0);
        anim.setDuration(ANIM_TIME);
        mChildView.startAnimation(anim);

        mChildView.layout(originalRect.left, originalRect.top, originalRect.right, originalRect.bottom);

        isMoved = false;

    }

    /**
     * 容器的的事件都在事件分发中处理，这里处理的是事件分发传递过来的事件，
     * <p>
     * 传递过来的为RecyclerVIew的事件  不拦截，直接交给reyclerview处理
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;  //不拦截  直接传递给子的view
    }

    /**
     * 判断是否可以下拉
     *
     * @return
     */
    private boolean isCanPullDown() {

        final RecyclerView.Adapter adapter = ((RecyclerView) mChildView).getAdapter();
        if (null == adapter) {
            return true;
        }
        //spf  ========================================================
        final int firstVisiblePosition = ((LinearLayoutManager) ((RecyclerView) mChildView).getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisiblePosition != 0 && adapter.getItemCount() != 0) {
            return false;
        }
        //===================================================================================
        int mostTop = (((RecyclerView) mChildView).getChildCount() > 0) ? ((RecyclerView) mChildView).getChildAt(0).getTop() : 0;
        return mostTop >= 0;
    }


    /**
     * 判断是否可以上拉
     *
     * @return
     */
    private boolean isCanPullUp() {
        final RecyclerView.Adapter adapter = ((RecyclerView) mChildView).getAdapter();

        if (null == adapter) {
            return true;
        }

        final int lastItemPosition = adapter.getItemCount() - 1;
        final int lastVisiblePosition = ((LinearLayoutManager) ((RecyclerView) mChildView).getLayoutManager()).findLastVisibleItemPosition();

        if (lastVisiblePosition >= lastItemPosition) {
            final int childIndex = lastVisiblePosition - ((LinearLayoutManager) ((RecyclerView) mChildView).getLayoutManager()).findFirstVisibleItemPosition();
            final int childCount = ((RecyclerView) mChildView).getChildCount();
            final int index = Math.min(childIndex, childCount - 1);
            final View lastVisibleChild = ((RecyclerView) mChildView).getChildAt(index);
            if (lastVisibleChild != null) {
                return lastVisibleChild.getBottom() <= mChildView.getBottom() - mChildView.getTop();
            }
        }

        return false;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onGlobalLayout() {
        //华为手机屏幕下方的返回、home键显示隐藏改变布局
        requestLayout();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    /**
     * 跟随弹性移动的view
     *
     * @param view
     */
    public void setMoveViews(View view) {
        this.mMoveViews.add(view);
        requestLayout();
    }
}
