package com.leachchen.testview.View;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
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
//        int dpSize =  5;
//        DisplayMetrics dm = getResources().getDisplayMetrics() ;
//        float boundWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, dm);
//
//        //绘制电池外框
//        mPaint.setColor(mColorBound);
//        mPaint.setStyle(Paint.Style.STROKE);//设置空心
//        mPaint.setStrokeWidth(boundWidth); //设置绘制边框宽度,宽度是往外侧增加一半，往内侧增加一半。
//        RectF battBoundRF = new RectF(0,0,mWidth,mHeight);
//        canvas.drawRect(battBoundRF,mPaint);
//
//        Paint paint = new Paint();
//        paint.setStrokeWidth(0);
//        paint.setColor(mColorProcess);
//        paint.setStyle(Paint.Style.FILL);//设置空心
//        RectF battProcessRF = new RectF(boundWidth/2,boundWidth/2,mWidth - boundWidth / 2,mHeight - boundWidth / 2);
//        canvas.drawRect(battProcessRF,paint);


        int dpSize =  5;
        DisplayMetrics dm = getResources().getDisplayMetrics() ;
        float boundWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, dm);

        //绘制电池外框
        mPaint.setColor(mColorBound);
        mPaint.setStyle(Paint.Style.STROKE);//设置空心
        mPaint.setStrokeWidth(boundWidth); //设置绘制边框宽度,宽度是往外侧增加一半，往内侧增加一半。

        Path path1 = new Path();
        RectF rect1 = new RectF(100, 200, 400, 400);
        float[] radii = {30, 30, 30, 30, 30, 30, 30, 30};
        path1.addRoundRect(rect1, radii, Path.Direction.CCW);
        //canvas.drawPath(path1, mPaint);

        Path path2 = new Path();
        RectF rect2 = new RectF(50, 100, 300, 300);
        path2.addRoundRect(rect2, radii, Path.Direction.CCW);

        path1.op(path2, Path.Op.UNION);

        canvas.drawPath(path1, mPaint);

    }


}
