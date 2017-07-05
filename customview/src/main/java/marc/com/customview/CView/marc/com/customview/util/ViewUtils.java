package marc.com.customview.CView.marc.com.customview.util;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by hannahxian on 2017/5/24.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class ViewUtils {
    public static int sp2dx(float sp,DisplayMetrics metrics){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,metrics);
    }

    public static float dip2px(int dip,DisplayMetrics metrics){
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,metrics);
    }
}
