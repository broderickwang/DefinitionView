package marc.com.customview.CView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import marc.com.customview.Bean.PieData;

/**
 * Created by 王成达
 * Date: 2017/7/10
 * Time: 15:05
 * Version: 1.0
 * Description:饼形图
 * Email:wangchengda1990@gmail.com
 **/
public class PieView extends View {

	private List<PieData> mDatas;

	private float mStartAngle;

	private Paint mPaint,mTextPaint;

	private int mWidth,mHeight;

	private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
			0xFFE6B800, 0xFF7CFC00};

	public PieView(Context context) {
		this(context,null);
	}

	public PieView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mStartAngle = 0;
		mPaint = new Paint();
		mPaint.setAntiAlias(true);

		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextSize(15);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = w;
		mHeight = h;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(Math.min(mWidth,mHeight),Math.min(mWidth,mHeight));
//		super.onMeasure(widthMeasureSpec,heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mDatas==null || mDatas.size()==0)
			throw new RuntimeException("please set the current data!");

		float r = (float) ((float) Math.min(mWidth,mHeight)/2*0.8);
		canvas.translate(mWidth/2,mWidth/2);
		RectF rectF = new RectF(-r,-r,r,r);
		float currentAngle = mStartAngle;

		for (int i = 0; i < mDatas.size(); i++) {
			PieData data = mDatas.get(i);
			mPaint.setColor(data.getColor());
			canvas.drawArc(rectF,currentAngle,data.getAngle(),true,mPaint);
			/*canvas.rotate(data.getAngle()/2);
			canvas.drawText(data.getName(),mWidth/2+r/2,mWidth/2,mTextPaint);*/
			currentAngle += data.getAngle();
		}
	}

	public void setData(List<PieData> list){
		if(list==null || list.size()==0){
			throw new IllegalArgumentException("please set a list not null!");
		}
		this.mDatas = list;
		initData(this.mDatas);
		invalidate();
	}

	public void setStartAngle(float angle){
		this.mStartAngle = angle;
	}

	// 初始化数据
	private void initData(List<PieData> mData) {
		if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
			return;

		float sumValue = 0;
		for (int i = 0; i < mData.size(); i++) {
			PieData pie = mData.get(i);

			sumValue += pie.getValue();       //计算数值和

			int j = i % mColors.length;       //设置颜色
			pie.setColor(mColors[j]);
		}

		float sumAngle = 0;
		for (int i = 0; i < mData.size(); i++) {
			PieData pie = mData.get(i);

			float percentage = pie.getValue() / sumValue;   // 百分比
			float angle = percentage * 360;                 // 对应的角度

			pie.setPercentage(percentage);                  // 记录百分比
			pie.setAngle(angle);                            // 记录角度大小
			sumAngle += angle;

			Log.i("angle", "" + pie.getAngle());
		}
	}
}
