package marc.com.customview.CView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuPopupHelper;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 王成达
 * User: Broderick
 * Date: 2017/7/10
 * Time: 14:02
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class BackgroundView extends View {
	private Paint mPaint;
	public BackgroundView(Context context) {
		this(context,null);
	}

	public BackgroundView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public BackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.parseColor("#abcdef"));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawColor(Color.parseColor("#abcdef"));
	}
}
