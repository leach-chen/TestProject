package com.leachchen.testview.View;

import android.animation.ObjectAnimator;
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

/**
 * ClassName:   VavaView.java
 * Description:
 * Author :     leach.chen
 * Date:        2019/4/3 18:31
 **/
public class VavaView extends View {

    private Context mContext;
    private Paint mPaint;
    private Path mPath;

    private int mWidth;
    private int mHeight;

    private float mVavaWidth=50;

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
        this.mContext = context;
        mPaint = new Paint();
        mPath = new Path();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected  void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure( widthMeasureSpec,  heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        mPath.moveTo(0,0);
        mPath.quadTo(19, 120    , 38, 0);
        //绘制路径
        canvas.drawPath(mPath, mPaint);
        canvas.drawPath(mPath,mPaint);
    }
}
