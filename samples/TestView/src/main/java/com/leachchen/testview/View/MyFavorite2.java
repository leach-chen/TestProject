package com.leachchen.testview.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.leachchen.testview.R;

import static android.R.attr.path;

/**
 * ClassName:   MyFavorite.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/23 11:19
 **/

public class MyFavorite2 extends View {

    private Context mContext;
    private Path mPath;
    private Paint mPaint;
    private float rate = 1.5f; // 半径变化率

    public MyFavorite2(Context context) {
        this(context, null);
    }

    public MyFavorite2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public MyFavorite2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }


    public void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);

        mPath = new Path();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 重置画板
        mPath.reset();
        // 得到屏幕的长宽的一半
        int px = getMeasuredWidth() / 2;
        int py = getMeasuredHeight() / 2;
        // 路径的起始点
        mPath.moveTo(px, py - 5 * rate);
        // 根据心形函数画图
        for (double i = 0; i <= 2 * Math.PI; i += 0.001) {
            float x = (float) (16 * Math.sin(i) * Math.sin(i) * Math.sin(i));
            float y = (float) (13 * Math.cos(i) - 5 * Math.cos(2 * i) - 2 * Math.cos(3 * i) - Math.cos(4 * i));
            x *= rate;
            y *= rate;
            x = px - x;
            y = py - y;
            mPath.lineTo(x, y);
        }
        canvas.drawPath(mPath, mPaint);
    }

}
