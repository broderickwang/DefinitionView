package marc.com.customview.CView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/15
 * Time: 14:17
 * Version: 1.0
 * Description:解决和ScrollView嵌套显示不全的问题
 * Email:wangchengda1990@gmail.com
 **/
public class MyListView extends ListView {
	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//右移两位，给一个30位的值。 AT_MOST是个两位的模式值。组合成一个32位的值。包含模式和值
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
		//heightMeasureSpec 32位的值
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
