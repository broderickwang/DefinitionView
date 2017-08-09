package marc.com.definitionview.fragements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import marc.com.customview.Bean.PieData;
import marc.com.customview.CView.PieView;
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
		View v = inflater.inflate(R.layout.pieviewlayout,container,false);
		PieView pieView = (PieView) v.findViewById(R.id.pie);
		List<PieData> pies = new ArrayList<>();
		for (int i=0;i<5;i++){
			pies.add(new PieData("test"+i,3+i));
		}
		pieView.setData(pies);
		return v;
	}

	public static BFragment newInstance() {
		
		Bundle args = new Bundle();
		
		BFragment fragment = new BFragment();
		fragment.setArguments(args);
		return fragment;
	}
}
