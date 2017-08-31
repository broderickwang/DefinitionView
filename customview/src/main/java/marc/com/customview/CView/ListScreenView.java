package marc.com.customview.CView;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.animation.ObjectAnimator;

import marc.com.customview.Adapter.ScreenViewBaseAdapter;
import marc.com.customview.Observer.AbMenuObserver;

/**
 * Created by 王成达 on 2017/7/23.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:仿汽车之家下来筛选控件
 */

public class ListScreenView extends LinearLayout {

    private String TAG = "ListScreenView";

    private LinearLayout mMenuTabView;
    private android.content.Context mContext;
    private FrameLayout mMenuMiddleView;

    private View mShadowView;
    private int mMenuContentHeight = 0;
    //阴影颜色
    private int mShadowColor = 0x88888888;

    private FrameLayout mContentView;

    private ScreenViewBaseAdapter mAdapter;

    private int mCurrentPosition = -1;

    private long DURATION_TIME = 350;

    private boolean mAnimatorExcute = false;

    private MenuObserver mObserver;

    public ListScreenView(Context context) {
        this(context,null);
    }

    public ListScreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ListScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initLayout();
    }

    private void initLayout() {
        setOrientation(VERTICAL);
        //1.创建头部用来存放Tab
        mMenuTabView = new LinearLayout(mContext);
        mMenuTabView.setOrientation(HORIZONTAL);
        mMenuTabView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mMenuTabView.setBackgroundColor(Color.RED);
        addView(mMenuTabView);
        Log.d(TAG, "initLayout: width="+mMenuTabView.getMeasuredWidth()+" height="+mMenuTabView.getMeasuredHeight());
        //2.创建framelayout用来存放阴影+布局
        mMenuMiddleView = new FrameLayout(mContext);
        mMenuMiddleView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mMenuMiddleView);

        //不设置layoutparams，默认match_parent
        mShadowView = new View(mContext);
        mShadowView.setBackgroundColor(mShadowColor);
        mShadowView.setAlpha(0f);
        mShadowView.setVisibility(GONE);
        mMenuMiddleView.addView(mShadowView);
        mShadowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentPosition != -1)
                    closeMenu();
            }
        });
        //3.创建菜单,存放菜单内容
        mContentView = new FrameLayout(mContext);
        mContentView.setBackgroundColor(Color.parseColor("#abcdef"));
        mMenuMiddleView.addView(mContentView);
        Log.d(TAG, "initLayout: width="+mMenuMiddleView.getMeasuredWidth()+" height="+mMenuMiddleView.getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");

        if(mMenuContentHeight == 0){
            int height = MeasureSpec.getSize(heightMeasureSpec);
            mMenuContentHeight = (int) (height*75/100f);
            ViewGroup.LayoutParams params = mContentView.getLayoutParams();
            params.height = mMenuContentHeight;
            mContentView.setLayoutParams(params);

            mContentView.setTranslationY(-mMenuContentHeight);
        }

    }

    /**
     *设置Adapter
     * @param adapter
     */
    public void setAdapter(ScreenViewBaseAdapter adapter){
        if(adapter == null){
            throw new RuntimeException("please set current adapter!");
        }

        if(adapter!=null && mObserver != null){
            adapter.registerDataSetObserver(mObserver);
        }

        this.mAdapter = adapter;
        mObserver = new MenuObserver();
        adapter.registerDataSetObserver(mObserver);

        int count = mAdapter.getCount();
        for(int i =0;i<count;i++){
            //获取Tab
            View tab = mAdapter.getTabView(i,mMenuTabView);
            LinearLayout.LayoutParams params = (LayoutParams) tab.getLayoutParams();
            params.weight = 1;
            tab.setLayoutParams(params);

            setTabClick(tab,i);

            mMenuTabView.addView(tab);
//            获取菜单
            View content = mAdapter.getMenuView(i,mContentView);
            content.setVisibility(GONE);
            mContentView.addView(content);
        }
        Log.d(TAG, "initLayout: width="+mMenuTabView.getMeasuredWidth()+" height="+mMenuTabView.getMeasuredHeight());
        Log.d(TAG, "initLayout: width="+mMenuMiddleView.getMeasuredWidth()+" height="+mMenuMiddleView.getMeasuredHeight());
        invalidate();
    }

    private void setTabClick(final View tabView, final int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentPosition == -1 || position!=mCurrentPosition){
                    //打开
                    openMenu(position,tabView);
                }else{
                    //关闭
                    closeMenu();
                }
//                invalidate();
            }
        });
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if(mAnimatorExcute)
            return;
        ObjectAnimator animator = (ObjectAnimator) ObjectAnimator.ofFloat(mContentView,"TranslationY",0,-mMenuContentHeight);
        animator.setDuration(DURATION_TIME);
        animator.start();

        ObjectAnimator a = ObjectAnimator.ofFloat(mShadowView,"alpha",1f,0f);
        a.setDuration(DURATION_TIME);
        mShadowView.setVisibility(GONE);
        a.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mAnimatorExcute = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimatorExcute = false;
                mContentView.getChildAt(mCurrentPosition).setVisibility(GONE);
                mAdapter.menuClose(mMenuTabView.getChildAt(mCurrentPosition));
                mCurrentPosition = -1;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        a.start();

    }

    /**
     * 打开菜单
     * @param position
     */
    public void openMenu(int position, final View tabView) {
        if(mAnimatorExcute)
            return;
        if(mCurrentPosition != position && mCurrentPosition!=-1){
            mAdapter.menuClose(mMenuTabView.getChildAt(mCurrentPosition));
            mContentView.getChildAt(position).setVisibility(VISIBLE);
            mAdapter.menuOpen(mMenuTabView.getChildAt(position));
            mContentView.getChildAt(mCurrentPosition).setVisibility(GONE);
        }else{
            ObjectAnimator animator = (ObjectAnimator) ObjectAnimator.ofFloat(mContentView,"TranslationY",-mMenuContentHeight,0);
            animator.setDuration(DURATION_TIME);
            mContentView.getChildAt(position).setVisibility(VISIBLE);
            animator.start();

            ObjectAnimator a = ObjectAnimator.ofFloat(mShadowView,"alpha",0f,1f);
            a.setDuration(DURATION_TIME);
            mShadowView.setVisibility(VISIBLE);
            a.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mAnimatorExcute = true;
                    mAdapter.menuOpen(tabView);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mAnimatorExcute = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            a.start();
        }


        mCurrentPosition = position;
    }

    //刚进来的时候，内容和阴影都是不显示的


    //////
    private class MenuObserver extends AbMenuObserver{

        @Override
        public void closeMenu() {
            ListScreenView.this.closeMenu();
        }
    }
}
