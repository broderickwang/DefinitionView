package marc.com.customview.CView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by 王成达
 * Date: 2017/9/1
 * Time: 09:10
 * Version: 1.0
 * Description:花束直播加载效果
 * Email:wangchengda1990@gmail.com
 **/
public class HuashuLoadView extends View{

	private int mRadius;
	private int mDistance;
	private Paint mPaint;

	private Paint bPaint;

	public HuashuLoadView(Context context) {
		this(context,null);
	}

	public HuashuLoadView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public HuashuLoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();

		ValueAnimator animator = ValueAnimator.ofInt(80,-80);
		animator.setDuration(1000);
		animator.setInterpolator(new LinearInterpolator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int d = (int) animation.getAnimatedValue();
				Log.d("TAG", "onAnimationUpdate: "+d);
				setDistance(d);
			}
		});
		animator.setRepeatCount(-1);
		animator.setRepeatMode(ValueAnimator.REVERSE);
		animator.start();
	}

	private void init() {
		mRadius = 20;
		mDistance = 80;

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.FILL);

		bPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bPaint.setStyle(Paint.Style.FILL);


	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.translate(getWidth()/2,getHeight()/2);

		mPaint.setColor(Color.RED);
		canvas.drawCircle(0,0,mRadius,mPaint);
		mPaint.setColor(Color.YELLOW);
		canvas.drawCircle(-mDistance,0,mRadius,mPaint);
		mPaint.setColor(Color.BLUE);
		canvas.drawCircle(mDistance,0,mRadius,mPaint);

		/*bPaint.setColor(Color.YELLOW);
		Bitmap b = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
		//创建完bitmap之后要用 eraseColor来设置颜色,不然bitmap出不来
		b.eraseColor(Color.parseColor("#FF0000"));//填充颜色

		canvas.drawBitmap(b,0,0,bPaint);*/
	}

	public void setDistance(int distance){
		this.mDistance = distance;
		invalidate();
	}
}
