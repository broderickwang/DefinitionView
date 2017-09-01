package marc.com.definitionview.Ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import marc.com.customview.CView.FlowLayout;
import marc.com.customview.CView.TagAdapter;
import marc.com.customview.CView.TagFlowLayout;
import marc.com.definitionview.R;

public class TagFlowActivity extends AppCompatActivity {

	private String[] mVals = new String[]
			{"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
					"Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
					"Android", "Weclome Hello", "Button Text", "TextView"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_flow);
		final TagFlowLayout tagflow = (TagFlowLayout) findViewById(R.id.tag_flow);
		tagflow.setAdapter(new TagAdapter<String>(mVals)
		{
			@Override
			public View getView(FlowLayout parent, int position, String s)
			{
				TextView tv = (TextView) LayoutInflater.from(TagFlowActivity.this)
						.inflate(R.layout.tv, tagflow, false);
				tv.setText(s);
				return tv;
			}
		});
	}
}
