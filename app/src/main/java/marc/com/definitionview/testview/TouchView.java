package marc.com.definitionview.testview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 王成达
 * Date: 2017/7/12
 * Time: 14:04
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class TouchView extends View {
	public TouchView(Context context) {
		this(context,null);
	}

	public TouchView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
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
		canvas.drawColor(Color.parseColor("#abcdef"));
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.d("TAG", "dispatchTouchEvent running: Touch view ");
		return super.dispatchTouchEvent(event);
	}

	/**
	 * ssd
	 * @param event
	 * @return
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("TAG", "onTouchEvent running: Touch view ");
		return super.onTouchEvent(event);
	}
}
