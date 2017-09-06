package marc.com.customview.Listner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.text.method.Touch;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import marc.com.customview.CView.BubbleView;

/**
 * Created by 王成达
 * Date: 2017/9/6
 * Time: 09:54
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class BubbleMessageTouchListner implements View.OnTouchListener {

	private View mStaticView;
	private Context mContext;
	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mParams;
	private BubbleView mView;

	public BubbleMessageTouchListner(View mStaticView, Context mContext) {
		this.mStaticView = mStaticView;
		this.mContext = mContext;

		mView = new BubbleView(mContext);

		mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		mParams = new WindowManager.LayoutParams(/*ViewGroup.LayoutParams.MATCH_PARENT
				, ViewGroup.LayoutParams.MATCH_PARENT*/);
		//Windowmanager背景透明
		mParams.format = PixelFormat.TRANSPARENT;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				mStaticView.setVisibility(View.INVISIBLE);
				//在WindowManager中添加一个BubbleView
				mWindowManager.addView(mView,mParams);

				//初始化BubbleView的点 getX()是获得相对于父布局的位置 ,应该获取屏幕的位置
				//使用getRawY获取的高度还是差一点。还是差一个状态栏的高度
				mView.initPoint(event.getRawX(),event.getRawY()- getStatusHeight());
				//获取没有动的view的bitmap，再去画这个bitmap
				mView.setDragBitmap(getBitmapByView(mStaticView));

				break;
			case MotionEvent.ACTION_MOVE:
				mView.updateDragPoint(event.getRawX(),event.getRawY()- getStatusHeight());
				break;
			case MotionEvent.ACTION_UP:
				break;
		}
		return true;
	}

	/**
	 * 从view中获取bitmap  截屏如何实现？？
	 * @param view
	 * @return
	 */
	private Bitmap getBitmapByView(View view) {
		view.buildDrawingCache();
		Bitmap b = view.getDrawingCache();
		return b;
	}

	/**
	 * 获取状态栏高度
	 * @return
	 */
	private int getStatusHeight(){
		int resouceId = mContext.getResources().getIdentifier("status_bar_height","dimen","android");
		if(resouceId > 0){
			return mContext.getResources().getDimensionPixelSize(resouceId);
		}
		return dip2dx(25);
	}
	private int dip2dx(int dip) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,mContext.getResources().getDisplayMetrics());
	}
}
