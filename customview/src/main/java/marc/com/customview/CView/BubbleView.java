package marc.com.customview.CView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import marc.com.customview.Listner.BubbleMessageTouchListner;

/**
 * Created by hannahxian on 2017/9/3.
 */

public class BubbleView extends View {

    private PointF mDragPoint,mFixedPoint;
    private Paint mPaint;

    private int mDefaultRadius = 10;

    private int mMaxDistance = 40;

    private float mGoldPoint = 0.618f;

    private int mFixationRadius;
    private int mFixationRadiusMax = 7;
    private int mFixationRadiusMin = 3;
    private Bitmap mDragBitmap;

    public BubbleView(Context context) {
        this(context,null);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValue();
    }

    private void initValue() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        mDefaultRadius = dip2dx(mDefaultRadius);
        mFixationRadiusMax = dip2dx(mFixationRadiusMax);
        mFixationRadiusMin = dip2dx(mFixationRadiusMin);
        mMaxDistance = dip2dx(mMaxDistance);

    }

    private int dip2dx(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下要去指定当前的位置
                float downX = event.getX();
                float downY = event.getY();
                initPoint(downX, downY);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                updateDragPoint(moveX, moveY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        if(mDragPoint == null || mFixedPoint == null)
            return ;
        canvas.drawCircle(mDragPoint.x,mDragPoint.y,mDefaultRadius,mPaint);

        Path p = getBezeril();
        if(p != null){
            canvas.drawCircle(mFixedPoint.x,mFixedPoint.y,mFixationRadius,mPaint);
            canvas.drawPath(p,mPaint);
        }

        if(mDragBitmap != null)
            canvas.drawBitmap(mDragBitmap,mDragPoint.x-mDragBitmap.getWidth()/2,mDragPoint.y-mDragBitmap.getHeight()/2,null);

    }

    public void updateDragPoint(float x, float y) {
        mDragPoint.x = x;
        mDragPoint.y = y;
        //重新绘制
        invalidate();

        /*double d = Math.sqrt((x-mFixedPoint.x)*(x-mFixedPoint.x) + (y-mFixedPoint.y)*(y-mFixedPoint.y));
        mFixedRadius = (int) (mFixedRadius - d / 14);*/
    }

    public void initPoint(float x, float y) {
        mDragPoint = new PointF(x,y);
        mFixedPoint = new PointF(x,y);
    }
    private double getDistance(PointF point1, PointF point2) {
        return Math.sqrt((point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) * (point1.y - point2.y));
    }

    private Path getBezeril(){
        double distance = getDistance(mDragPoint,mFixedPoint);

        mFixationRadius = (int) (mFixationRadiusMax - distance / 14);
        if(mFixationRadius < mFixationRadiusMin)
            return null;
       /* float scale = (float) ((mMaxDistance-distance)/mMaxDistance);

        if(scale == 0.0f)
            return null;
        mFixationRadius = (int) (mFixationRadiusMax*scale);*/


        Path bezerlPath = new Path();
        float dx = mDragPoint.x - mFixedPoint.x;
        float dy = mDragPoint.y - mFixedPoint.y;

        float tanA = dy/dx;
        double A = Math.atan(tanA);

        float p1x = (float) (mFixedPoint.x + Math.sin(A)*mFixationRadius );
        float p1y = (float) (mFixedPoint.y - Math.cos(A)*mFixationRadius  );

        float p2x = (float) (mFixedPoint.x -  Math.sin(A)*mFixationRadius);
        float p2y = (float) (mFixedPoint.y + Math.cos(A)*mFixationRadius);

        float p3x = (float) (mDragPoint.x + Math.sin(A)*mDefaultRadius);
        float p3y = (float) (mDragPoint.y - Math.cos(A)*mDefaultRadius);

        float p4x = (float) (mDragPoint.x - Math.sin(A)*mDefaultRadius);
        float p4y = (float) (mDragPoint.y + Math.cos(A)*mDefaultRadius);


        /*float p1x = (float) (dx/distance*mFixationRadius + mFixedPoint.x);
        float p1y = (float) (mFixedPoint.y - dy/distance*mFixationRadius  );

        float p2x = (float) (mFixedPoint.x -  dx/distance*mFixationRadius);
        float p2y = (float) (mFixedPoint.y + dy/distance*mFixationRadius);

        float p3x = (float) (dx/distance*mDefaultRadius + mDragPoint.x);
        float p3y = (float) (mDragPoint.y - dy/distance*mDefaultRadius);

        float p4x = (float) (mDragPoint.x - dx/distance*mDefaultRadius);
        float p4y = (float) (mDragPoint.y + dy/distance*mDefaultRadius);

        float controlX = (mDragPoint.x+mFixedPoint.x)/2;
        float controlY = (mDragPoint.y+mFixedPoint.y)/2;*/
        float controlX = (mDragPoint.x - mFixedPoint.x)*mGoldPoint + mFixedPoint.x;
        float controlY = (mDragPoint.y - mFixedPoint.y)*mGoldPoint + mFixedPoint.y;

        bezerlPath.moveTo(p1x,p1y);
        bezerlPath.quadTo(controlX,controlY,p3x,p3y);
        bezerlPath.lineTo(p4x,p4y);
        bezerlPath.quadTo(controlX,controlY,p2x,p2y);
        bezerlPath.close();

        return bezerlPath;
    }

    /**
     * 绑定可以拖拽的控件
     * @param view
     * @param listner
     */
    public static void attach(View view, BubbleDisapperaListner listner) {
        if(view == null)
            throw new RuntimeException("The drag View is null,Please set current View to drag it!");
        view.setOnTouchListener(new BubbleMessageTouchListner(view,view.getContext()));
    }

    public void setDragBitmap(Bitmap bitmap) {
        mDragBitmap = bitmap;
    }


    public interface BubbleDisapperaListner{
        void disappear(View view);
    }
}
