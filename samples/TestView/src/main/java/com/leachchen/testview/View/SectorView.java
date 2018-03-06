package com.leachchen.testview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * ClassName:   SectorView.java
 * Description:
 * Author :     leach.chen
 * Date:        2017/7/13 11:52
 **/

public class SectorView extends View {

    private static final int DEFAULT_RADIUS = 100;
    private int mRadius = DEFAULT_RADIUS; //外圆的半径
    private Paint mPaint;


    public SectorView(Context context) {
        super(context);
        init();
    }

    public SectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        RectF oval1 = new RectF(DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS+200, DEFAULT_RADIUS+200);
        canvas.drawArc(oval1, 270, 90, true, mPaint);

        RectF oval2 = new RectF(DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS+200, DEFAULT_RADIUS+200+20);
        canvas.drawArc(oval2, 360, 90, true, mPaint);

        RectF oval3 = new RectF(DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS+200-20, DEFAULT_RADIUS+200+20);
        canvas.drawArc(oval3, 90, 90, true, mPaint);

        RectF oval4 = new RectF(DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS+200-20, DEFAULT_RADIUS+200);
        canvas.drawArc(oval4, 180, 90, true, mPaint);

    }

}
