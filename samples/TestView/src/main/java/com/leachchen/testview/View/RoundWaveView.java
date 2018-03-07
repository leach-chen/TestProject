package com.leachchen.testview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * ClassName:   RoundView.java
 * Description:
 * Author :     leach.chen
 * Date:        2017/4/27 13:58
 **/

public class RoundWaveView extends View {

    private int mFirstColor;
    private int mSecondColor;
    private int mThirdColor;
    private int mFourthColor;
    private int mFirstRadius;
    private int mSecondRadius;
    private int mThirdRadius;
    private int mFourthRadius;


    private boolean isMaxOrderFirst;
    private boolean isMaxOrderSecond;
    private boolean isMaxOrderThird;
    private boolean isMaxOrderFourth;


    private int addValue;
    private boolean isFirst = true;
    private Handler mHandler = new Handler();

    public RoundWaveView(Context context) {
        super(context);
        init();
    }

    public RoundWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mFirstColor = Color.parseColor("#61A7F2");
        mSecondColor = Color.parseColor("#79B4F2");
        mThirdColor = Color.parseColor("#499BF2");
        mFourthColor = Color.parseColor("#79b4f2");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.post(mRunnable);
            }
        }, 2000);
    }


    @Override
    public void onDraw(Canvas canvas) {
        if (isFirst) {
            addValue = getWidth() / 6;
            mFirstRadius = 2 * addValue;
            mSecondRadius = addValue;
            mThirdRadius = 1;
            mFourthRadius = -addValue;
            isFirst = false;
            isMaxOrderFourth = true;
            //Log.d("aaa","1,2,3,4");
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        drawOrder(canvas,paint);
    }


    private void drawOrder(Canvas canvas,Paint paint)
    {
        //1,2,3,4
        if(isMaxOrderFourth)
        {
            paint.setColor(mFirstColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mFirstRadius, paint);

            paint.setColor(mSecondColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mSecondRadius, paint);

            paint.setColor(mThirdColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mThirdRadius, paint);

            paint.setColor(mFourthColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mFourthRadius, paint);
        }

        //2,3,4,1
        if(isMaxOrderFirst)
        {
            paint.setColor(mSecondColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mSecondRadius, paint);

            paint.setColor(mThirdColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mThirdRadius, paint);

            paint.setColor(mFourthColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mFourthRadius, paint);

            paint.setColor(mFirstColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mFirstRadius, paint);
        }
        //3,4,1,2
        if(isMaxOrderSecond)
        {
            paint.setColor(mThirdColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mThirdRadius, paint);

            paint.setColor(mFourthColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mFourthRadius, paint);

            paint.setColor(mFirstColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mFirstRadius, paint);

            paint.setColor(mSecondColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mSecondRadius, paint);

        }

        //4,1,2,3
        if(isMaxOrderThird)
        {
            paint.setColor(mFourthColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mFourthRadius, paint);

            paint.setColor(mFirstColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mFirstRadius, paint);

            paint.setColor(mSecondColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mSecondRadius, paint);

            paint.setColor(mThirdColor);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mThirdRadius, paint);
        }

    }



    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mFirstRadius = mFirstRadius + 5;
            mSecondRadius = mSecondRadius + 5;
            mThirdRadius = mThirdRadius + 5;
            mFourthRadius = mFourthRadius + 5;


            if(mFirstRadius >= 4 * addValue)
            {
                isMaxOrderFirst = true;
                mFirstRadius = 0;
                isMaxOrderSecond = false;
                isMaxOrderThird = false;
                isMaxOrderFourth = false;
                //Log.d("aaa","2,3,4,1");
            }


            if(mSecondRadius >= 4 * addValue)
            {
                isMaxOrderFirst = false;
                isMaxOrderSecond = true;
                mSecondRadius = 0;
                isMaxOrderThird = false;
                isMaxOrderFourth = false;
                //Log.d("aaa","3,4,1,2");
            }

            if(mThirdRadius >= 4 * addValue)
            {
                isMaxOrderFirst = false;
                isMaxOrderSecond = false;
                isMaxOrderThird = true;
                mThirdRadius = 0;
                isMaxOrderFourth = false;
                //Log.d("aaa","4,1,2,3");
            }

            if(mFourthRadius >= 4 * addValue)
            {
                isMaxOrderFirst = false;
                isMaxOrderSecond = false;
                isMaxOrderThird = false;
                isMaxOrderFourth = true;
                mFourthRadius = 0;
                //Log.d("aaa","1,2,3,4");
            }
            //Log.d("aa", "a:" + mFirstRadius + " b:" + mSecondRadius + " c:" + mThirdRadius);
            postInvalidate();
            mHandler.postDelayed(mRunnable, 20);
        }
    };
}
