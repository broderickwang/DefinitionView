package marc.com.definitionview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import marc.com.customview.CView.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView mytext;

	private final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mytext = (TextView)findViewById(R.id.mytext);

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
