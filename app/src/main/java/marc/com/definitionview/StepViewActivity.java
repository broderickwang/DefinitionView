package marc.com.definitionview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import marc.com.customview.CView.StepView;

public class StepViewActivity extends AppCompatActivity {
	private StepView stepView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_step_view);

		stepView =(StepView)findViewById(R.id.step_view);
		stepView.setStepMax(10000);

		ValueAnimator animator = ObjectAnimator.ofFloat(0,8000);
		animator.setDuration(3000);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float currentStep = (float) animation.getAnimatedValue();
				stepView.setCurrentStep((int)currentStep);
			}
		});
		animator.start();
	}
}
