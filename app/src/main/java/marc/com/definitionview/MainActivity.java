package marc.com.definitionview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import marc.com.customview.CView.StepView;
import marc.com.customview.CView.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView mytext;

	private StepView stepView;

	private final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mytext = (TextView)findViewById(R.id.mytext);

		stepView = (StepView)findViewById(R.id.stepview);

//		stepView.setCurrentStep(1000);
		//属性动画
		ValueAnimator animator = ObjectAnimator.ofFloat(0,9000);
		animator.setDuration(2000);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float currentStep = (float)animation.getAnimatedValue();
				stepView.setCurrentStep((int)currentStep);
			}
		});
		animator.start();

		mytext.post(new Runnable() {
			@Override
			public void run() {
				Log.d(TAG, "run: "+mytext.getWidth()+"-"+mytext.getHeight());
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume: "+mytext.getWidth()+"-"+mytext.getHeight());
	}
}
