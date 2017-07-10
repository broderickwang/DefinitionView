package marc.com.definitionview.fragements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import marc.com.definitionview.R;

/**
 * Created by 王成达
 * User: Broderick
 * Date: 2017/7/10
 * Time: 14:16
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class AFragement extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.backgroundlayout,null);
	}

	public static AFragement newInstance() {
		
		Bundle args = new Bundle();
		
		AFragement fragment = new AFragement();
		fragment.setArguments(args);
		return fragment;
	}
}
