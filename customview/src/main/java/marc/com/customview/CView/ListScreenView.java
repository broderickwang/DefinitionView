package marc.com.customview.CView;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import marc.com.customview.R;

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
    private int mMenuContentHeight;
    //阴影颜色
    private String mShadowColor = "#2b2b2b";

    private FrameLayout mContentView;

    private ScreenViewBaseAdapter mAdapter;

    private boolean mIsMenuOpen = false;

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
        mShadowView.setBackgroundColor(Color.parseColor(mShadowColor));
        mShadowView.setAlpha(0f);
        mShadowView.setVisibility(GONE);
        mMenuMiddleView.addView(mShadowView);
        //3.创建菜单,存放菜单内容
        mContentView = new FrameLayout(mContext);
        mContentView.setBackgroundColor(Color.parseColor("#abcdef"));
        mMenuMiddleView.addView(mContentView);
        Log.d(TAG, "initLayout: width="+mMenuMiddleView.getMeasuredWidth()+" height="+mMenuMiddleView.getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        mMenuContentHeight = (int) (height*75/100f);
        ViewGroup.LayoutParams params = mContentView.getLayoutParams();
        params.height = mMenuContentHeight;
        mContentView.setLayoutParams(params);

        mContentView.setTranslationY(-mMenuContentHeight);
    }

    /**
     *设置Adapter
     * @param adapter
     */
    public void setAdapter(ScreenViewBaseAdapter adapter){
        if(adapter == null){

        }
        this.mAdapter = adapter;

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

    private void setTabClick(View tabView, final int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mIsMenuOpen){
                    //打开
                    mShadowView.setVisibility(VISIBLE);
                    mContentView.getChildAt(position).setVisibility(VISIBLE);
                    mContentView.setTranslationY(mMenuContentHeight);
                    mIsMenuOpen = true;
                }else{
                    //关闭
                    mShadowView.setVisibility(GONE);
                    mContentView.getChildAt(position).setVisibility(GONE);
                    mContentView.setTranslationY(-mMenuContentHeight);
                    mIsMenuOpen = false;
                }
                invalidate();
            }
        });
    }

    //刚进来的时候，内容和阴影都是不显示的
}
