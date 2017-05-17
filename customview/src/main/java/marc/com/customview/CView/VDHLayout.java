package marc.com.customview.CView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
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

	public VDHLayout(Context context) {
		super(context);
	}

	public VDHLayout(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
			@Override
			public boolean tryCaptureView(View child, int pointerId) {
				//返回ture则表示可以捕获该view，你可以根据传入的第一个view参数决定哪些可以捕获
				return true;
			}

			/*clampViewPositionHorizontal,
			clampViewPositionVertical
			可以在该方法中对child移动的边界进行控制，
			left , top 分别为即将移动到的位置，
			比如横向的情况下，
			我希望只在ViewGroup的内部移动，
			即：
			最小>=paddingleft，
			最大<=ViewGroup.getWidth()-paddingright-child.getWidth
			如下代码编写:
			 public int clampViewPositionHorizontal(View child, int left, int dx)
            {
                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - mDragView.getWidth() - leftBound;

                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

                return newLeft;
            }
			*/

			@Override
			public int clampViewPositionHorizontal(View child, int left, int dx) {
				return left;
			}

			@Override
			public int clampViewPositionVertical(View child, int top, int dy) {
				return top;
			}
		});
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
}
