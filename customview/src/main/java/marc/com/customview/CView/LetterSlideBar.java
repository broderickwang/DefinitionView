package marc.com.customview.CView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import marc.com.customview.CView.marc.com.customview.util.ViewUtils;

/**
 * Created by hannahxian on 2017/7/12.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class LetterSlideBar extends View {
    private Paint mPaint,mCenterPaint;
    private int mLetterSize = 10;
    private int mDefineLetterColor = Color.BLACK;
    private int mSelectLettorColor = Color.RED;
    private String[] mLetters = {"A","B","C","D","E","F","G","H","I","J","K",
            "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#"};
    private int mCurrentPosition = -1;

    private OnTouchListner mTouchlistner;

    public LetterSlideBar(Context context) {
        this(context,null);
    }

    public LetterSlideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LetterSlideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(ViewUtils.sp2dx(mLetterSize,context.getResources().getDisplayMetrics()));
        mPaint.setColor(mDefineLetterColor);
    }

    public void setTouchListner(OnTouchListner listner){
        this.mTouchlistner = listner;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //计算宽度 = padding + letter width;
        float letterWidth = mPaint.measureText("A",0,1);
        int width = getPaddingLeft()+getPaddingRight() + (int)letterWidth;

        //高度直接获取
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int itemHeight = (getHeight()-getPaddingTop()-getPaddingBottom())/mLetters.length;

        for (int i=0;i<mLetters.length;i++){
            if(i == mCurrentPosition)
                mPaint.setColor(mSelectLettorColor);
            else if(i!= mCurrentPosition && mPaint.getColor()!=mDefineLetterColor)
                mPaint.setColor(mDefineLetterColor);
            int centerY = itemHeight/2 + i*itemHeight + getPaddingTop();
            Paint.FontMetrics fontMetrics =  mPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom);
            int baseLine = centerY + dy;

            Rect rect = new Rect();
            mPaint.getTextBounds(mLetters[i],0,mLetters[i].length(),rect);
            int letterWidth = getWidth();/*getPaddingLeft()*/ // 字母的x绘制在中间
            canvas.drawText(mLetters[i],(letterWidth-rect.width())/2,baseLine,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //
                int itemHeight = (getHeight()-getPaddingTop()-getPaddingBottom())/mLetters.length;
                float ey = event.getY();
                int cPosition = mCurrentPosition;
                mCurrentPosition = (int) (ey/itemHeight);
                //如果点击的位置和现在已经选中的相同，就不再调用invalidate，优化性能
                if(cPosition == mCurrentPosition)
                    return true;
                //回调
                if(mTouchlistner != null && mCurrentPosition>=0 && mCurrentPosition<mLetters.length){
                    mTouchlistner.TouchListner(mLetters[mCurrentPosition],true);
                }else if(mCurrentPosition<0 || mCurrentPosition>=mLetters.length) {
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mTouchlistner.TouchListner("",false);
                        }
                    },200);

                }
                invalidate();
                    break;
            case MotionEvent.ACTION_UP:
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTouchlistner.TouchListner("",false);
                    }
                },200);
                break;
        }
        return true;
    }

    public interface OnTouchListner{
        void TouchListner(String str,boolean isTouch);
    }
}
