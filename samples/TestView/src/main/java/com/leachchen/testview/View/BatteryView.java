package com.leachchen.testview.View;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * ClassName:   BatteryView.java
 * Description:
 * Author :     leach.chen
 * Date:        2019/4/2 16:15
 **/
public class BatteryView extends View {

    private Context mContext;
    private Paint mPaint;
    private int mColorBound;
    private int mColorProcess;
    private int mProcess;
    private int mWidth;
    private int mHeight;


    public BatteryView(Context context) {
        super(context);
        init(context);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context)
    {
        this.mContext = context;
        mPaint = new Paint();

        mColorBound = Color.parseColor("#8B8B8B");
        mColorProcess = Color.parseColor("#39D689");
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
        //绘制电池外框
        mPaint.setColor(mColorBound);
        mPaint.setStyle(Paint.Style.STROKE);//设置空心
        mPaint.setStrokeWidth(2); //设置绘制边框宽度
        RectF battRectf = new RectF(0,0,mWidth,mHeight);
        canvas.drawRect(battRectf,mPaint);




    }


}
