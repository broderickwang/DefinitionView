package marc.com.customview.Listner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import marc.com.customview.CView.BubbleView;
import marc.com.customview.R;

/**
 * Created by 王成达
 * Date: 2017/9/6
 * Time: 09:54
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class BubbleMessageTouchListner implements View.OnTouchListener,BubbleView.BubbleActionListner {

	private View mStaticView;
	private Context mContext;
	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mParams;
	private BubbleView mView;
	//爆炸的动画容器
	private FrameLayout mBoomFrame;
	private ImageView mBoomImage;
	private BubbleView.BubbleDisappearListner mDisapperaListner;

	public BubbleMessageTouchListner(View view, Context mContext, BubbleView.BubbleDisappearListner listner) {
		this.mStaticView = view;
		this.mContext = mContext;
		this.mDisapperaListner = listner;

		mView = new BubbleView(mContext);
		mView.setBubbleActionListner(this);

		mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		mParams = new WindowManager.LayoutParams(/*ViewGroup.LayoutParams.MATCH_PARENT
				, ViewGroup.LayoutParams.MATCH_PARENT*/);
		//Windowmanager背景透明
		mParams.format = PixelFormat.TRANSPARENT;

		mBoomFrame = new FrameLayout(mContext);
		mBoomImage = new ImageView(mContext);
		mBoomImage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
				, ViewGroup.LayoutParams.WRAP_CONTENT));
		mBoomFrame.addView(mBoomImage);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				//在WindowManager中添加一个BubbleView
				mWindowManager.addView(mView,mParams);

				Bitmap b = getBitmapByView(mStaticView);

				//初始化BubbleView的点 getX()是获得相对于父布局的位置 ,应该获取屏幕的位置
				//使用getRawY获取的高度还是差一点。还是差一个状态栏的高度
				//保证固定圆的中心在view的中心
				int[] xy = new int[2];
				mStaticView.getLocationOnScreen(xy);
				mView.initPoint(xy[0]+ b.getWidth()/2,xy[1] + b.getHeight()/2 - getStatusHeight());
				//获取没有动的view的bitmap，再去画这个bitmap
				mView.setDragBitmap(b);
				//将原来的视图隐藏
				mStaticView.setVisibility(View.INVISIBLE);

				break;
			case MotionEvent.ACTION_MOVE:
				mView.updateDragPoint(event.getRawX(),event.getRawY()- getStatusHeight());
				break;
			case MotionEvent.ACTION_UP:
				mView.handleActionUp();
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

	@Override
	public void BubbleRestore() {
		mStaticView.setVisibility(View.VISIBLE);
		mWindowManager.removeView(mView);
	}

	@Override
	public void BubbleBoom(PointF position) {
		//执行爆炸动画
		//原来的view移除
		mWindowManager.removeView(mView);
		//在windowmanager上面添加一个爆炸动画
		mWindowManager.addView(mBoomFrame,mParams);

		mBoomImage.setBackgroundResource(R.drawable.anim_bubble_pop);
		AnimationDrawable a = (AnimationDrawable) mBoomImage.getBackground();
		mBoomImage.setX(position.x-a.getIntrinsicWidth()/2);
		mBoomImage.setY(position.y-a.getIntrinsicHeight()/2);
		a.start();
		mBoomImage.postDelayed(new Runnable() {
			@Override
			public void run() {
				mWindowManager.removeView(mBoomFrame);
				if(mDisapperaListner!=null)
					mDisapperaListner.disappear(mStaticView);
			}
		},getDrawableAnimateTime(a));
	}

	private long getDrawableAnimateTime(AnimationDrawable a) {
		long time = 0;
		int number = a.getNumberOfFrames();
		for (int i=0;i<number;i++){
			time += a.getDuration(i);
		}
		return time;
	}
}
