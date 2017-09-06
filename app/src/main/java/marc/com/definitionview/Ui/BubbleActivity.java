package marc.com.definitionview.Ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import marc.com.customview.CView.BubbleView;
import marc.com.definitionview.R;

public class BubbleActivity extends AppCompatActivity implements BubbleView.BubbleDisapperaListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);
        BubbleView.attach(findViewById(R.id.buble_text),this);
    }

    @Override
    public void disappear(View view) {

    }
}
