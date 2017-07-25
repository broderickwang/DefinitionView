package marc.com.definitionview.Adapters;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王成达 on 2017/7/23.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public abstract class BaseAdapter {
    //1. 有多少个条目
    public abstract int getCount();

    public abstract View getView(int position, ViewGroup parent);

    //及时更新 观察者模式

//    public void unregister
}
