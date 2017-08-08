package marc.com.customview.CView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.TreeMap;

/**
 * Created by 王成达
 * Date: 2017/7/10
 * Time: 15:05
 * Version: 1.0
 * Description:饼形图
 * Email:wangchengda1990@gmail.com
 **/
public class PieView extends View {
	Paint mPaintRed,mPaintGreen,mPaintBlue,mPaintBlack;
	public PieView(Context context) {
		this(context,null);
	}

	public PieView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		mPaintBlack = new Paint();
		mPaintBlack.setColor(Color.BLACK);
		mPaintBlack.setAntiAlias(true);

		mPaintBlue = new Paint();
		mPaintBlue.setColor(Color.BLUE);
		mPaintBlue.setAntiAlias(true);

		mPaintGreen = new Paint();
		mPaintGreen.setColor(Color.GREEN);
		mPaintGreen.setAntiAlias(true);

		mPaintRed = new Paint();
		mPaintRed.setColor(Color.RED);
		mPaintRed.setAntiAlias(true);
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

		int WD = Math.min(getWidth(),getHeight());

		canvas.drawArc(0,0,WD,WD,0,90,true,mPaintRed);
		canvas.drawArc(0,0,WD,WD,90,30,true,mPaintBlack);
		canvas.drawArc(0,0,WD,WD,120,80,true,mPaintBlue);
		canvas.drawArc(0,0,WD,WD,200,160,true,mPaintGreen);
	}

}
