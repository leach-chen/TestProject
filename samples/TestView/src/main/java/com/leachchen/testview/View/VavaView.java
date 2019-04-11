package com.leachchen.testview.View;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.leachchen.testview.utils.DensityUtils;

/**
 * ClassName:   VavaView.java
 * Description:
 * Author :     leach.chen
 * Date:        2019/4/3 18:31
 **/
public class VavaView extends View {

    private Context mContext;
    private Paint mPaint;
    private Paint mPaintMove;

    private Path mPath1;
    private Path mPathMove1;
    //开始点和结束点1
    private int mStartXPoint1;
    private int mStartYPoint1;
    private int mEndXPoint1;
    private int mEndYPoint1;
    //控制点1
    private int mConXPoint1;
    private int mConYPoint1;

    private Path mPath2;
    private Path mPathMove2;
    //开始点和结束点2
    private int mStartXPoint2;
    private int mStartYPoint2;
    private int mEndXPoint2;
    private int mEndYPoint2;
    //控制点2
    private int mConXPoint2;
    private int mConYPoint2;


    private Path mPath3;
    private Path mPathMove3;
    //开始点和结束点3
    private int mStartXPoint3;
    private int mStartYPoint3;
    private int mEndXPoint3;
    private int mEndYPoint3;
    //控制点3
    private int mConXPoint3;
    private int mConYPoint3;

    private Path mPath4;
    private Path mPathMove4;
    //开始点和结束点4
    private int mStartXPoint4;
    private int mStartYPoint4;
    private int mEndXPoint4;
    private int mEndYPoint4;
    //控制点4
    private int mConXPoint4;
    private int mConYPoint4;



    //移动点
    private int mMoveXPoint;
    private int mMoveYPoint;
    private int mMoveXPointLast;
    private int mMoveYPointLast;

    private int mWidth;
    private int mHeight;

    private int mVavaWidth=0;
    private int mVavaHeight=0;

    private int mPaintWidth;

    private PathMeasure mPathMeasure1,mPathMeasure2,mPathMeasure3,mPathMeasure4;
    private float mPathLength1,mPathLength2,mPathLength3,mPathLength4;
    private float mPathPercent;

    public VavaView(Context context) {
        super(context);
        init(context);
    }

    public VavaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VavaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {

        mContext = context;
        int topleftHeight = DensityUtils.dip2px(mContext,2);
        mVavaWidth = DensityUtils.dip2px(mContext,16) + topleftHeight;

        mStartXPoint1 = topleftHeight;
        mStartYPoint1 = topleftHeight;
        mEndXPoint1 = mVavaWidth;
        mEndYPoint1 = topleftHeight;
        mConXPoint1 = (mVavaWidth /2);
        mConYPoint1 = (int)(mVavaWidth * 2);

        float t = 0.5f;
        float temp = 1 - t;
        mVavaHeight = (int) (temp * temp * mStartYPoint1 + 2 * t * temp * mConYPoint1 + t * t * mEndYPoint1);


        mStartXPoint2 = mVavaWidth;
        mStartYPoint2 = mVavaHeight;
        mEndXPoint2 = mVavaWidth * 2;
        mEndYPoint2 = mVavaHeight;
        mConXPoint2 = mVavaWidth + (mVavaWidth /2);
        mConYPoint2 = mVavaHeight - (int)(mVavaWidth * 2);


        mStartXPoint3 = mVavaWidth*2;
        mStartYPoint3 = topleftHeight;
        mEndXPoint3 = mVavaWidth * 3;
        mEndYPoint3 = topleftHeight;
        mConXPoint3 = mVavaWidth*2 + (mVavaWidth /2);
        mConYPoint3 = (int)(mVavaWidth * 2);

        mStartXPoint4 = mVavaWidth*3;
        mStartYPoint4 = mVavaHeight;
        mEndXPoint4 = mVavaWidth * 4;
        mEndYPoint4 = mVavaHeight;
        mConXPoint4 = mVavaWidth*3 + (mVavaWidth /2);
        mConYPoint4 = mVavaHeight - (int)(mVavaWidth * 2);


        this.mContext = context;
        mPaint = new Paint();
        mPaintMove = new Paint();

        mPath1 = new Path();
        mPathMove1 = new Path();

        mPath2 = new Path();
        mPathMove2 = new Path();

        mPath3 = new Path();
        mPathMove3 = new Path();

        mPath4 = new Path();
        mPathMove4 = new Path();

        mPaintWidth = DensityUtils.dip2px(mContext,2);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#5D5D5D"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mPaintWidth);

        mPaintMove.setStyle(Paint.Style.STROKE);
        mPaintMove.setColor(Color.parseColor("#DFDFDF"));
        mPaintMove.setAntiAlias(true);
        mPaintMove.setStrokeWidth(mPaintWidth);

    }

    @Override
    protected  void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure( widthMeasureSpec,  heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //测量路径长度
        mPath1.moveTo(mStartXPoint1, mStartYPoint1);
        mPath1.quadTo(mConXPoint1, mConYPoint1, mEndXPoint1, mEndYPoint1);
        mPathMeasure1 = new PathMeasure();
        mPathMeasure1.setPath(mPath1, false);
        mPathLength1 = mPathMeasure1.getLength();

        mPath2.moveTo(mStartXPoint2, mStartYPoint2);
        mPath2.quadTo(mConXPoint2, mConYPoint2, mEndXPoint2, mEndYPoint2);
        mPathMeasure2 = new PathMeasure();
        mPathMeasure2.setPath(mPath2, false);
        mPathLength2 = mPathMeasure2.getLength();

        mPath3.moveTo(mStartXPoint3, mStartYPoint3);
        mPath3.quadTo(mConXPoint3, mConYPoint3, mEndXPoint3, mEndYPoint3);
        mPathMeasure3 = new PathMeasure();
        mPathMeasure3.setPath(mPath3, false);
        mPathLength3 = mPathMeasure3.getLength();

        mPath4.moveTo(mStartXPoint4, mStartYPoint4);
        mPath4.quadTo(mConXPoint4, mConYPoint4, mEndXPoint4, mEndYPoint4);
        mPathMeasure4 = new PathMeasure();
        mPathMeasure4.setPath(mPath4, false);
        mPathLength4 = mPathMeasure4.getLength();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //贝塞尔曲线1，绘制第一段底部曲线
        mPath1.reset();
        mPath1.moveTo(mStartXPoint1, mStartYPoint1);
        mPath1.quadTo(mConXPoint1, mConYPoint1, mEndXPoint1, mEndYPoint1);
        canvas.drawPath(mPath1, mPaint);

        if(mPathPercent>=0 && mPathPercent <= 0.25) {   //动态绘制第一段变化曲线
            mPathMove1.reset();
            //mPathMove1.lineTo(0, 0);
            float stop1 = mPathLength1 * mPathPercent*4;
            float start1 = 0;
            mPathMeasure1.getSegment(start1, stop1, mPathMove1, true);
            canvas.drawPath(mPathMove1, mPaintMove);
        }else if(mPathPercent > 0.25)
        {
            mPathMove1.reset();
            //mPathMove1.lineTo(0, 0);
            float stop1 = mPathLength1 * 1;
            float start1 = 0;
            mPathMeasure1.getSegment(start1, stop1, mPathMove1, true);
            canvas.drawPath(mPathMove1, mPaintMove);
        }



        //贝塞尔曲线2
        mPath2.reset();
        mPath2.moveTo(mStartXPoint2, mStartYPoint2);
        mPath2.quadTo(mConXPoint2, mConYPoint2, mEndXPoint2, mEndYPoint2);
        canvas.drawPath(mPath2, mPaint);

        if(mPathPercent > 0.25 && mPathPercent <= 0.5) {
            mPathMove2.reset();
            //mPathMove2.lineTo(0, 0);
            float stop2 = mPathLength2 * (0.25f - (0.5f - mPathPercent))*4;
            float start2 = 0;
            mPathMeasure2.getSegment(start2, stop2, mPathMove2, true);
            canvas.drawPath(mPathMove2, mPaintMove);
        }else  if(mPathPercent > 0.5)
        {
            mPathMove2.reset();
            //mPathMove2.lineTo(0, 0);
            float stop2 = mPathLength2 * 1;
            float start2 = 0;
            mPathMeasure2.getSegment(start2, stop2, mPathMove2, true);
            canvas.drawPath(mPathMove2, mPaintMove);
        }

        //贝塞尔曲线3
        mPath3.reset();
        mPath3.moveTo(mStartXPoint3, mStartYPoint3);
        mPath3.quadTo(mConXPoint3, mConYPoint3, mEndXPoint3, mEndYPoint3);
        canvas.drawPath(mPath3, mPaint);

        if(mPathPercent > 0.5 && mPathPercent <= 0.75) {
            mPathMove3.reset();
            //mPathMove3.lineTo(0, 0);
            float stop3 = mPathLength3 * (0.25f - (0.75f - mPathPercent))*4;
            float start3 = 0;
            mPathMeasure3.getSegment(start3, stop3, mPathMove3, true);
            canvas.drawPath(mPathMove3, mPaintMove);
        }else  if(mPathPercent > 0.75)
        {
            mPathMove3.reset();
            //mPathMove3.lineTo(0, 0);
            float stop3 = mPathLength3 * 1;
            float start3 = 0;
            mPathMeasure3.getSegment(start3, stop3, mPathMove3, true);
            canvas.drawPath(mPathMove3, mPaintMove);
        }

        //贝塞尔曲线4
        mPath4.reset();
        mPath4.moveTo(mStartXPoint4, mStartYPoint4);
        mPath4.quadTo(mConXPoint4, mConYPoint4, mEndXPoint4, mEndYPoint4);
        canvas.drawPath(mPath4, mPaint);

        if(mPathPercent > 0.75 && mPathPercent <= 1) {
            mPathMove4.reset();
            //mPathMove4.lineTo(0, 0);
            float stop4 = mPathLength4 * (0.25f - (1.0f - mPathPercent))*4;
            float start4 = 0;
            mPathMeasure4.getSegment(start4, stop4, mPathMove4, true);
            canvas.drawPath(mPathMove4, mPaintMove);
        }else  if(mPathPercent >= 1)
        {
            mPathMove4.reset();
            //mPathMove4.lineTo(0, 0);
            float stop4 = mPathLength4 * 1;
            float start4 = 0;
            mPathMeasure4.getSegment(start4, stop4, mPathMove4, true);
            canvas.drawPath(mPathMove4, mPaintMove);
        }
    }

    ValueAnimator valueAnimator;
    public void startAnimation()
    {
        /*valueAnimator = ValueAnimator.ofObject(new CirclePointEvaluator(), new Point(mStartXPoint, mStartYPoint),new Point(mEndXPoint, mEndYPoint));
        valueAnimator.setDuration(1500);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                mMoveXPoint = point.x;
                mMoveYPoint = point.y;
                invalidate();
                Log.e("mytest","mMoveXPoint:"+mMoveXPoint+" mMoveYPoint:"+mMoveYPoint);
            }
        });
        valueAnimator.start();*/


        valueAnimator= ValueAnimator.ofFloat(0, 1);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPathPercent = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void stopAnimation()
    {
        valueAnimator.cancel();
    }

    /**
     * 0~100的数值
     * @param process
     */
    public void setProcess(int process)
    {
        mPathPercent = process / 100.0f;
        invalidate();
    }

    /**
     * 自定义Evaluator
     */
    public class CirclePointEvaluator implements TypeEvaluator {

        /**
         * @param t          当前动画进度
         * @param startValue 开始值
         * @param endValue   结束值
         * @return
         */
        @Override
        public Object evaluate(float t, Object startValue, Object endValue) {

            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;

            float temp = 1 - t;
            int x = (int) (temp * temp * startPoint.x + 2 * t * temp * mConXPoint1 + t * t * endPoint.x);
            int y = (int) (temp * temp * startPoint.y + 2 * t * temp * mConYPoint1 + t * t * endPoint.y);

            return new Point(x,y);
        }

    }

}

