package marc.com.definitionview.Ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TableLayout;

import marc.com.definitionview.Adapters.HenCode1Adapter;
import marc.com.definitionview.R;

public class HenCode1Activity extends AppCompatActivity {
	TabLayout mTabLayout;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hen_code1);

		Window window = this.getWindow();
		window.setStatusBarColor(Color.parseColor("#abcdef"));

		mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
		mViewPager = (ViewPager)findViewById(R.id.viewpager);

		FragmentManager fragmentManager = getSupportFragmentManager();

		mViewPager.setAdapter(new HenCode1Adapter(fragmentManager));

		mTabLayout.setupWithViewPager(mViewPager);
	}
}
