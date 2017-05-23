package marc.com.customview.CView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import marc.com.customview.R;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/15
 * Time: 13:48
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class TextView extends ViewGroup {

	private Paint mPaint;

	private int mTextSize = 95;

	private int mTextColor = Color.BLACK;

	private String mText;

	public TextView(Context context) {
		super(context);
	}

	public TextView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		//获取自定义属性
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextView);

		mTextSize = array.getDimensionPixelSize(R.styleable.TextView_MarcTextSize,mTextSize);

		mTextColor = array.getColor(R.styleable.TextView_MarcTextColor,mTextColor);

		mText = array.getString(R.styleable.TextView_MartText);

		array.recycle();


		mPaint = new Paint();
		//抗锯齿
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(mTextSize);
		mPaint.setColor(mTextColor);
	}

	public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//获取宽高的模式
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		//获取确定的值，不需要计算，直接获取
		int width = MeasureSpec.getSize(widthMeasureSpec);

		//wrap_content计算值
		if(widthMode == MeasureSpec.AT_MOST){
			//计算宽度 字体的长度，字体的大小有关 用画笔测量
			Rect bounds = new Rect();
			//获取文本的rect
			mPaint.getTextBounds(mText,0,mText.length(),bounds);
			width = bounds.width();
		}

		int height = MeasureSpec.getSize(heightMeasureSpec);
		if(heightMode == MeasureSpec.AT_MOST){
			//计算高度 字体的长度，字体的大小有关 用画笔测量
			Rect bounds = new Rect();
			//获取文本的rect
			mPaint.getTextBounds(mText,0,mText.length(),bounds);
			height = bounds.height();
		}

		//设置控件的宽高
		setMeasuredDimension(width,height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()){
			case MotionEvent.ACTION_UP:
				break;
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_MOVE:
				break;
		}
		return super.onTouchEvent(event);
	}
}
