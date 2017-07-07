package marc.com.customview.CView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

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

	private Type mType = Type.CIRLE;

	public LoadingView(Context context) {
		this(context,null);
	}

	public LoadingView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		mWidth = MeasureSpec.getSize(widthMeasureSpec);

		mHeight = MeasureSpec.getSize(heightMeasureSpec);

		super.onMeasure(Math.min(mWidth,mHeight), Math.min(mWidth,mHeight));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int center = getWidth()/2;
		mPaint.setColor(Color.RED);
		canvas.drawCircle(center,center,center,mPaint);
		/*switch (mType){
			case CIRLE:
				mPaint.setColor(Color.RED);
				canvas.drawCircle(center,center,center,mPaint);
				break;
			case RECTANGE:
				mPaint.setColor(Color.BLUE);
				canvas.drawRect(0,0,getWidth(),getWidth(),mPaint);
				break;
		}*/
	}

	public enum Type{
		CIRLE,
		RECTANGE,
		TRIANGLE
	}

	public void change(Type type){
		this.mType = type;
		invalidate();
	}

	public Type getmType() {
		return mType;
	}
}
