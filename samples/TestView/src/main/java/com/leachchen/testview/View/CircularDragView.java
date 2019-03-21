package com.leachchen.testview.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.leachchen.testview.R;
import com.leachchen.testview.utils.BitmapUtils;
import com.leachchen.testview.utils.ChartUtils;
import com.leachchen.testview.utils.DensityUtils;

/**
 * ClassName:   CircularDrag.java
 * Description:
 * Author :     leach.chen
 * Date:        2019/3/12 18:29
 **/
public class CircularDragView extends View {


    private Context mContext;
    private Paint mPaint;
    private int mWidth, mHeight;
    private SweepGradient mSweepGradient;

    /**
     * 线条宽度
     */
    private int mLineStrokeWidth;


    private Bitmap mDragBitmap;
    private int mCenterX, mCenterY;
    /**
     * 圆半径
     */
    private int mRadius;
    private float mCurrentAngle;
    private Paint mBitmapPaint;

    /**
     * 最小有效点击半径
     */
    private int mMinValidateTouchArcRadius;
    /**
     * 最大有效点击半径
     */
    private int mMaxValidateTouchArcRadius;

    private boolean mIsTouchOnArc;

    /**
     * 上一次的弧边角
     */
    private float lastAngle;
    /**
     * 上一次点所在象限
     */
    private int lastQuadrant = 1;

    public CircularDragView(Context context) {
        super(context);
        init(context);
    }

    public CircularDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircularDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        this.mContext = context;
        mLineStrokeWidth = DensityUtils.dip2px(context, 30);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth((float) mLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.TRANSPARENT);
        mPaint.setColor(Color.BLACK);

        mBitmapPaint = new Paint();
        mBitmapPaint.setDither(true);
        mBitmapPaint.setFilterBitmap(true);
        mBitmapPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        mPaint.setShader(mSweepGradient);
        RectF rectF = new RectF(30,30,mWidth-30,mHeight-30);
        canvas.drawArc(rectF,0,360,false,mPaint);

        drawDragBitmap(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int[] colors = {Color.RED,Color.GREEN,Color.YELLOW};
        mSweepGradient = new SweepGradient(mWidth/2,mHeight/2, colors,null);
        mDragBitmap  = BitmapFactory.decodeResource(getResources(), R.mipmap.ring_dot);
        mDragBitmap = BitmapUtils.conversionBitmap(mDragBitmap,DensityUtils.dip2px(mContext,
                40), DensityUtils.dip2px(mContext, 40));
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        int minXY = Math.min(mCenterX, mCenterY);
        mRadius = minXY - mLineStrokeWidth / 2;


        mMinValidateTouchArcRadius = (int) (mRadius - mDragBitmap.getWidth() / 2 * 1.5f);
        mMaxValidateTouchArcRadius = (int) (mRadius + mDragBitmap.getWidth() / 2 * 1.5f);
    }

    private void drawDragBitmap(Canvas canvas) {
        PointF progressPoint = ChartUtils.calcArcEndPointXY(mCenterX, mCenterY, mRadius,mCurrentAngle, 180);
        int left = (int) progressPoint.x - mDragBitmap.getWidth() / 2;
        int top = (int) progressPoint.y - mDragBitmap.getHeight() / 2;
        canvas.drawBitmap(mDragBitmap, left, top, mBitmapPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST)
        {
            mWidth = widthSpecSize;
        }else
        {
            mWidth = 0;
        }

        if(heightSpecMode == MeasureSpec.EXACTLY || heightSpecMode == MeasureSpec.AT_MOST)
        {
            mHeight = widthSpecSize;
        }else
        {
            mHeight = 0;
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    /**
     * 按下时判断按下的点是否按在圆边范围内
     *
     * @param x x坐标点
     * @param y y坐标点
     */
    private boolean isTouchArc(float x, float y) {
        double d = getTouchRadius(x, y);
        return d >= mMinValidateTouchArcRadius && d <= mMaxValidateTouchArcRadius;
    }

    /**
     * 计算某点到圆点的距离
     *
     * @param x x坐标点
     * @param y y坐标点
     */
    private double getTouchRadius(float x, float y) {
        float cx = x - getWidth() / 2;
        float cy = y - getHeight() / 2;
        return Math.hypot(cx, cy);
    }

    /**
     * 更新当前进度对应弧度
     *
     * @param x 按下x坐标点
     * @param y 按下y坐标点
     */
    private void updateCurrentAngle(float x, float y) {
        //根据坐标转换成对应的角度
        float pointX = x - mCenterX;
        float pointY = y - mCenterY;
        float tan_x;//根据左边点所在象限处理过后的x值
        float tan_y;//根据左边点所在象限处理过后的y值
        double atan;//所在象限弧边angle

        //01：第一象限-右上角区域
        if (pointX >= 0 && pointY <= 0 && lastQuadrant != 3 && lastAngle > 0.f) {
            tan_x = pointX;
            tan_y = pointY * (-1);
            atan = Math.atan(tan_x / tan_y);//求弧边
            mCurrentAngle = (int) Math.toDegrees(atan) + 90.f;
            lastQuadrant = 1;
        }

        //02：第二象限-左上角区域
        if (pointX <= 0 && pointY <= 0) {
            tan_x = pointX * (-1);
            tan_y = pointY * (-1);
            atan = Math.atan(tan_y / tan_x);//求弧边
            mCurrentAngle = (int) Math.toDegrees(atan);
            if (lastAngle >= 270.f) {
                mCurrentAngle = 359.f;
                lastQuadrant = 3;
            } else {
                lastQuadrant = 2;
            }
        }

        //03：第三象限-左下角区域
        if (pointX <= 0 && pointY >= 0) {
            tan_x = pointX * (-1);
            tan_y = pointY;
            atan = Math.atan(tan_x / tan_y);//求弧边
            mCurrentAngle = (int) Math.toDegrees(atan) + 270f;
            if (lastAngle < 90.f) {
                mCurrentAngle = 0.f;
                lastQuadrant = 2;
            } else {
                lastQuadrant = 3;
            }
        }

        //04：第四象限-右下角区域
        if (pointX >= 0 && pointY >= 0 && lastQuadrant != 2 && lastAngle < 359.f) {
            tan_x = pointX;
            tan_y = pointY;
            atan = Math.atan(tan_y / tan_x);//求弧边
            mCurrentAngle = (int) Math.toDegrees(atan) + 180f;
            lastQuadrant = 4;
        }
        lastAngle = mCurrentAngle;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取点击位置的坐标
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isTouchArc(x, y)) {
                    mIsTouchOnArc = true;
                    updateCurrentAngle(x, y);
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsTouchOnArc) {
                    updateCurrentAngle(x, y);

                }
                break;
            case MotionEvent.ACTION_UP:
                mIsTouchOnArc = false;

                break;
        }

        invalidate();
        return true;
    }


}
