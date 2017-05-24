package marc.com.customview.CView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import marc.com.customview.CView.marc.com.customview.util.ViewUtils;
import marc.com.customview.R;

/**
 * Created by hannahxian on 2017/5/24.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class StepView extends View {

    private int mOuterColor = Color.BLUE;

    private int mInnerColor = Color.RED;

    private int mCircleWidth = 5;

    private int mTextColor = Color.BLACK;

    private int mTextSize = 10;

    private Paint mPaint;

    public StepView(Context context) {
        this(context,null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //分析效果
        //确定自定义属性

        TypedArray array = context.obtainStyledAttributes(R.styleable.StepView);

        mOuterColor = array.getColor(R.styleable.StepView_outerColor,mOuterColor);

        mInnerColor = array.getColor(R.styleable.StepView_innerColor,mInnerColor);

        mCircleWidth = array.getDimensionPixelOffset(R.styleable.StepView_circleWidth, ViewUtils.sp2dx(mCircleWidth,getResources().getDisplayMetrics()));

        mTextColor = array.getColor(R.styleable.StepView_svTextColor,mTextColor);

        mTextSize = array.getDimensionPixelSize(R.styleable.StepView_svTextSize,ViewUtils.sp2dx(mTextSize,getResources().getDisplayMetrics()));

        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        //在布局中使用
        //在view中获取自定义属性
    }

    //onMeasure
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //调用者在布局文件中可能是wrap_content，或者宽度和高度不一致

        //宽高不一致，取最大的
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width>height?width:height,width>height?width:height);
    }

    //画
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画外层大弧
        mPaint.setColor(mOuterColor);
        RectF rectF = new RectF(mCircleWidth/2,mCircleWidth/2,getWidth()-mCircleWidth/2,getHeight()-mCircleWidth/2);
        canvas.drawArc(rectF,135,270,false,mPaint);

        //画内层小弧
        mPaint.setColor(mInnerColor);
        canvas.drawArc(rectF,135,170,false,mPaint);
        //写文字
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        canvas.drawText("1800",getWidth()/2,getHeight()/2,mPaint);
    }
}
