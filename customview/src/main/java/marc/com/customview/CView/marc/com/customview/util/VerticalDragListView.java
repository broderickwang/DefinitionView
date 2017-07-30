package marc.com.customview.CView.marc.com.customview.util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;

/**
 * Created by 王成达 on 2017/7/30.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class VerticalDragListView extends FrameLayout {

    //系统给写好的一个工具类
    private ViewDragHelper mDragHelper;
    private View mDragListView;
    private int mMenuHeight;
    private float mDownY;
    private boolean mMenuIsOpen = false;

    public VerticalDragListView(@NonNull Context context) {
        this(context,null);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mDragHelper = ViewDragHelper.create(this,mDragHelperCallback);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();
        if(childCount != 2){
            throw new RuntimeException("please set 2 child views!");
        }

        mDragListView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed)
            mMenuHeight = getChildAt(0).getMeasuredHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    //ListView可以滑动，但是菜单的事件滑动不了了,没效果了

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    // Ignoring pointerId=0 because ACTION_DOWN was not received for this pointer before ACTION_MOVE.
    // It likely happened because  ViewDragHelper did not receive all the events in the event stream.
    //
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //向下滑动的时候，进行拦截，不要给listview
        if(mMenuIsOpen){
            //菜单打开的时候，要全部拦截，来自己处理
            return true;
        }

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                mDragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
//                Log.d("TAG", "onInterceptTouchEvent: "+moveY+" - "+mDownY);
                if((moveY - mDownY) > 0 && !canChildScrollUp()){
                    //向下滑动的时候，拦截，自己来处理
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    //1.拖动子View

    private ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //指定 child子View是否可以拖得动
            //只能是ListView列表可以拖动
            return child == mDragListView ;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            //垂直拖动的位置
            //垂直拖动的位置只能是后面菜单的高度
            if(top <= 0) {
                top = 0;
                mMenuIsOpen = false;
            }
            if(top >= mMenuHeight) {
                top = mMenuHeight;
                mMenuIsOpen = true;
            }
            return top;
        }

        //手指松开的时候的回调

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int y = releasedChild.getTop();
            if(y > mMenuHeight/2){
                //滚动到菜单的高度，菜单打开
                mDragHelper.settleCapturedViewAt(0,mMenuHeight);
            }else{
                //滚动到0，菜单关闭
                mDragHelper.settleCapturedViewAt(0,0);
            }
            invalidate();
        }


        /*@Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //水平拖动的位置
            return left;
        }*/
    };

    @Override
    public void computeScroll() {
        if(mDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    //判断list是否滚动到了顶部
    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mDragListView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mDragListView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mDragListView, -1) || mDragListView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mDragListView, -1);
        }
    }

    public boolean canChildScrollDown(){
        if(Build.VERSION.SDK_INT < 14){
            if(mDragListView instanceof AbsListView){
                final  AbsListView absListView = (AbsListView)mDragListView;
                return absListView.getChildCount() > 0
                        && (absListView.getLastVisiblePosition()>0
                        || absListView.getChildAt(absListView.getAdapter().getCount()-1).getTop() < absListView.getPaddingTop());
            }else{
                return ViewCompat.canScrollVertically(mDragListView, 1) || mDragListView.getScrollY() > 0;
            }
        }else{
            return ViewCompat.canScrollVertically(mDragListView, 1) || mDragListView.getScrollY() > 0;
        }
    }
}
