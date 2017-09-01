package marc.com.definitionview.Ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import marc.com.customview.CView.ShapeView;
import marc.com.definitionview.R;

public class TongchengActivity extends Activity {
	ShapeView loadingView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tongcheng);
		loadingView = (ShapeView)findViewById(R.id.myld);

		/*ValueAnimator animator = ObjectAnimator.ofFloat(0,4000);
		animator.setDuration(4000*1000);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {

			}
		});

		animator.start();*/
	}

	public void exchange(View v){
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							loadingView.change();
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
