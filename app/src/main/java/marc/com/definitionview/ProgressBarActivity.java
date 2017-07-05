package marc.com.definitionview;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import marc.com.customview.CView.ProgressBar;

public class ProgressBarActivity extends AppCompatActivity {
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_bar);
		progressBar = (ProgressBar)findViewById(R.id.progress_bar);

		ValueAnimator animator = ValueAnimator.ofFloat(0.0f,1.0f);
		animator.setDuration(5000);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				progressBar.setProgress((float)animation.getAnimatedValue());
			}
		});

		animator.start();
	}
}
