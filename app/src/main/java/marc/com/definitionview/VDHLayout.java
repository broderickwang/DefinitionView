package marc.com.definitionview;

import android.content.Context;
import android.graphics.*;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/16
 * Time: 15:06
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/


public class VDHLayout extends LinearLayout {

	private ViewDragHelper mDragger;

	private View mDragView;

	private View mAutoBackView;

	private View mEdgeTackerView;

	private android.graphics.Point mAutoBackOriginPos = new Point();

	public VDHLayout(Context context) {
		super(context);
	}

	public VDHLayout(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
			@Override
			public boolean tryCaptureView(View child, int pointerId) {
				//返回ture则表示可以捕获该view，你可以根据传入的第一个view参数决定哪些可以捕获
//				return true;
				return child==mDragView || child==mAutoBackView;
			}

			@Override
			public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
				super.onViewPositionChanged(changedView, left, top, dx, dy);
				Log.d("TAG", "onViewPositionChanged: "+left+","+top+","+dx+","+dy);
			}

			/* *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
			 * clampViewPositionHorizontal,
			 * clampViewPositionVertical
			 * 可以在该方法中对child移动的边界进行控制，
			 * left , top 分别为即将移动到的位置，
			 * 比如横向的情况下，
			 * 我希望只在ViewGroup的内部移动，
			 * 即：
			 * 最小>=paddingleft，
			 * 最大<=ViewGroup.getWidth()-paddingright-child.getWidth
			 * 如下代码编写:
			 * public int clampViewPositionHorizontal(View child, int left, int dx)
             * {
             *     final int leftBound = getPaddingLeft();
             *     final int rightBound = getWidth() - mDragView.getWidth() - leftBound;
             *     final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
             *     return newLeft;
             * }
			 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * */
			@Override
			public int clampViewPositionHorizontal(View child, int left, int dx) {
				final int leftBound = getPaddingLeft();
				final int rightBound = getWidth() - child.getWidth() - leftBound;

				final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

				return newLeft;
			}

			@Override
			public int clampViewPositionVertical(View child, int top, int dy) {
				final int topBound = getPaddingTop();
				final int bottomtBound = getHeight() - child.getHeight() - topBound;

				final int newTop = Math.min(Math.max(top, topBound), bottomtBound);

				return newTop;
			}

			//手指释放的时候回调
			@Override
			public void onViewReleased(View releasedChild, float xvel, float yvel) {
				//mAutoBackView手指释放时可以自动回去
				if(releasedChild == mAutoBackView){
					mDragger.settleCapturedViewAt(mAutoBackOriginPos.x,mAutoBackOriginPos.y);
					invalidate();
				}
			}

			//在边界拖动时回调
			@Override
			public void onEdgeDragStarted(int edgeFlags, int pointerId) {
				mDragger.captureChildView(mEdgeTackerView,pointerId);
			}


			//如果子view是Button，或者给TextView等其他控件添加了clickable = true ，都记得重写下面这两个方法
			@Override
			public int getViewVerticalDragRange(View child) {
				return getMeasuredHeight() - child.getMeasuredHeight();
			}

			@Override
			public int getViewHorizontalDragRange(View child) {
				return getMeasuredWidth() - child.getMeasuredWidth();
			}
		});

		mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
	}

	public VDHLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return mDragger.shouldInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mDragger.processTouchEvent(event);
		return true;
	}

	@Override
	public void computeScroll() {
		if(mDragger.continueSettling(true)){
			invalidate();
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		mAutoBackOriginPos.x = mAutoBackView.getLeft();
		mAutoBackOriginPos.y = mAutoBackView.getTop();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		mDragView = getChildAt(0);

		mAutoBackView = getChildAt(1);

		mEdgeTackerView = getChildAt(2);
	}

}
