package marc.com.customview.Bean;

/**
 * Created by 王成达
 * Date: 2017/8/9
 * Time: 10:02
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class PieData {
	private String name;
	private float value;
	private float percentage;

	private int color;
	private float angle = 0;

	public PieData(String name, float value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
}
