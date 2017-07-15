package marc.com.customview.CView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

import marc.com.customview.CView.marc.com.customview.util.ViewUtils;
import marc.com.customview.R;

/**
 * Created by 王成达 on 2017/7/15.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class SlideMenu extends HorizontalScrollView {

    private int mRightMargin = 62;

    private int mMenuWidth;

    View mMenuView;

    View mContentView;

    public SlideMenu(Context context) {
        this(context,null);
    }
    public SlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //计算真个菜单的宽度
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlideMenu);
        mRightMargin = (int) array.getDimension(R.styleable.SlideMenu_SlideMenuRightMargin,
              ViewUtils.dip2px(mRightMargin,context.getResources().getDisplayMetrics())  /*dip2px(context,50)*/);
        mMenuWidth = getScreenWidth(context) - mRightMargin;
        array.recycle();
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
        //菜单页宽度等于屏幕的宽度减去右边的一部分距离(自定义属性来制定获取的)
        mContentView = container.getChildAt(1);
        ViewGroup.LayoutParams contentParamers = mContentView.getLayoutParams();
        contentParamers.width = getScreenWidth(getContext());
        mContentView.setLayoutParams(contentParamers);
        mContentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                smoothScrollTo(mMenuWidth,0);
            }
        });
        //默认菜单是关闭的
//        scrollTo(mMenuWidth,0); // 没用
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //2. 进来菜单默认是关闭的
        scrollTo(mMenuWidth,0);
    }

    //手指抬起，menu的关闭和打开


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
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
        float rightScale = 0.7f + 0.3f*scale;
        //设置右边的缩放 默认是以中心点缩放
        //缩放的中心点，要设置到左边线的中心点
        ViewCompat.setPivotX(mContentView,0);
        ViewCompat.setPivotY(mContentView,mContentView.getMeasuredHeight()/2);
        //设置缩放
        ViewCompat.setScaleX(mContentView,rightScale);
        ViewCompat.setScaleY(mContentView,rightScale);

        //设置菜单的缩放和透明度
        //透明是 半透明到完全透明 0.5f-1.0f
        float alpha = 0.5f + (1-scale)*0.5f;//alpha0-1
        ViewCompat.setAlpha(mMenuView,alpha);
        //缩放0.6f-1.0f
        float leftScale = 0.6f + 0.4f * (1-scale);
        ViewCompat.setPivotX(mMenuView,mMenuView.getMeasuredWidth());
        ViewCompat.setPivotY(mMenuView,mMenuView.getMeasuredHeight()/2);
        ViewCompat.setScaleX(mMenuView,leftScale);
        ViewCompat.setScaleY(mMenuView,leftScale);
        //退出按钮，刚出现的时候是在右边，目前是永远在左边
        //设置平移 1*0.7f
        ViewCompat.setTranslationX(mMenuView,0.05f*l);
        //下面这一句就是实现抽屉效果
//        ViewCompat.setTranslationX(mMenuView,l);
    }

    /**
     * 打开菜单
     */
    private void openMenu() {
        //smoothScrollTo和ScrollTo相比是有动画的
        smoothScrollTo(0,0);
    }

    /**
     * 关闭菜单
     */
    private void closeMenu() {
        smoothScrollTo(mMenuWidth,0);
    }

    private int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
