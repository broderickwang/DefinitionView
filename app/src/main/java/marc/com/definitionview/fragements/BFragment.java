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
 * Time: 14:24
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class BFragment extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pieviewlayout,null);
	}

	public static BFragment newInstance() {
		
		Bundle args = new Bundle();
		
		BFragment fragment = new BFragment();
		fragment.setArguments(args);
		return fragment;
	}
}
