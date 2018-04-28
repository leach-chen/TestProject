package com.leachchen.testview.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.leachchen.testview.R;


/**
 * 仿iphone带进度的进度条，线程安全的View，可直接在线程中更新进度
 * @author xiaanming
 *
 */
public class RoundProgressBar extends View {
	/**
	 * 画笔对象的引用
	 */
	private Paint paint;

	/**
	 * 圆环的颜色
	 */
	private int roundColor;

	/**
	 * 圆环进度的颜色
	 */
	private int roundProgressColor;

	/**
	 * 中间进度百分比的字符串的颜色
	 */
	private int textColor;

	/**
	 * 中间进度百分比的字符串的字体
	 */
	private float textSize;

	/**
	 * 圆环的宽度
	 */
	private float roundWidth = 4;

	/**
	 * 最大进度
	 */
	private int max;

	/**
	 * 当前进度
	 */
	private int progress;
	/**
	 * 是否显示中间的进度
	 */
	private boolean textIsDisplayable;

	/**
	 * 进度的风格，实心或者空心
	 */
	private int style;

	public static final int STROKE = 0;
	public static final int FILL = 1;

	public RoundProgressBar(Context context) {
		this(context, null);
	}

	public RoundProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paint = new Paint();


		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBar);

		//获取自定义属性和默认值
		roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
		roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
		textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
		textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);
		roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
		max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
		textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
		style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);

		mTypedArray.recycle();
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);



		setLayerType( LAYER_TYPE_SOFTWARE , null);


		int centre = getWidth()/2; //获取圆心的x坐标
		int radius = (int) (centre - roundWidth/2); //圆环的半径
		paint.setColor(roundColor); //设置圆环的颜色
		paint.setStyle(Paint.Style.STROKE); //设置空心
		paint.setStrokeWidth(roundWidth); //设置圆环的宽度
		paint.setShadowLayer(2, 0, 0, Color.GREEN);
		paint.setAntiAlias(true);  //消除锯齿
		/*paint.setShadowLayer(12, 0, 0, Color.GRAY);*/
		canvas.drawCircle(centre, centre, radius, paint); //画出圆环











/*
		setLayerType( LAYER_TYPE_SOFTWARE , null);

		Paint paint3 = new Paint();
		paint3.setStyle(Paint.Style.STROKE); //设置空心
		paint3.setAntiAlias(true);  //消除锯齿
		paint3.setStrokeWidth(10);
		paint3.setColor(Color.WHITE);
	*//*	setLayerType( LAYER_TYPE_SOFTWARE , null);
		paint.setShadowLayer(10, 5, 2, Color.GREEN);
		//paint.setMaskFilter(new BlurMaskFilter(50,BlurMaskFilter.Blur.NORMAL));
		canvas.drawCircle(200, 130,10, paint3);*//*

		//setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		int centre = getWidth()/2; //获取圆心的x坐标
		int radius = (int) (centre - roundWidth/2); //圆环的半径

		paint3.setShadowLayer(12, 0, 0, Color.GRAY);
		canvas.drawCircle(centre, centre,radius, paint3);*/















	/*	setLayerType( LAYER_TYPE_SOFTWARE , null);

		// 建立Paint 物件
		Paint paint1 = new Paint();
		// 设定颜色
		paint1.setColor(0xFFFFFF00);
		// 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
		paint1.setShadowLayer(5, 3, 3, 0xFFFF00FF);
		// 实心矩形& 其阴影
		canvas.drawText("我很爱你", 20,40,paint1);
		Paint paint2 = new Paint();
		paint2.setColor(Color.GREEN);
		paint2.setShadowLayer(10, 5, 2, Color.YELLOW);
		canvas.drawText("你真傻", 20,60,paint2);

*//*		Paint paint3 = new Paint();
		paint3.setColor(Color.RED);
		paint3.setShadowLayer(30, 5, 2, Color.GREEN);
		canvas.drawCircle(50, 130,30, paint3);*//*


		Paint paint3 = new Paint();
		paint3.setStyle(Paint.Style.STROKE); //设置空心
		paint3.setAntiAlias(true);  //消除锯齿
		paint3.setStrokeWidth(10);
		paint3.setColor(Color.WHITE);
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		paint3.setShadowLayer(30, 5, 2, Color.GREEN);
		canvas.drawCircle(50, 130,30, paint3);*/




	}






}
