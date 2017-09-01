package marc.com.customview.CView;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/**
 * Created by 王成达 on 2017/9/1.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class MessageBubbleView extends View {

    public MessageBubbleView(Context context) {
        super(context);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                initDefaultPoint();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void initDefaultPoint() {

    }
}
