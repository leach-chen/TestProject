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
    private Path mPath;
    private int mColorBound;
    private int mColorNoPower;
    private int mColorProcess;
    private int mProcess = 0;
    private int mWidth;
    private int mHeight;
    private float DEFAULT_RADIUS = 0;
    private static final float RIGHT_RADIUS = 5;

    private Paint mInnerPaint;
    private Path mInnerPath;


    float position_start_y = 0;
    float position_start_y1 = 0;
    float position_end_y = 0;
    float dpSize =  2;
    float boundWidth;
    float paddwdith;

    private int mRedValue;

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
        mPath = new Path();

        mInnerPaint = new Paint();
        mInnerPath = new Path();

        mColorBound = Color.parseColor("#8B8B8B");
        mColorProcess = Color.parseColor("#39D689");
        mColorNoPower = Color.parseColor("#F87878");
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

        DisplayMetrics dm = getResources().getDisplayMetrics() ;
        boundWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, dm);
        paddwdith = boundWidth/2;
        mPaint.setColor(mColorBound);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(boundWidth);
        mPaint.setAntiAlias(true);

        if(mProcess <= mRedValue)
        {
            mPaint.setColor(mColorNoPower);
        }
        drawBatteryOut(canvas);

        if(mProcess == 100)
        {
            mPaint.setColor(mColorProcess);
            mPaint.setStyle(Paint.Style.FILL);
            drawBatteryOut(canvas);
        }else if(mProcess > 0)
        {
            drawBatterInner(canvas);
        }

    }

    private void drawBatterInner(Canvas canvas)
    {
        mInnerPath.reset();
        mInnerPaint.setColor(mColorProcess);
        mInnerPaint.setStyle(Paint.Style.FILL);
        mInnerPaint.setStrokeWidth(boundWidth);
        mInnerPaint.setAntiAlias(true);

        float width = mWidth - DEFAULT_RADIUS+paddwdith;
        float height = mHeight - 1.5f*paddwdith;

        RectF rectF = new RectF(1.5f*paddwdith,1.5f*paddwdith,width*(mProcess/100.0f),height);
        float[] radii = {0.7f*DEFAULT_RADIUS, 0.7f*DEFAULT_RADIUS, 0, 0, 0, 0, 0.7f*DEFAULT_RADIUS, 0.7f*DEFAULT_RADIUS};
        mInnerPath.addRoundRect(rectF,radii, Path.Direction.CCW);
        canvas.drawPath(mInnerPath, mInnerPaint); //绘制内部电池
    }

    private void drawBatteryOut(Canvas canvas)
    {

        DEFAULT_RADIUS = mHeight / 3;

        position_start_y = paddwdith;
        position_end_y = paddwdith;

        RectF rectF = null;
        //绘制一条线
        mPath.moveTo(DEFAULT_RADIUS+paddwdith, position_start_y);
        mPath.lineTo(mWidth - DEFAULT_RADIUS - RIGHT_RADIUS - paddwdith, position_end_y);


        //绘制右上角圆角
        rectF = new RectF(mWidth - DEFAULT_RADIUS - RIGHT_RADIUS - paddwdith,position_start_y,mWidth - RIGHT_RADIUS - paddwdith, DEFAULT_RADIUS+paddwdith);//增加矩形y轴位置
        mPath.arcTo(rectF,270,90);

        position_end_y = (DEFAULT_RADIUS+paddwdith)/2; //当前位置y坐标

        position_end_y = position_end_y + DEFAULT_RADIUS/4;//增加y轴位置
        //向下连接
        mPath.lineTo(mWidth - RIGHT_RADIUS - paddwdith, position_end_y); //绘制到指定位置


        position_start_y = position_end_y;//重置y轴开始位置
        position_start_y1 = position_start_y;//绘制电池头下部分需要用到的位置
        //绘制电池头上边圆角
        rectF = new RectF(mWidth - RIGHT_RADIUS - paddwdith - RIGHT_RADIUS,position_start_y,mWidth-paddwdith,position_end_y + DEFAULT_RADIUS/2);//增加矩形y轴位置
        mPath.arcTo(rectF,270,90);
        position_end_y = position_end_y + (DEFAULT_RADIUS/2)/2; //当前位置y坐标

        //电池头向下连接
        mPath.lineTo(mWidth-paddwdith, position_end_y+DEFAULT_RADIUS/2);//新增当前y轴位置

        position_end_y = position_end_y+DEFAULT_RADIUS/2;//当前位置y坐标


        //绘制电池头下边圆角,将电池头相对上一个角的矩形下移DEFAULT_RADIUS/2位置
        rectF = new RectF(mWidth - RIGHT_RADIUS - paddwdith - RIGHT_RADIUS,position_start_y1+DEFAULT_RADIUS/2,mWidth-paddwdith,position_end_y+DEFAULT_RADIUS/2);
        mPath.arcTo(rectF,0,90);

        position_end_y = position_end_y+(DEFAULT_RADIUS/2)/2;//当前位置y坐标

        //电池头向下连接
        mPath.lineTo(mWidth - RIGHT_RADIUS - paddwdith, position_end_y+DEFAULT_RADIUS/4f);//新增y轴位置
        position_end_y = position_end_y+DEFAULT_RADIUS/4f;//当前y轴位置


        //绘制右下角圆角
        rectF = new RectF(mWidth - DEFAULT_RADIUS - RIGHT_RADIUS - paddwdith,mHeight - paddwdith - DEFAULT_RADIUS,mWidth - RIGHT_RADIUS - paddwdith, mHeight - paddwdith);//增加矩形y轴位置
        mPath.arcTo(rectF,0,90);

        position_end_y = mHeight - paddwdith;

        //绘制到左下角位置
        mPath.lineTo(DEFAULT_RADIUS+paddwdith, position_end_y);

        //绘制左下角
        rectF = new RectF(paddwdith,position_end_y - DEFAULT_RADIUS,DEFAULT_RADIUS+paddwdith, position_end_y);
        mPath.arcTo(rectF,90,90);

        //连接到左上角位置
        mPath.lineTo(paddwdith, DEFAULT_RADIUS);

        //绘制左上角
        rectF = new RectF(paddwdith,paddwdith,DEFAULT_RADIUS+paddwdith, DEFAULT_RADIUS+paddwdith);
        mPath.arcTo(rectF,180,90);
        mPath.close();

        canvas.drawPath(mPath, mPaint); //绘制外壳电池
    }


    /**
     * 更新电池进度
     * @param process
     */
    public void updateProcess(int process)
    {
        this.mProcess = process;
        postInvalidate();
    }

    /**
     * 设置电池变红的值0~100
     * @param value
     */
    public void setRedValue(int value)
    {
        this.mRedValue = value;
    }

}
