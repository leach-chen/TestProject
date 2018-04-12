package com.leachchen.testview.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;
import android.widget.TextView;

import com.leachchen.testview.R;

/**
 * ClassName:   ItemRemoveRecyclerView.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/12 18:51
 **/

public class ItemRemoveRecyclerView extends RecyclerView {
    private Context mContext;

    //上一次的触摸点
    private int mLastX, mLastY;
    //当前触摸的item的位置
    private int mPosition;

    //item对应的布局
    private View mItemLayout;
    //删除按钮
    private TextView mDelete;

    //最大滑动距离(即删除按钮的宽度)
    private int mMaxLength;
    //是否在垂直滑动列表
    private boolean isDragging;
    //item是在否跟随手指移动
    private boolean isItemMoving;

    //item是否开始自动滑动
    private boolean isStartScroll;
    //删除按钮状态   0：关闭 1：将要关闭 2：将要打开 3：打开
    private int mDeleteBtnState;

    //检测手指在滑动过程中的速度
    private VelocityTracker mVelocityTracker;
    private Scroller mScroller;
    private RecyListViewOnItemClick mListener;
    private int item_delete;
    /**设置删除监听*/
    public void setmListener(RecyListViewOnItemClick mListener) {
        this.mListener = mListener;
    }

    public int getItem_delete() {
        return item_delete;
    }
    /**设置删除按钮*/
    public void setItem_delete(int item_delete) {
        this.item_delete = item_delete;
    }

    public ItemRemoveRecyclerView(Context context) {
        this(context, null);
    }

    public ItemRemoveRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemRemoveRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;

        mScroller = new Scroller(context, new LinearInterpolator());
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(mItemLayout==null ||mItemLayout.getScrollX()!=0){
                    return true;
                }
                break;
        }
        return super.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        mVelocityTracker.addMovement(e);

        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(isItemMoving);
                if (mDeleteBtnState == 0) {
                    View view = findChildViewUnder(x, y);
                    if (view == null) {
                        return false;
                    }

                    ViewHolder viewHolder = getChildViewHolder(view);

                    mItemLayout = viewHolder.itemView;
                    mItemLayout.setClickable(true);
                    mPosition = viewHolder.getAdapterPosition();

                    mDelete = (TextView) mItemLayout.findViewById(R.id.tv_channel);
                    mMaxLength = mDelete.getWidth();
                    mDelete.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.onItemClick(v,mPosition);
                            mItemLayout.scrollTo(0, 0);
                            mDeleteBtnState = 0;
                        }
                    });
                } else if (mDeleteBtnState == 3){
                    mScroller.startScroll(mItemLayout.getScrollX(), 0, -mMaxLength, 0, 200);
                    invalidate();
                    mDeleteBtnState = 0;
                    return false;
                }else{
                    return false;
                }

                break;
            case MotionEvent.ACTION_MOVE:

                int dx = mLastX - x;
                int dy = mLastY - y;

                if(dy<80 && x>=mItemLayout.getWidth()/2 || mItemLayout.getScrollX()>0){
                    isItemMoving=true;
                    int scrollX = mItemLayout.getScrollX();
                    if (Math.abs(dx) > Math.abs(dy)) {//左边界检测
                        if (scrollX + dx <= 0) {
                            mItemLayout.scrollTo(0, 0);
                            return true;
                        } else if (scrollX + dx >= mMaxLength) {//右边界检测
                            mItemLayout.scrollTo(mMaxLength, 0);
                            return true;
                        }
                        mItemLayout.scrollBy(dx, 0);//item跟随手指滑动
                    }
                }else{
                    isItemMoving=false;
                }
                getParent().requestDisallowInterceptTouchEvent(isItemMoving);
                return false;
            case MotionEvent.ACTION_UP:

                mVelocityTracker.computeCurrentVelocity(1000);//计算手指滑动的速度
                float xVelocity = mVelocityTracker.getXVelocity();//水平方向速度（向左为负）
                float yVelocity = mVelocityTracker.getYVelocity();//垂直方向速度

                int deltaX = 0;
                int upScrollX = mItemLayout.getScrollX();

                if (Math.abs(xVelocity) > 100 && Math.abs(xVelocity) > Math.abs(yVelocity)) {
                    if (xVelocity <= -100) {//左滑速度大于100，则删除按钮显示
                        deltaX = mMaxLength - upScrollX;
                        mDeleteBtnState = 2;
                    } else if (xVelocity > 100) {//右滑速度大于100，则删除按钮隐藏
                        deltaX = -upScrollX;
                        mDeleteBtnState = 1;
                    }
                } else {
                    if (upScrollX >= mMaxLength / 2) {//item的左滑动距离大于删除按钮宽度的一半，则则显示删除按钮
                        deltaX = mMaxLength - upScrollX;
                        mDeleteBtnState = 2;
                    } else if (upScrollX < mMaxLength / 2) {//否则隐藏
                        deltaX = -upScrollX;
                        mDeleteBtnState = 1;
                    }
                }

                //item自动滑动到指定位置
                mScroller.startScroll(upScrollX, 0, deltaX, 0, 200);
                isStartScroll = true;
                getParent().requestDisallowInterceptTouchEvent(false);

                mVelocityTracker.clear();
                break;
        }

        mLastX = x;
        mLastY = y;
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mItemLayout.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        } else if (isStartScroll) {
            isStartScroll = false;
            if (mDeleteBtnState == 1) {
                mDeleteBtnState = 0;
            }

            if (mDeleteBtnState == 2) {
                mDeleteBtnState = 3;
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        isDragging = state == SCROLL_STATE_DRAGGING;
    }


    public interface RecyListViewOnItemClick
    {
        void onItemClick(View v,int position);
    }
}
