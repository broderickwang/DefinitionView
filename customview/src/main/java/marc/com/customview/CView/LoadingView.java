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

		drawSJX(canvas);

	}

	private void drawSJX(Canvas canvas){
		mPaint = new Paint();
		mPaint.setColor(Color.RED);

		Path p = new Path();
		p.moveTo(mWidth/2,(mHeight/2)-6);
		p.lineTo((mWidth/2)+5.2f,(mHeight/2)+3);
		p.lineTo((mWidth/2)+-5.2f,(mHeight/2)+3);
		p.close();
		canvas.drawPath(p,mPaint);
	}
}
