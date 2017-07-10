package marc.com.definitionview.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import marc.com.definitionview.fragements.AFragement;
import marc.com.definitionview.fragements.BFragment;
import marc.com.definitionview.fragements.HenCode1Fragment;

/**
 * Created by 王成达
 * Date: 2017/7/10
 * Time: 14:18
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class HenCode1Adapter extends FragmentPagerAdapter {

	private String[] titles = new String[]{"BackgroundView", "PieView", "Tab3"};


	public HenCode1Adapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		Fragment f ;
		switch (position){
			case 0:
				f = AFragement.newInstance();
				break;
			case 1:
				f = BFragment.newInstance();
				break;
			case 2:
				f = HenCode1Fragment.newInstance();
				break;
			default:
				f = null;
				break;
		}
		return f;

	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	@Override
	public int getCount() {
		return 3;
	}
}
