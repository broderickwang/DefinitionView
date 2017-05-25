package marc.com.definitionview;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import marc.com.customview.CView.CircleIndicatorView;

public class ViewpagetActivity extends AppCompatActivity {

	private List<View> views;

	ViewPager viewPager;

	CircleIndicatorView indicatorView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpaget);
		viewPager = (ViewPager)findViewById(R.id.myviewpager);
		indicatorView = (CircleIndicatorView)findViewById(R.id.indicator);

		views = new ArrayList<>();

		views.add(LayoutInflater.from(this).inflate(R.layout.a,null));
		views.add(LayoutInflater.from(this).inflate(R.layout.b,null));
		views.add(LayoutInflater.from(this).inflate(R.layout.c,null));

		viewPager.setAdapter(new MAdapter());

		indicatorView.setViewPager(viewPager);
	}

	class MAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position));
			return views.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}
	}
}
