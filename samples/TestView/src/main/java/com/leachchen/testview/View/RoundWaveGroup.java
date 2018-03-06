package com.leachchen.testview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.leachchen.testview.R;


/**
 * ClassName:   RoundViewGroup.java
 * Description:
 * Author :     leach.chen
 * Date:        2017/5/2 11:55
 **/

public class RoundWaveGroup extends FrameLayout{

    private RoundWaveView mRoundWaveView;

    public RoundWaveGroup(@NonNull Context context) {
        super(context);
        init(context);
    }
    public RoundWaveGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public RoundWaveGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        mRoundWaveView = new RoundWaveView(context);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.home_icon_phone);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.topMargin = 30;
        params.bottomMargin = 30;
        imageView.setLayoutParams(params);
        this.addView(mRoundWaveView);
        this.addView(imageView);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), 20, 20, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.REPLACE);
        super.dispatchDraw(canvas);
    }



}
