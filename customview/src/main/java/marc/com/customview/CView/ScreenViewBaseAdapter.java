package marc.com.customview.CView;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王成达 on 2017/7/23.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public abstract class ScreenViewBaseAdapter {
    public abstract int getCount();

    public abstract View getTabView(int position, ViewGroup parent);

    public abstract View getMenuView(int position,ViewGroup parent);
}
