package marc.com.customview.CView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 王成达
 * Date: 2017/8/21
 * Time: 15:24
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MoMoLoading extends View {

	private int mDistance = 10;
	private Paint mPaint;
	private int mRadius = 5;

	public MoMoLoading(Context context) {
		this(context,null);
	}

	public MoMoLoading(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public MoMoLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.parseColor("#abcdef"));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int base = mRadius+mDistance/2;
		canvas.translate(getWidth()/2,getHeight()/2);
		canvas.drawCircle(-base,0,mRadius,mPaint);
		canvas.drawCircle(base,0,mRadius,mPaint);
		canvas.drawCircle(-(base+mRadius*2+mDistance),0,mRadius,mPaint);
		canvas.drawCircle((base+mRadius*2+mDistance),0,mRadius,mPaint);

	}
}
