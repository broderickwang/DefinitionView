package marc.com.customview.CView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import marc.com.customview.R;

//import android.support.v4.view.ViewCompat;ViewCompat

/**
 * Created by 王成达 on 2017/7/16.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:仿QQ6.0 侧滑
 */

public class QQSlideMenu extends HorizontalScrollView {
    private int mRightMargin = 62;

    private int mMenuWidth;

    private boolean mMenuIsOpen = false;

    private GestureDetector mGestureDetector;

    private boolean mIsIntercept ;

    View mMenuView;

    View mContentView;

    private View mShadowView;

    private GestureDetector.SimpleOnGestureListener mGestureDetectorListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("TAG", "onFling: velocityX->"+velocityX);
            if(mMenuIsOpen){
                if(velocityX < 0) {
                    closeMenu();
                    return true;
                }
            }else{
                if(velocityX > 0) {
                    openMenu();
                    return true;
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };

    public QQSlideMenu(Context context) {
        this(context,null);
    }
    public QQSlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQSlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //计算真个菜单的宽度
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlideMenu);
        mRightMargin = (int) array.getDimension(R.styleable.SlideMenu_SlideMenuRightMargin,
                dip2px(mRightMargin,context.getResources().getDisplayMetrics())  /*dip2px(context,50)*/);
        mMenuWidth = getScreenWidth(context) - mRightMargin;
        array.recycle();

        mGestureDetector = new GestureDetector(context,mGestureDetectorListener);
    }

    //宽度不对,z制定宽高


    @Override
    protected void onFinishInflate() {
        //1 . 布局解析完毕XML，调用的方法，也就是布局文件XML解析完毕,最早执行
        super.onFinishInflate();
        //内容页宽度等于屏幕的宽度
        //获取linearlayout
        ViewGroup container = (ViewGroup) getChildAt(0);
        if(container.getChildCount() != 2)
            throw new RuntimeException("LinearLayout is required two child view!");
        //菜单
        mMenuView = container.getChildAt(0);
        ViewGroup.LayoutParams layoutParams = mMenuView.getLayoutParams();
        layoutParams.width = mMenuWidth;
        //7.0以上的手机，必须再设置进去
        mMenuView.setLayoutParams(layoutParams);

        //把内容布局套一层阴影，然后再放回远处
        //菜单页宽度等于屏幕的宽度减去右边的一部分距离(自定义属性来制定获取的)
        mContentView = container.getChildAt(1);
        ViewGroup.LayoutParams contentParamers = mContentView.getLayoutParams();

        container.removeView(mContentView);

        RelativeLayout contentContainer = new RelativeLayout(getContext());
        contentContainer.addView(mContentView);

        mShadowView = new View(getContext());
        mShadowView.setBackgroundColor(Color.parseColor("#552b2b2b"));
        mShadowView.setAlpha(0.0f);
        contentContainer.addView(mShadowView);

        contentParamers.width = getScreenWidth(getContext());

        contentContainer.setLayoutParams(contentParamers);

        container.addView(contentContainer);

        //默认菜单是关闭的
//        scrollTo(mMenuWidth,0); // 没用
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //2. 进来菜单默认是关闭的
        scrollTo(mMenuWidth,0);
    }

    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mIsIntercept = false;
        Log.d("TAG", "onInterceptHoverEvent2: "+ev.getAction());
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mIsIntercept = false;
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG", "onInterceptTouchEvent: Marc ");
                if(mMenuIsOpen){
                    float currentX = ev.getX();
                    if(currentX > mMenuWidth){
                        closeMenu();
                        mIsIntercept = true;
                        return true; // 返回TRUE是拦截子view的事件，但是会调用自己的onTouch事件
                    }
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }*/

   /*
   * 打开菜单之后，还是可以回滑
   * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mIsIntercept = false;
        if(mMenuIsOpen && ev.getAction()==MotionEvent.ACTION_UP ){
            float currentX = ev.getX();
            if(currentX > mMenuWidth){
                closeMenu();
                mIsIntercept = true;
                return true; // 返回TRUE是拦截子view的事件，但是会调用自己的onTouch事件
            }

        }
        Log.d("TAG", "dispatchTouchEvent: "+super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    //手指抬起，menu的关闭和打开
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("TAG", "dispatchTouchEvent: "+ev.getAction()+"-"+mIsIntercept);
        if(mIsIntercept) {
            return true;
        }
        //快速滑动触发了，下面不要执行
        if(mGestureDetector.onTouchEvent(ev))
            return true;
        if (ev.getAction() == MotionEvent.ACTION_UP){
            //只处理手指的抬起
            //3. 根据当前滚动的距离来判断
            int currentSrollX = getScrollX();
            if(currentSrollX > mMenuWidth/2){
                // 关闭
                closeMenu();
            }else{
                // 打开菜单
                openMenu();
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    //4. 处理右边的缩放，左边的缩放和透明度
    //不断的获取当前滚动的位置


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d("Tag", "onScrollChanged: l->"+l +"menuWidth->"+mMenuWidth);//变化是从mMenuWidth到0
        //算一个梯度值
        float scale = 1f*l/mMenuWidth; //scale从1变化到0
        //右边缩放，最小缩放0.7 最大1f
//        float rightScale = 0.7f + 0.3f*scale;
        //滚动的过程控制阴影的透明度
        ViewCompat.setAlpha(mShadowView,(1-scale));
        //设置右边的缩放 默认是以中心点缩放
        //缩放的中心点，要设置到左边线的中心点
        /*ViewCompat.setPivotX(mContentView,0);
        ViewCompat.setPivotY(mContentView,mContentView.getMeasuredHeight()/2);
        //设置缩放
        ViewCompat.setScaleX(mContentView,rightScale);
        ViewCompat.setScaleY(mContentView,rightScale);*/

        //设置菜单的缩放和透明度
        //透明是 半透明到完全透明 0.5f-1.0f
        float alpha = 0.5f + (1-scale)*0.5f;//alpha0-1
        ViewCompat.setAlpha(mMenuView,alpha);
        //缩放0.6f-1.0f
        float leftScale = 0.6f + 0.4f * (1-scale);
//        ViewCompat.setPivotX(mMenuView,mMenuView.getMeasuredWidth());
//        ViewCompat.setPivotY(mMenuView,mMenuView.getMeasuredHeight()/2);
        /*ViewCompat.setScaleX(mMenuView,leftScale);
        ViewCompat.setScaleY(mMenuView,leftScale);*/
        //退出按钮，刚出现的时候是在右边，目前是永远在左边
        //设置平移 1*0.7f
        ViewCompat.setTranslationX(mMenuView,0.7f*l);
        //下面这一句就是实现抽屉效果
//        ViewCompat.setTranslationX(mMenuView,l);
    }

    /**
     * 打开菜单
     */
    private void openMenu() {
        //smoothScrollTo和ScrollTo相比是有动画的
        smoothScrollTo(0,0);
        mMenuIsOpen = true;
    }

    /**
     * 关闭菜单
     */
    private void closeMenu() {
        smoothScrollTo(mMenuWidth,0);
        mMenuIsOpen = false;
    }

    private int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public float dip2px(int dip,DisplayMetrics metrics){
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,metrics);
    }
}
