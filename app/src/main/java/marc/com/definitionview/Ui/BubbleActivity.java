package marc.com.definitionview.Ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import marc.com.customview.CView.BubbleView;
import marc.com.definitionview.R;

public class BubbleActivity extends AppCompatActivity implements BubbleView.BubbleDisappearListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);
        BubbleView.attach(findViewById(R.id.buble_text),this);
    }

    @Override
    public void disappear(View view) {
        Toast.makeText(this, view.getId()+" 消失了！", Toast.LENGTH_SHORT).show();
    }
}
