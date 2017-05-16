package marc.com.definitionview;

import android.animation.TypeEvaluator;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/5/16
 * Time: 13:56
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class PointEvaluator implements TypeEvaluator {
	@Override
	public Object evaluate(float fraction, Object startValue, Object endValue) {
		Point start = (Point)startValue;
		Point end = (Point)endValue;

		float x = start.getX() + fraction*(end.getX() - start.getX());
		float y = start.getY() + fraction*(end.getY() - start.getY());

		Point p = new Point(x,y);
		return p;
	}
}
