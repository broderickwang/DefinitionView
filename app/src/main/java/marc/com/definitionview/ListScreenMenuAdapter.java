package marc.com.definitionview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import marc.com.customview.CView.ScreenViewBaseAdapter;

/**
 * Created by 王成达 on 2017/7/23.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class ListScreenMenuAdapter extends ScreenViewBaseAdapter {

    private Context mContext;

    public ListScreenMenuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    private String[] mItems = {"类型","品牌","价格","更多"};

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public View getTabView(int position, ViewGroup parent) {
        TextView v = (TextView) LayoutInflater.from(mContext).inflate(R.layout.screen_menu,parent,false);
        v.setText(mItems[position]);
        return v;
    }

    @Override
    public View getMenuView(int position, ViewGroup parent) {
        TextView v = (TextView) LayoutInflater.from(mContext).inflate(R.layout.screen_menu,parent,false);
        v.setText(mItems[position]);
        return v;
    }
}