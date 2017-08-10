package marc.com.customview.CView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import marc.com.customview.R;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/16
 * Time: 11:15
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class LoadingView extends LinearLayout {

	private ShapeView mShapeView;


	public LoadingView(Context context) {
		this(context,null);
	}

	public LoadingView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initLayout();
	}

	private void initLayout() {
		inflate(getContext(), R.layout.ui_loading_layout,this);
//		mShapeView = (ShapeView) this.findViewById(R.id.shape_view);

		/*new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					try {
						Thread.sleep(1000);
						mShapeView.change();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();*/
		/*ObjectAnimator animator = (ObjectAnimator) ObjectAnimator.ofFloat(0,1000);
		animator.setDuration(1000*1000);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
//				mShapeView.change();
				Log.d("TAG", "onAnimationUpdate: ---->"+animation);
			}
		});
		animator.start();*/

//	   	ObjectAnimator animator = ObjectAnimator.ofInt()

	}
}
