package marc.com.definitionview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/16
 * Time: 11:15
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class LoadingView extends View {

	private int mWidth;

	private int mHeight;

	private Paint mPaint;

	private Point mCurrentPoint;

	private int flag = 0;

	public LoadingView(Context context) {
		super(context);
	}

	public LoadingView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		mWidth = MeasureSpec.getSize(widthMeasureSpec);

		mHeight = MeasureSpec.getSize(heightMeasureSpec);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		if(mCurrentPoint == null && mPaint == null){
			mPaint = new Paint();
			mCurrentPoint = new Point(mWidth/2,mHeight/2-200);
			_draw(canvas);
			startAnimator();
		}else{
			_draw(canvas);
		}

		canvas.drawText("玩命加载中",mWidth/2,mHeight+10,mPaint);

	}

	private void _draw(final Canvas canvas){
		if(flag == 0){
			drawSJX(canvas);
		}else if(flag == 1){
			drawCircle(canvas);
		}else if(flag == 2){
			drawRect(canvas);
		}
	}

	private void startAnimator(){
		Point startP = new Point(mWidth/2,mHeight/2-200);
		Point endP = new Point(mWidth/2,mHeight/2);

		ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),
				startP,endP);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Point p = (Point) animation.getAnimatedValue();
				mCurrentPoint = p;
				invalidate();
			}
		});

		animator.setDuration(1000);
		//动画重复
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setRepeatMode(ValueAnimator.REVERSE);
		animator.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {

			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				Log.i("TAG", "onAnimationRepeat: ");
				if(flag == 0){
					flag = 1;
				}else if(flag == 1){
					flag = 2;
				}else if(flag == 2){
					flag = 0;
				}
			}
		});
//		animator.setInterpolator(new BounceInterpolator());
		animator.start();

	}

	private void drawSJX(Canvas canvas ){
		mPaint.setColor(Color.RED);

		Path p = new Path();
		p.moveTo(mCurrentPoint.getX(),mCurrentPoint.getY()-60);
		p.lineTo(mCurrentPoint.getX()+52f,mCurrentPoint.getY()+30);
		p.lineTo(mCurrentPoint.getX()+-52f,mCurrentPoint.getY()+30);
		p.close();
		canvas.drawPath(p,mPaint);
	}
	private void drawCircle(Canvas canvas){
		mPaint.setColor(Color.GREEN);
		canvas.drawCircle(mCurrentPoint.getX(),mCurrentPoint.getY(),60,mPaint);
	}

	private void drawRect(Canvas canvas){

		float cx = mCurrentPoint.getX();
		float cy = mCurrentPoint.getY();

		mPaint.setColor(Color.BLUE);

		canvas.drawRect(cx-40,cy-40,cx+40,cy+40,mPaint);
	}
}
