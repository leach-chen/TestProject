package com.leachchen.testproject.testview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * ClassName:   MyView.java
 * Description:
 * Author :     leach.chen
 * Date:        2017/2/24 15:47
 **/

public class ClockView extends View {

    private Context mContext;

    //秒针长度
    private float secondPointerLength;
    //分针长度
    private float minutePointerLength;
    //时针长度
    private float hourPointerLength;

    //指针反向超过圆点的长度
    private static final float DEFAULT_POINT_BACK_LENGTH = 40f;
    //长刻度线
    private static final float DEFAULT_LONG_DEGREE_LENGTH = 40f;
    //外圆边框宽度
    private static final float DEFAULT_BORDER_WIDTH = 6f;

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        timeThread.start();
    }
    /**
     * 计算时针、分针、秒针的长度
     */
    private void reset(){
        float r = (Math.min(getHeight() / 2, getWidth() / 2) - DEFAULT_BORDER_WIDTH / 2);
        secondPointerLength = r * 0.8f;
        minutePointerLength = r * 0.6f;
        hourPointerLength = r * 0.5f;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        reset();
        //画外圆
        float borderWidth = DEFAULT_BORDER_WIDTH;
        float r = Math.min(getHeight() / 2, getWidth() / 2) - borderWidth / 2;

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,paint);

        float degreeLength = 0;
        Paint paintDegree = new Paint();
        paintDegree.setAntiAlias(true);
        for(int i = 0;i < 60;i++)
        {
            if(i%5 == 0)
            {
                paintDegree.setStrokeWidth(8);
                degreeLength = 16;
               /* paintDegree.setTextSize(20);
                canvas.drawText(""+60/i,getWidth()/2, Math.abs(getWidth()/2 - getHeight()/2),paintDegree);*/
            }else
            {
                paintDegree.setStrokeWidth(6);
                degreeLength = 12;
            }
            canvas.drawLine(getWidth()/2, Math.abs(getWidth()/2 - getHeight()/2), getWidth()/2, Math.abs(getWidth()/2 - getHeight()/2) + degreeLength, paintDegree);
            canvas.rotate(360/60, getWidth()/2, getHeight()/2);
        }

        int degressNumberSize = 30;
        canvas.translate(getWidth()/2,getHeight()/2);
        Paint paintDegreeNumber  = new Paint();
        paintDegreeNumber.setTextAlign(Paint.Align.CENTER);
        paintDegreeNumber.setTextSize(degressNumberSize);
        paintDegreeNumber.setFakeBoldText(true);
        for(int i = 0; i < 12;i++)
        {
            float[] temp = calculatePoint((i+1)*30, r - DEFAULT_LONG_DEGREE_LENGTH - degressNumberSize/2 - 15);
            canvas.drawText((i+1)+"", temp[2], temp[3] + degressNumberSize/2-6, paintDegreeNumber);
        }

        //画指针
        Paint paintHour = new Paint();
        paintHour.setAntiAlias(true);
        paintHour.setStrokeWidth(15);

        Paint paintMinute = new Paint();
        paintMinute.setAntiAlias(true);
        paintMinute.setStrokeWidth(10);

        Paint paintSecond = new Paint();
        paintSecond.setAntiAlias(true);
        paintSecond.setStrokeWidth(5);


        Calendar now = Calendar.getInstance();
        float[] hourPoints = calculatePoint(now.get(Calendar.HOUR_OF_DAY)%12/12f*360, hourPointerLength);
        canvas.drawLine(hourPoints[0], hourPoints[1], hourPoints[2], hourPoints[3], paintHour);
        float[] minutePoints = calculatePoint(now.get(Calendar.MINUTE)/60f*360, minutePointerLength);
        canvas.drawLine(minutePoints[0], minutePoints[1], minutePoints[2], minutePoints[3], paintMinute);
        float[] secondPoints = calculatePoint(now.get(Calendar.SECOND)/60f*360, secondPointerLength);
        canvas.drawLine(secondPoints[0], secondPoints[1], secondPoints[2], secondPoints[3], paintSecond);

        //画圆心
        Paint paintCenter = new Paint();
        paintCenter.setColor(Color.WHITE);
        canvas.drawCircle(0, 0, 2, paintCenter);

    }

    /**
     * 根据角度和长度计算线段的起点和终点的坐标
     * @param angle
     * @param length
     * @return
     */
    private float[] calculatePoint(float angle, float length){
        float[] points = new float[4];
        if(angle <= 90f){
            points[0] = -(float) Math.sin(angle*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[1] = (float) Math.cos(angle*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[2] = (float) Math.sin(angle*Math.PI/180) * length;
            points[3] = -(float) Math.cos(angle*Math.PI/180) * length;
        }else if(angle <= 180f){
            points[0] = -(float) Math.cos((angle-90)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[1] = -(float) Math.sin((angle-90)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[2] = (float) Math.cos((angle-90)*Math.PI/180) * length;
            points[3] = (float) Math.sin((angle-90)*Math.PI/180) * length;
        }else if(angle <= 270f){
            points[0] = (float) Math.sin((angle-180)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[1] = -(float) Math.cos((angle-180)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[2] = -(float) Math.sin((angle-180)*Math.PI/180) * length;
            points[3] = (float) Math.cos((angle-180)*Math.PI/180) * length;
        }else if(angle <= 360f){
            points[0] = (float) Math.cos((angle-270)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[1] = (float) Math.sin((angle-270)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[2] = -(float) Math.cos((angle-270)*Math.PI/180) * length;
            points[3] = -(float) Math.sin((angle-270)*Math.PI/180) * length;
        }
        return points;
    }

    private Thread timeThread = new Thread() {
        @Override
        public void run() {
            try {
                while(true){
                    updateHandler.sendEmptyMessage(0);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            invalidate();
        }
    };


}
