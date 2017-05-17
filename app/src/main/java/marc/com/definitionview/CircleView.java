package marc.com.definitionview;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/16
 * Time: 16:31
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class CircleView extends View {

	private int mWidth;

	private int mHeight;

	private Paint mPaint;

	public CircleView(Context context) {
		this(context,null);
	}

	public CircleView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		if(mPaint == null){
			mPaint = new Paint();
			mPaint.setColor(Color.BLACK);
		}
	}

	public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(mWidth/2,mHeight/2,400,mPaint);

		canvas.drawCircle(mWidth/2,mHeight/2,380,mPaint);

		for(int i=0;i<360;i+=10){
			canvas.drawLine(0,380,0,400,mPaint);
			canvas.rotate(10);
		}
	}
}
