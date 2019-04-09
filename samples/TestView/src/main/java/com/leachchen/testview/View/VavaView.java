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
    private Path mPath;
    private Path mPathMove;

    //开始点和结束点
    private int mStartXPoint;
    private int mStartYPoint;
    private int mEndXPoint;
    private int mEndYPoint;
    //控制点
    private int mConXPoint;
    private int mConYPoint;
    //移动点
    private int mMoveXPoint;
    private int mMoveYPoint;
    private int mMoveXPointLast;
    private int mMoveYPointLast;

    private int mWidth;
    private int mHeight;

    private float mVavaWidth=50;

    private PathMeasure mPathMeasure;
    private float mPathLength;
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

        mStartXPoint = 0;
        mStartYPoint = 0;
        mEndXPoint = 38;
        mEndYPoint = 0;
        mConXPoint = 19;
        mConYPoint = 120;

        this.mContext = context;
        mPaint = new Paint();
        mPaintMove = new Paint();

        mPath = new Path();
        mPathMove = new Path();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3);

        mPaintMove.setStyle(Paint.Style.STROKE);
        mPaintMove.setColor(Color.parseColor("#914847"));
        mPaintMove.setAntiAlias(true);
        mPaintMove.setStrokeWidth(3);

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
        mPath.moveTo(mStartXPoint, mStartYPoint);
        mPath.quadTo(mConXPoint, mConYPoint, mEndXPoint, mEndYPoint);
        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(mPath, false);
        mPathLength = mPathMeasure.getLength();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //贝塞尔曲线
        mPath.moveTo(mStartXPoint, mStartYPoint);
        mPath.quadTo(mConXPoint, mConYPoint, mEndXPoint, mEndYPoint);
        canvas.drawPath(mPath, mPaint);

        mPathMove.reset();
        mPathMove.lineTo(0, 0);
        float stop = mPathLength * mPathPercent;
        float start = 0;
        mPathMeasure.getSegment(start, stop, mPathMove, true);
        canvas.drawPath(mPathMove, mPaintMove);

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
        //anim.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(1500);
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
            int x = (int) (temp * temp * startPoint.x + 2 * t * temp * mConXPoint + t * t * endPoint.x);
            int y = (int) (temp * temp * startPoint.y + 2 * t * temp * mConYPoint + t * t * endPoint.y);

            return new Point(x,y);
        }

    }

}

