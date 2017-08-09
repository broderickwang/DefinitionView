package marc.com.customview.CView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import marc.com.customview.Util.ViewUtils;
import marc.com.customview.R;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/7/5
 * Time: 12:58
 * Version: 1.0
 * Description:圆形进度条
 * Email:wangchengda1990@gmail.com
 **/
public class ProgressBar extends View {
	private int mInnerColor = Color.RED;
	private int mOutterColor = Color.BLUE;
	private int mRoundWidth = 10;
	private float mTextSize = 15;
	private int mTextColor = Color.RED;

	private Paint mInnerPaint;
	private Paint mOutterPaint;
	private Paint mTextPaint;

	private int mMax = 100;

	private int progress = 0;

	public ProgressBar(Context context) {
		this(context,null);
	}

	public ProgressBar(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public ProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		//获取自定义属性
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressBar);
		mInnerColor = array.getColor(R.styleable.ProgressBar_innerBackground, mInnerColor);
		mOutterColor = array.getColor(R.styleable.ProgressBar_outterBackground, mOutterColor);
		mTextColor = array.getColor(R.styleable.ProgressBar_progressTextColor,mTextColor);
		mRoundWidth = (int)array.getDimension(R.styleable.ProgressBar_roundWidth,
				ViewUtils.dip2px(mRoundWidth,getResources().getDisplayMetrics()));
		mTextSize = array.getDimensionPixelSize(R.styleable.ProgressBar_progressTextSize,
				ViewUtils.sp2dx(mTextSize,getResources().getDisplayMetrics()));
		array.recycle();

		mInnerPaint = new Paint();
		mInnerPaint.setAntiAlias(true);
		mInnerPaint.setColor(mInnerColor);
		mInnerPaint.setStrokeWidth(mRoundWidth);
		mInnerPaint.setStyle(Paint.Style.STROKE);

		mOutterPaint = new Paint();
		mOutterPaint.setAntiAlias(true);
		mOutterPaint.setColor(mOutterColor);
		mOutterPaint.setStrokeWidth(mRoundWidth);
		mOutterPaint.setStyle(Paint.Style.STROKE);

		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(mTextColor);
		mTextPaint.setTextSize(mTextSize);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(Math.min(width,height),Math.min(width,height));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int center = getWidth()/2;
		//画内圆
		canvas.drawCircle(center,center,center-mRoundWidth/2,mInnerPaint);

		//画外圆
		float percent = (float) progress/mMax;
		canvas.drawArc(mRoundWidth/2,mRoundWidth/2,getWidth()-mRoundWidth/2,getWidth()-mRoundWidth/2,0,360*percent,false,mOutterPaint);

		//画文字
		String text = (int)(percent*100)+"%";
		Rect txtRect = new Rect();
		mTextPaint.getTextBounds(text,0,text.length(),txtRect);

		int x = getWidth()/2-txtRect.width()/2;
		//画基线
		Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
		int dy = (fontMetricsInt.bottom-fontMetricsInt.top)/2-fontMetricsInt.bottom;
		int baseLine = getHeight()/2 + dy;

		canvas.drawText(text,x,baseLine,mTextPaint);
	}

	public synchronized void setMax(int max){
		if(mMax < 0){
			throw new IllegalArgumentException("please set the value is bigger than 0 !");
		}
		this.mMax = max;
	}

	public synchronized void setProgress(int progress) {
		if(progress<0 || progress>mMax){
			throw new IllegalArgumentException("please set the value between 0 and "+ mMax+" !");
		}
		this.progress = progress;
		invalidate();
	}
}
