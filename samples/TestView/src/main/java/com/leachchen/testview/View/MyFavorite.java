package com.leachchen.testview.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * ClassName:   MyFavorite.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/23 11:19
 **/

public class MyFavorite extends View {

    private Context mContext;
    private Paint mPaint;
    private ValueAnimator av;
    private float mLength;
    private PathMeasure pm;
    private Path mPath;

    public MyFavorite(Context context) {
        this(context, null);
    }

    public MyFavorite(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public MyFavorite(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

/*        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        Log.e("mytest","specMode:"+specMode+" specSize:"+specSize);*/

        int measuredHeight = measureHeight(heightMeasureSpec);
        int measuredWidth = measureWidth(widthMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

/*  1. EXACTLY
    表示父视图希望子视图的大小应该是由specSize的值来决定的，系统默认会按照这个规则来设置子视图的大小，简单的说（当设置width或height为match_parent时，模式为EXACTLY，因为子view会占据剩余容器的空间，所以它大小是确定的）
    2. AT_MOST
    表示子视图最多只能是specSize中指定的大小。（当设置为wrap_content时，模式为AT_MOST, 表示子view的大小最多是多少，这样子view会根据这个上限来设置自己的尺寸）
    3. UNSPECIFIED
    表示开发人员可以将视图按照自己的意愿设置成任意的大小，没有任何限制。这种情况比较少见，不太会用到。*/

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        Log.e("mytest","measureHeight:"+specSize);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {  //wrap_content
            //result = specSize;
            Log.e("mytest","aaa");
        } else if (specMode == MeasureSpec.EXACTLY) {  //match_parent或者具体值
            result = specSize;
            Log.e("mytest","bbb");
        }
        return result;
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 200;
        if (specMode == MeasureSpec.AT_MOST) {
             result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }


    public void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath = new Path();
        mPath.moveTo(396 - 50, 313);
        mPath.cubicTo(207 - 50, 114, 339 - 50, 46, 396 - 50, 111);
        mPath.cubicTo(453 - 50, 46, 585 - 50, 114, 396 - 50, 313);
        //----------------------------------------------
        pm = new PathMeasure(mPath, false);//false表示是否强制闭合路径


        //Log.e("mytest", "pm.getLength():" + pm.getLength());
        av = ValueAnimator.ofFloat(0, pm.getLength());
        av.setDuration(5000);
        av.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mLength = (float) animation.getAnimatedValue();
                //Log.e("mytest", "" + mLength);
                invalidate();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start();
            }
        }, 1000);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        Path path = new Path();
        //path.reset();
        //这里需要lineTo才会绘画图像，据说是openGl的一个BUG
        path.lineTo(0, 0);
        //这个方法的意思是取路径片段，0是指从头截取，mLength是指截取的长度
        pm.getSegment(0, mLength, path, true);
        canvas.drawPath(path, mPaint);
        // canvas.drawPath(mPath,paint);
        canvas.restore();
    }

    public void start() {
        av.start();
    }

}
