package marc.com.customview.CView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by 王成达
 * Date: 2017/7/21
 * Time: 14:30
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class HorizontalScrollViewEx extends ViewGroup {

	private int mChildeSize;
	private int mChildeWidth;
	private int mChildeIndex;

	private int mLastX = 0;
	private int mLastY = 0;

	private int mLastXIntercept = 0;
	private int mLastYIntercept = 0;

	private Scroller mScroller;
	private VelocityTracker mVelocityTracker;

	private void init(){
		mScroller = new Scroller(getContext());
		mVelocityTracker = VelocityTracker.obtain();

		mChildeSize = getChildCount();

	}

	public HorizontalScrollViewEx(Context context) {
		this(context,null);
	}

	public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean intercepted = false;
		int x = (int) ev.getX();
		int y = (int) ev.getY();

		switch (ev.getAction()){
			case MotionEvent.ACTION_DOWN:
				intercepted = false;
				if(!mScroller.isFinished()){
					mScroller.abortAnimation();
					intercepted = true;
				}
				break;
			case MotionEvent.ACTION_MOVE:
				int delX = x - mLastXIntercept;
				int delY = y - mLastXIntercept;
				if(Math.abs(delX) > Math.abs(delY)){
					intercepted = true;
				}else{
					intercepted = false;
				}
				break;
			case MotionEvent.ACTION_UP:
				intercepted = false;
				break;
		}

		mLastX = x;
		mLastY = y;

		mLastXIntercept = x;
		mLastYIntercept = y;
		return intercepted;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mVelocityTracker.addMovement(event);

		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				if(!mScroller.isFinished()){
					mScroller.abortAnimation();
				}
				break;
			case MotionEvent.ACTION_MOVE:
				int deltaX = x - mLastX;
				int deltaY = y - mLastY;

				scrollBy(-deltaX,0);
				break;
			case MotionEvent.ACTION_UP:
				int scrollX = getScrollX();
				int scrollChildIndex = scrollX/mChildeWidth;
				mVelocityTracker.computeCurrentVelocity(1000);
				float xVelocity = mVelocityTracker.getXVelocity();
				if(Math.abs(xVelocity) >= 50){
					mChildeIndex = xVelocity>0?mChildeIndex-1:mChildeIndex+1;
				}else{
					mChildeIndex = (scrollX+mChildeWidth/2)/mChildeWidth;
				}
				mChildeIndex = Math.max(0,Math.min(mChildeIndex,mChildeSize-1));
				int dx = mChildeIndex*mChildeWidth-scrollX;
				scrollBy(dx,0);
				mVelocityTracker.clear();
				break;
		}
		mLastY = y;
		mLastX = x;
		return true;
	}

	@Override
	public void computeScroll() {
		if(mScroller.computeScrollOffset()){
			scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
			postInvalidate();
		}
	}
}
