package com.leachchen.testproject.testview.View;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * ClassName:   ArcProgress.java
 * Description:
 * Author :     leach.chen
 * Date:        2017/3/9 17:58
 **/

public class ArcProgressView extends View{
    private float value;//用户设置的值
    private Paint arcPaint1;//要用到的画笔
    private Paint arcPaint2;//要用到的画笔
    private Paint arcPaint3;//要用到的画笔
    private RectF rectF;//绘制的范围
    private float oldValue;//过时的值
    private float percentOne;
    private float newPercentOne;
    private float percentTwo;
    private float newPercentTwo;
    private float percentThree;
    private float newPercentThree;
    private float oldPercentOne = 0;
    private float oldPercentTwo = 0;
    private float oldPercentThree = 0;

    public ArcProgressView(Context context) {
        super(context);
        init();
        /*arcprogress_v = (ArcProgressView)this.findViewById(arcprogress_v);
        arcprogress_v.setValue(300);
        arcprogress_v.setValue(new Random().nextInt(20), new Random().nextInt(20),
                new Random().nextInt(20));*/
    }

    public ArcProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArcProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        arcPaint1 = new Paint();
        arcPaint1.setAntiAlias(true);//抗锯齿
        arcPaint1.setStyle(Paint.Style.FILL);//只绘制圆弧边界
        arcPaint1.setColor(Color.parseColor("#2C2B2E"));
        arcPaint1.setStrokeWidth(10);//50px的圆弧宽度


        arcPaint2 = new Paint();
        arcPaint2.setAntiAlias(true);//抗锯齿
        arcPaint2.setStyle(Paint.Style.FILL);//只绘制圆弧边界
        arcPaint2.setColor(Color.parseColor("#3CF3FA"));
        arcPaint2.setStrokeWidth(10);//50px的圆弧宽度


        arcPaint3 = new Paint();
        arcPaint3.setAntiAlias(true);//抗锯齿
        arcPaint3.setStyle(Paint.Style.FILL);//只绘制圆弧边界
        arcPaint3.setColor(Color.parseColor("#FD2D6A"));
        arcPaint3.setStrokeWidth(10);//50px的圆弧宽度
    }

    public void setValue(final float v) {
        ValueAnimator animator = ValueAnimator.ofFloat(oldValue, v);
        oldValue = v;
        if (Math.abs(v - oldValue) > 180) {
            animator.setDuration(1000);
        } else {
            animator.setDuration(500);
        }
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                Log.d("change", "=" + animation.getAnimatedValue());
                invalidate();
            }
        });
        animator.start();
    }



    public void setValue(int value1, int value2, int value3) {
        int sum = value1 + value2 + value3;
        percentOne = (float) value1 / sum;
        newPercentOne = percentOne;
        percentTwo = (float) value2 / sum;
        newPercentTwo = percentTwo;
        percentThree = (float) value3 / sum;
        newPercentThree = percentThree;
        Log.d("aa","value1:"+value1+" value2:"+value2+" value3:"+value3 + " newPercentOne:"+newPercentOne+" newPercentTwo:"+newPercentTwo+" newPercentThree:"+newPercentThree);
        ValueAnimator animator1 = ValueAnimator.ofFloat(oldPercentOne, newPercentOne);
        ValueAnimator animator2 = ValueAnimator.ofFloat(oldPercentTwo, newPercentTwo);
        ValueAnimator animator3 = ValueAnimator.ofFloat(oldPercentThree, newPercentThree);
        animator1.setDuration(1000);
        animator2.setDuration(1000);
        animator3.setDuration(1000);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                percentOne = (float) animation.getAnimatedValue();
                Log.d("aa","percentOne:"+percentOne);
            }
        });
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                percentTwo = (float) animation.getAnimatedValue();
            }
        });
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                percentThree= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(animator1,animator2,animator3);
        animatorSet.start();
        Log.d("rect :", "percentone=" + percentOne + ",percenttwo=" + percentTwo + ",percentthree=" + percentThree);

    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        rectF = new RectF(50, 50, getMeasuredHeight() - 50, getMeasuredHeight() - 50);
        canvas.drawArc(rectF, 0, 360 * percentOne, true, arcPaint1);
        canvas.drawArc(rectF, 360 * percentOne, 360 * percentTwo, true, arcPaint2);
        canvas.drawArc(rectF, 360 * (percentOne + percentTwo), 360 * percentThree, true, arcPaint3);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        oldValue = 0;
    }



}
