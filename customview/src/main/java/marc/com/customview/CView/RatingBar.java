package marc.com.customview.CView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import marc.com.customview.R;

/**
 * Created by hannahxian on 2017/7/11.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class RatingBar extends View {

    private Bitmap mNormalBitmap;
    private Bitmap mFocusBitmap;
    private int mGradeNumber = 5;

    private int mSelectPosition = -1;

    public RatingBar(Context context) {
        this(context,null);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array= context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        int starNormal = array.getResourceId(R.styleable.RatingBar_starNormal,0);
        if(starNormal == 0){
            throw new RuntimeException("请设置starNormal属性");
        }

        mNormalBitmap = BitmapFactory.decodeResource(getResources(),starNormal);

        int starFocus = array.getResourceId(R.styleable.RatingBar_starFocus,0);
        if(starFocus ==0 ){
            throw new RuntimeException("请设置starNormal属性");
        }

        mFocusBitmap = BitmapFactory.decodeResource(getResources(),starFocus);
        mGradeNumber = array.getInt(R.styleable.RatingBar_gradeNumber,mGradeNumber);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //高度 一张图片的高度，需要添加padding
        int height = mFocusBitmap.getHeight()+getPaddingBottom()+getPaddingTop();
        //宽度，就是几颗星星的宽度
        int range = (mGradeNumber-1)*3;//+间隔\ 设置默认宽度为3
        int width = mFocusBitmap.getWidth() * mGradeNumber + range+getPaddingLeft()+getPaddingRight();
        Log.d("TAG", "onMeasure: bitmap"+mNormalBitmap.getWidth() + "-"+mNormalBitmap.getHeight());
        Log.d("TAG", "onMeasure: "+height + "-"+width);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制图片
        for (int i=0;i<mGradeNumber;i++){
            int x = getPaddingLeft()+i*3;
            if(i > mSelectPosition-1 )
                canvas.drawBitmap(mNormalBitmap,i*mNormalBitmap.getWidth()+x,getPaddingTop(),null);
            else
                canvas.drawBitmap(mFocusBitmap,i*mFocusBitmap.getWidth()+x,getPaddingTop(),null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理交互 移动 按下 处理逻辑都是一样，都是判断手指的位置，根据当前位置，计算出分数 然后再去刷新界面
        //
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                //event.getRawX();//获取屏幕位置 getX获取相当于当前控件的位置
                float moveX = event.getX();
                int add = 0;
                if(moveX%mNormalBitmap.getWidth() > 0){
                    add = 1;
                }
                mSelectPosition = (int)moveX/mNormalBitmap.getWidth() + add;
                Log.d("TAG", "onTouchEvent: "+mSelectPosition);
                invalidate();
                break;
        }
        //super默认返回的是false，即不处理。如果down进来，down以后的事件就进不来了，就处理不了，所以如果用super，默认则moving是没有效果的。
        return true;
    }
}
