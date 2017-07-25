package marc.com.definitionview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);

        Button b1 = (Button)findViewById(R.id.kgch);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondMain.this,KugouCHActivity.class));
            }
        });

        /*Button b2 = (Button) findViewById(R.id.tag);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondMain.this,TagActivity.class));
            }
        });*/
        Button b21 = (Button) findViewById(R.id.screen);
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondMain.this,ScreenActivity.class));
            }
        });
    }
}
