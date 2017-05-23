package marc.com.customview.CView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/18
 * Time: 16:14
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class A extends View {

	Scroller mScroller;

	public A(Context context) {
		super(context);
	}

	public A(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		mScroller = new Scroller(context);
	}

	public A(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	private void smoothScrollTo(int destX,int destY){
		int scrollX = getScrollX();
		int delta = destX - scrollX;
		mScroller.startScroll(scrollX,0,delta,0,1000);
		invalidate();
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
	}

	@Override
	public void scrollTo(@Px int x, @Px int y) {
		super.scrollTo(x, y);
	}

	@Override
	public void scrollBy(@Px int x, @Px int y) {
		super.scrollBy(x, y);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		return super.dispatchTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	
}
