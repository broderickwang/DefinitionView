package marc.com.customview.CView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import marc.com.customview.CView.marc.com.customview.util.Indicator;
import marc.com.customview.R;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/25
 * Time: 09:17
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class CircleIndicatorView extends View implements ViewPager.OnPageChangeListener {

	private final int NONE = 0 ;
	private final int NUMBER = 1;
	private final int LETTER = 2;

	private String[] LETTERS = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	private int mSelectColor = Color.RED;

	private int mNSelectColor = Color.GRAY;

	private int mBorderWidth = 5;

	private int mFillMode = NUMBER;

	private int mTextColor = Color.BLACK;

	private int mTextSize = 13;

	private int mRadius = 5;

	private int mSpace = 25;

	private Paint mCirclePaint,mTextPaint, mArcPaint;

	private int mCount;

	private int mSelectPosition;

	private List<Indicator> mIndicators;

	private ViewPager mViewPager;

	private OnIndicatorClickListener mOnIndicatorClickListener;

	/**
	 * Indicator 点击回调
	 */
	public interface OnIndicatorClickListener{
		public void onSelected(int position);
	}

	public CircleIndicatorView(Context context) {
		this(context,null);
	}

	public CircleIndicatorView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public CircleIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		getAttrs(attrs,context);

		initPaint();
	}

	private void initPaint(){
		mCirclePaint = new Paint();
		mCirclePaint.setAntiAlias(true);

		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setColor(mTextColor);

		mArcPaint = new Paint();
		mArcPaint.setAntiAlias(true);
		mArcPaint.setStrokeWidth(mBorderWidth);
		mArcPaint.setStyle(Paint.Style.STROKE);
		mArcPaint.setColor(Color.parseColor("#abcdef"));

		mIndicators = new ArrayList<>();

	}

	private void getAttrs(AttributeSet attrs,Context context){
		// 获取 自定义属性
		TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.CircleIndicatorView);
		mSelectColor = array.getColor(R.styleable.CircleIndicatorView_selectColor,mSelectColor);
		mNSelectColor = array.getColor(R.styleable.CircleIndicatorView_nselectColor,mNSelectColor);
		mBorderWidth = array.getDimensionPixelSize(R.styleable.CircleIndicatorView_borderWidth, ChangeSp2Dx(mBorderWidth));
		mTextColor = array.getColor(R.styleable.CircleIndicatorView_indicatorTextColor,mTextColor);
		mTextSize = array.getDimensionPixelSize(R.styleable.CircleIndicatorView_indicatorTextSize, ChangeSp2Dx(mTextSize));
		mRadius = array.getDimensionPixelOffset(R.styleable.CircleIndicatorView_indicatorRadius, ChangeSp2Dx(mRadius));
		mFillMode = array.getInt(R.styleable.CircleIndicatorView_fill_mode,mFillMode);
		mSpace = array.getDimensionPixelOffset(R.styleable.CircleIndicatorView_indicatorSpace, ChangeSp2Dx(mSpace));
		array.recycle();
	}

	private void measureIndicator(){
		mIndicators.clear();
		for (int i=0;i<mCount;i++){
			Indicator indicator = new Indicator();
			if (i == 0){
				indicator.setX(mRadius+mBorderWidth);
				indicator.setY(getMeasuredHeight()/2);
			}else{
				indicator.setX(mIndicators.get(i-1).getX()+(mRadius+mBorderWidth)*2+mSpace);
				indicator.setY(getMeasuredHeight()/2);
			}
			mIndicators.add(indicator);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int i=0;i<mIndicators.size();i++){
			Indicator indicator = mIndicators.get(i);
			int cx = indicator.getX();
			int cy = indicator.getY();

			if(i == mSelectPosition){
				mCirclePaint.setStyle(Paint.Style.FILL);
				mCirclePaint.setColor(mSelectColor);
			}else{
				mCirclePaint.setColor(mNSelectColor);
				if(mFillMode != NONE){
					mCirclePaint.setStyle(Paint.Style.STROKE);
				}else{
					mCirclePaint.setStyle(Paint.Style.FILL);
				}
			}
			canvas.drawCircle(cx,cy,mRadius, mCirclePaint);

			canvas.drawCircle(cx,cy,mRadius,mArcPaint);

			if(mFillMode != NONE){

				String tx = "";
				if(mFillMode == NUMBER)
					tx = String.valueOf(i+1);
				else if(mFillMode == LETTER){
					tx = LETTERS[i];
				}
				Rect bounds = new Rect();
				mTextPaint.getTextBounds(tx,0,tx.length(),bounds);

				int txtWidth = bounds.width();
				int txtHeight = bounds.height();

				canvas.drawText(tx,cx-txtWidth/2,cy+txtHeight/2,mTextPaint);
			}
		}
	}

	@Override
	public void onPageSelected(int position) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		mSelectPosition = position;
		invalidate();
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	public void setViewPager(ViewPager viewPager){
		releaseViewPager();
		if(viewPager == null){
			return;
		}
		mViewPager = viewPager;
		mViewPager.addOnPageChangeListener(this);
		int count = mViewPager.getAdapter().getCount();
		setCount(count);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = (mRadius+mBorderWidth)* 2 * mCount + mSpace *(mCount - 1);
		int height = mRadius * 2 + mSpace * 2;

		setMeasuredDimension(width,height);

		measureIndicator();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float xPoint = 0;
		float yPoint = 0;
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				xPoint = event.getX();
				yPoint = event.getY();
				handleActionDown(xPoint,yPoint);
				break;

		}
		return super.onTouchEvent(event);
	}

	private void handleActionDown(float x,float y){
		for(int i=0;i<mIndicators.size();i++){
			Indicator indicator = mIndicators.get(i);
			if(x < (indicator.getX() + mRadius+mBorderWidth)
					&& x >=(indicator.getX() - (mRadius + mBorderWidth))
					&& y >= (y - (indicator.getY()+mBorderWidth))
					&& y <(indicator.getY()+mRadius+mBorderWidth)){
				// 找到了点击的Indicator
				// 切换ViewPager
				mViewPager.setCurrentItem(i,false);
				// 回调
				if(mOnIndicatorClickListener!=null){
					mOnIndicatorClickListener.onSelected(i);
				}
				break;
			}
		}
	}
	private void releaseViewPager(){
		if(mViewPager!=null){
			mViewPager.removeOnPageChangeListener(this);
			mViewPager = null;
		}

	}
	public void setOnIndicatorClickListener(OnIndicatorClickListener onIndicatorClickListener) {
		mOnIndicatorClickListener = onIndicatorClickListener;
	}

	private void setCount(int count) {
		mCount = count;
		invalidate();
	}
	/**
	 * 像素转dx
	 * @param sp
	 * @return dx
	 */
	private int ChangeSp2Dx(int sp){
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,getResources().getDisplayMetrics());
	}
}
