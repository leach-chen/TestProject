package com.leachchen.testview.View;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
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
    }
}
