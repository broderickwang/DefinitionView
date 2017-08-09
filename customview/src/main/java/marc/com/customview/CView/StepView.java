package marc.com.customview.CView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import marc.com.customview.Util.ViewUtils;
import marc.com.customview.R;

/**
 * Created by hannahxian on 2017/5/24.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:仿QQ健康 步数记录
 */

public class StepView extends View {

    private int mOuterColor = Color.BLUE;

    private int mInnerColor = Color.RED;

    private int mCircleWidth = 5;

    private int mTextColor = Color.BLACK;

    private int mTextSize = 10;

    private Paint mPaint;

    private Paint mTextPaint;

    private int mStepMax = 10000;

    private int mStepMin;

    private int mCurrentStep;

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

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.StepView);

        mOuterColor = array.getColor(R.styleable.StepView_outerColor,mOuterColor);

        mInnerColor = array.getColor(R.styleable.StepView_innerColor,mInnerColor);

        mCircleWidth = array.getDimensionPixelSize(R.styleable.StepView_circleWidth, ViewUtils.sp2dx(mCircleWidth,getResources().getDisplayMetrics()));

        mTextColor = array.getColor(R.styleable.StepView_svTextColor,mTextColor);

        mTextSize = array.getDimensionPixelSize(R.styleable.StepView_svTextSize,ViewUtils.sp2dx(mTextSize,getResources().getDisplayMetrics()));

        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mPaint.setAntiAlias(true);
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

        setMeasuredDimension(width>height?height:width,width>height?height:width);
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
        float b = (float)mCurrentStep/(float)mStepMax;
        canvas.drawArc(rectF,135,270*b,false,mPaint);
        //写文字
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        int dy = (int) ((fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom);
        int baseLine = getHeight()/2 + dy;
        Rect bounds = new Rect();
        String mText = mCurrentStep+"";
        mPaint.getTextBounds(mText,0,mText.length(),bounds);
        int x = getWidth()/2-(bounds.right-bounds.left);
        canvas.drawText(mCurrentStep+"",x,baseLine,mTextPaint);
    }

    public synchronized void setStepMax(int stepMax){
        this.mStepMax = stepMax;
    }

    public synchronized void setStepMin(int stepMin){
        this.mStepMin = stepMin;
    }

    public synchronized void setCurrentStep(int currentStep){
        this.mCurrentStep = currentStep;
        invalidate();
    }
}
