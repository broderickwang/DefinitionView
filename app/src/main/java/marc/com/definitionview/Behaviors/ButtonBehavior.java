package marc.com.definitionview.Behaviors;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import marc.com.definitionview.TempView;

/**
 * Created by 王成达
 * Date: 2017/7/11
 * Time: 12:30
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class ButtonBehavior extends CoordinatorLayout.Behavior<Button> {

	private int mWidth;

	public ButtonBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		mWidth = metrics.widthPixels;
	}

	/**
	 * 判断child的布局是否依赖dependency
	 */
	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
		return dependency instanceof TempView;
	}

	/**
	 * 当dependency发生改变时（位置、宽高等），执行这个函数
	 * 返回true表示child的位置或者是宽高要发生改变，否则就返回false
	 */
	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {

		int top = dependency.getTop();
		int left = dependency.getLeft();

		int x = mWidth - left - child.getWidth();
		int y = top;
		setPosition(child,x,y);
		return true;
	}

	private void setPosition(View v,int x,int y){
		CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) v.getLayoutParams();
		params.leftMargin = x;
		params.topMargin = y;
		v.setLayoutParams(params);
	}
}
