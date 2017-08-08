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
 * Date: 2017/7/7
 * Time: 12:21
 * Version: 1.0
 * Description:三角 圆 正方形 变化
 * Email:wangchengda1990@gmail.com
 **/
public class ShapeView extends View {

	private Paint mPaint;

	private Type mCurrentType = Type.Circle;

	private Path mPath;

	public ShapeView(Context context) {
		this(context,null);
	}

	public ShapeView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(Math.min(width,height),Math.min(width,height));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int center = getWidth()/2;

		switch (mCurrentType){
			case Circle:
				mPaint.setColor(Color.RED);
				canvas.drawCircle(center,center,center,mPaint);
				break;
			case Square:
				mPaint.setColor(Color.BLUE);
				canvas.drawRect(0,0,getWidth(),getWidth(),mPaint);
				break;
			case Triangle:
				mPaint.setColor(Color.GREEN);
				if(mPath == null){
					mPath = new Path();
					mPath.moveTo(center,0);
					mPath.lineTo(0, (float) (center*Math.sqrt(3)));
					mPath.lineTo(getWidth(),(float) (center*Math.sqrt(3)));
					mPath.close();
				}
				canvas.drawPath(mPath,mPaint);
				break;
		}

	}

	public enum Type{
		Circle,
		Square,
		Triangle
	}

	public void change(){
		switch (mCurrentType){
			case Circle:
				this.mCurrentType = Type.Square;
				break;
			case Square:
				this.mCurrentType = Type.Triangle;
				break;
			case Triangle:
				this.mCurrentType = Type.Circle;
				break;
		}
		invalidate();
	}
}
