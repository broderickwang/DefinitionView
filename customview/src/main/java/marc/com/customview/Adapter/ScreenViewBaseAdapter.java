package marc.com.customview.Adapter;

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

    //获取当前的菜单内容
    public abstract View getMenuView(int position,ViewGroup parent);


    public abstract void menuOpen(View tabView);

    public abstract void menuClose(View tabView);
}
