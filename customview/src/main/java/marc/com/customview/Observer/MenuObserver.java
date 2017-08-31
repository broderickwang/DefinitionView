package marc.com.customview.Observer;

import marc.com.customview.Adapter.ScreenViewBaseAdapter;
import marc.com.customview.CView.ListScreenView;

/**
 * Created by 王成达
 * Date: 2017/8/31
 * Time: 13:44
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MenuObserver extends AbMenuObserver {
	private ListScreenView mView;

	public MenuObserver(ListScreenView view) {
		this.mView = view;
	}

	@Override
	public void closeMenu() {
		mView.closeMenu();
	}
}
