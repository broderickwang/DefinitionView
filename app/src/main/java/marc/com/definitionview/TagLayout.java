package marc.com.definitionview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import marc.com.definitionview.Adapters.BaseAdapter;

/**
 * Created by 王成达 on 2017/7/22.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class TagLayout extends ViewGroup {

    private BaseAdapter mAdapter;

    private List<List<View>> mChildViews;

    public TagLayout(Context context) {
        this(context,null);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mChildViews = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mChildViews.clear();
        int childCount = getChildCount();
        //获取父布局的宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int height = getPaddingTop() + getPaddingBottom()/* + getChildAt(0).getMeasuredHeight()*/;

        ArrayList<View> childViews = new ArrayList<>();
        //一行的宽度
        int lineWidth = getPaddingLeft();

        int maxHeight = 0;
        for (int i=0;i<childCount;i++){
            //for循环测量子View
            View child = getChildAt(i);
            //这之后就能获取子View的宽高了，因为measureChlid会调用view的measure方法
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

            // TODO: 2017/7/22 高度不一致的情况

            int maxHeightLine = 0;

            //根据子View计算和指定自己的宽高
            //什么时候要换行,当子view的宽度 大于父布局的宽度
            // 计算子View的margin 和子View高度不一样的情况
            MarginLayoutParams p = (MarginLayoutParams) child.getLayoutParams();
            if(lineWidth+(child.getMeasuredWidth()+ p.leftMargin + p.rightMargin ) > width ){
                Log.d("TAG2", "onMeasure: height2 = "+height);
                //换行 高度累加
                height += maxHeight ;
                lineWidth = child.getMeasuredWidth()+ p.leftMargin + p.rightMargin;
                mChildViews.add(childViews);
                childViews = new ArrayList<>();
                childViews.add(child);
            }else{
                Log.d("TAG2", "onMeasure: height1 = "+height);
                //不换行
                lineWidth += child.getMeasuredWidth()+ p.leftMargin + p.rightMargin;
                childViews.add(child);
                Log.d("TAG", "onMeasure: childHeight = "+child.getMeasuredHeight());
                maxHeight = Math.max(maxHeight,child.getMeasuredHeight()+ p.topMargin + p.bottomMargin);
            }
        }
        height += maxHeight;
        mChildViews.add(childViews);
        //设置ViewGroup的宽高
        Log.d("TAG2", "onMeasure: height3 = "+height);
        Log.d("TAG", "onMeasure: width->"+width +" height->"+height);
        setMeasuredDimension(width,height);
    }

    /**
     * 摆放子View
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //摆放所有的子View
        int left,top = 0,right,bottom;
        Log.d("TAG", "onLayout: size="+mChildViews.size());
        int maxHeight = 0;
        for (List<View> mChildView : mChildViews) {
            left = getPaddingLeft() ;

            for (View view : mChildView) {
                MarginLayoutParams p = (MarginLayoutParams) view.getLayoutParams();
                left += p.leftMargin;
                int childTop = top + p.topMargin;
                right = left+view.getMeasuredWidth();
                bottom = childTop + view.getMeasuredHeight();
                //摆放
                Log.d("TAG", "onLayout:lett->"+left+" top->"+childTop+" right->"+right+" bottom->"+bottom);
                view.layout(left,childTop,right,bottom);
                //left后移
                left += view.getMeasuredWidth() + p.rightMargin;
                maxHeight = Math.max(maxHeight,view.getMeasuredHeight() + p.topMargin + p.bottomMargin);
            }
            //不断的叠加top值
            top += maxHeight;
        }
    }
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    public void setAdapter(BaseAdapter adapter){
        if(adapter == null){
            throw  new NullPointerException("Adapter is not allowed null!");
        }
        this.mAdapter = null;
        this.mAdapter = adapter;

        //清空所有的子View
        removeAllViews();

        //获取数量
        int childCount = mAdapter.getCount();
        for(int i=0;i<childCount;i++){
            //通过位置获取View
            View child = mAdapter.getView(i,this);
            addView(child);
        }
    }
}
