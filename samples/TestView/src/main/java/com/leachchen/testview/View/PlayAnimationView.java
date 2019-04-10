package  com.leachchen.testview.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.leachchen.testview.utils.DisplayUtil;


/**
 * ClassName:   PlayAnimationView.java
 * Description:
 * Author :     leach.chen
 * Date:        2019/4/4 9:54
 **/
public class PlayAnimationView extends View {

    private Paint mPaint;
    private Paint mPaintTriangle;
    private Path mPath1;//绘制左半圆
    private Path mPath2;//绘制右半圆
    private Path mPath3;//绘制上边左侧线条
    private Path mPath4;//绘制上边右边线条
    private Path mPath5;//绘制下边左侧线条
    private Path mPath6;//绘制下边右侧线条
    private Path mPath7;//绘制三角形

    private int mWidth;
    private int mHeight;


    private float mCenterX; //中心坐标
    private float mCenterY;//中心坐标
    private int mRadius = 50; //圆半径
    private int mDistance = 50;//水平移动距离
    private float mPercent = 0.1f;//距离变化百分比

    private int mTriangleRadius = 10; //圆角三角形圆角半径
    private int mTriangleWidth = 30; //圆角三角形长度
    private int mTriangleHeight = 30; //圆角三角形长度

    private ValueAnimator mValueAnimatorMove;//移动变化动画

    private Context mContext;


    public PlayAnimationView(Context context) {
        super(context);
        init(context);
    }

    public PlayAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        mContext = context;
        mPaint = new Paint();
        mPaintTriangle = new Paint();
        mPath1 = new Path();
        mPath2 = new Path();
        mPath3 = new Path();
        mPath4 = new Path();
        mPath5 = new Path();
        mPath6 = new Path();
        mPath7 = new Path();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(DisplayUtil.dip2px(mContext,2));

        mPaintTriangle.setStyle(Paint.Style.FILL);
        mPaintTriangle.setColor(Color.parseColor("#ffffff"));
        mPaintTriangle.setAntiAlias(true);

        mValueAnimatorMove = ValueAnimator.ofFloat(0,1);
        mValueAnimatorMove.setInterpolator(new LinearInterpolator());  //线性匀速变化
        mValueAnimatorMove.setDuration(1500);//动画时长1秒钟
        mValueAnimatorMove.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPercent = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        mValueAnimatorMove.start();
    }

    public void startPlayAnima(){
        mPercent = 0;
        if (mValueAnimatorMove != null && !mValueAnimatorMove.isStarted()){
            mValueAnimatorMove.start();
        }
    }

    @Override
    protected  void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure( widthMeasureSpec,  heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2.0f;
        mCenterY = mHeight / 2.0f;

        mRadius = mHeight / 2 - DisplayUtil.dip2px(mContext,2);
        mDistance = mWidth / 2 - mRadius - DisplayUtil.dip2px(mContext,2);

        mTriangleWidth = DisplayUtil.dip2px(mContext,15);
        mTriangleHeight = DisplayUtil.dip2px(mContext,15);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        mPath1.reset();
        mPath2.reset();
        mPath3.reset();
        mPath4.reset();
        mPath5.reset();
        mPath6.reset();
        mPath7.reset();

        RectF rectF = new RectF(mCenterX - mRadius - mDistance*mPercent,mCenterY - mRadius,mCenterX+mRadius - mDistance*mPercent,mCenterY+mRadius);
        mPath1.arcTo(rectF,90,180);

        rectF = new RectF(mCenterX - mRadius + mDistance*mPercent,mCenterY - mRadius,mCenterX+mRadius + mDistance*mPercent,mCenterY+mRadius);
        mPath2.arcTo(rectF,270,180);

        mPath3.moveTo(mCenterX,mCenterY-mRadius);
        mPath3.lineTo(mCenterX - mDistance*mPercent ,mCenterY-mRadius);

        mPath4.moveTo(mCenterX,mCenterY-mRadius);
        mPath4.lineTo(mCenterX + mDistance*mPercent ,mCenterY-mRadius);

        mPath5.moveTo(mCenterX,mCenterY+mRadius);
        mPath5.lineTo(mCenterX - mDistance*mPercent ,mCenterY+mRadius);

        mPath6.moveTo(mCenterX,mCenterY+mRadius);
        mPath6.lineTo(mCenterX + mDistance*mPercent ,mCenterY+mRadius);

        canvas.drawPath(mPath1,mPaint);
        canvas.drawPath(mPath2,mPaint);
        canvas.drawPath(mPath3,mPaint);
        canvas.drawPath(mPath4,mPaint);
        canvas.drawPath(mPath5,mPaint);
        canvas.drawPath(mPath6,mPaint);


        mPaintTriangle.setAlpha((int)(255*mPercent));
        mPath7.moveTo(mCenterX+mTriangleWidth/2 - mTriangleWidth,mCenterY);
        mPath7.lineTo(mCenterX-mTriangleWidth/2 - mTriangleWidth,mCenterY - mTriangleHeight / 2);
        mPath7.lineTo(mCenterX - mTriangleWidth/2 - mTriangleWidth,mCenterY + mTriangleHeight / 2);
        mPath7.close();

        mPaintTriangle.setTextSize(DisplayUtil.sp2px(getContext(),18));
        Paint.FontMetricsInt fontMetrics = mPaintTriangle.getFontMetricsInt();
        int textHeight = fontMetrics.bottom-fontMetrics.top;//详细:(baseY＋bottom)-(baseY-top);
        canvas.drawText("Live",mCenterX,mCenterY+textHeight/4,mPaintTriangle);
        canvas.drawPath(mPath7, mPaintTriangle);
    }


}
