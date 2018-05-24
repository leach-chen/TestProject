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
import android.widget.LinearLayout;


/**
 * ClassName:   RoundViewGroup.java
 * Description:
 * Author :     leach.chen
 * Date:        2017/5/2 11:55
 **/

public class RoundLinearLayout extends LinearLayout {

    /*圆角的半径，依次为左上角xy半径，右上角，右下角，左下角*/
    private float[] rids = {800f, 800f, 600f, 600f, 400f, 400f, 200f, 200f};//四个角都圆角

    public RoundLinearLayout(@NonNull Context context) {
        super(context);
    }
    public RoundLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public RoundLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), rids, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.REPLACE);
        super.dispatchDraw(canvas);
    }


}
