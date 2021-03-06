package marc.com.definitionview.Ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import marc.com.customview.CView.ListScreenView;
import marc.com.definitionview.Adapters.ListScreenMenuAdapter;
import marc.com.definitionview.R;

public class ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        ListScreenView  view = (ListScreenView)findViewById(R.id.screen);
        view.setAdapter(new ListScreenMenuAdapter(this));
    }
}
