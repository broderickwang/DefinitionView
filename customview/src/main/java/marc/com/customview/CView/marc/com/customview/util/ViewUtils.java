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
    public static int sp2dx(int sp,DisplayMetrics metrics){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,metrics);
    }
}
