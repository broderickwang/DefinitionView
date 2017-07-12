package marc.com.definitionview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import marc.com.customview.CView.LetterSlideBar;

public class Main5Activity extends AppCompatActivity {

    private TextView letterTv;
    private LetterSlideBar mLetterBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        letterTv = (TextView)findViewById(R.id.letter_tv);
        mLetterBar = (LetterSlideBar)findViewById(R.id.letterbar);

        mLetterBar.setTouchListner(new LetterSlideBar.OnTouchListner() {
            @Override
            public void TouchListner(String str,boolean isTouch) {
                if(!isTouch) {
                    letterTv.setVisibility(View.GONE);
                }
                else {
                    letterTv.setVisibility(View.VISIBLE);
                    letterTv.setText(str);
                }
            }
        });
    }
}
