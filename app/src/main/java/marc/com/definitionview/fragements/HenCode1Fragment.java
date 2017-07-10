package marc.com.definitionview.fragements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import marc.com.definitionview.R;

/**
 * Created by 王成达
 * Date: 2017/7/10
 * Time: 14:26
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class HenCode1Fragment extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.c,null);
	}

	public static HenCode1Fragment newInstance() {
		
		Bundle args = new Bundle();
		
		HenCode1Fragment fragment = new HenCode1Fragment();
		fragment.setArguments(args);
		return fragment;
	}
}
