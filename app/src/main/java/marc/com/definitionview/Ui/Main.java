package marc.com.definitionview.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import marc.com.definitionview.R;

public class Main extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		Button vp = (Button) findViewById(R.id.vp);
		Button step = (Button) findViewById(R.id.step);
		Button loading = (Button) findViewById(R.id.loading);
		Button flow = (Button) findViewById(R.id.flow);
		Button tagflow = (Button) findViewById(R.id.tagflow);
		Button tc = (Button) findViewById(R.id.tongcheng);
		Button progressbar = (Button) findViewById(R.id.progressbar);
		Button henCode1 = (Button) findViewById(R.id.hencode1);
		Button behavior1 = (Button) findViewById(R.id.behavior1);
		Button rating = (Button) findViewById(R.id.rating);
		Button slideLetter = (Button)findViewById(R.id.slideletter);

		flow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,FlowActivity.class));
			}
		});

		vp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,ViewpagetActivity.class));
			}
		});
		step.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,StepViewActivity.class));
			}
		});
		loading.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,MainActivity.class));
			}
		});
		tagflow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,TagFlowActivity.class));
			}
		});
		progressbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,ProgressBarActivity.class));
			}
		});
		tc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,TongchengActivity.class));
			}
		});
		henCode1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,HenCode1Activity.class));
			}
		});
		behavior1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,CoordinatorActivity.class));
			}
		});
		rating.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,Main4Activity.class));
			}
		});
		slideLetter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main.this,Main5Activity.class));
			}
		});
	}
}
