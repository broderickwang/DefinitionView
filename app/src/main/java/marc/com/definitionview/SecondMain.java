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

        Button b3 = (Button)findViewById(R.id.qczj);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondMain.this,QichezhijiaActivity.class));
            }
        });

        Button pie_view = (Button)findViewById(R.id.pie_view);
        pie_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondMain.this,HenCode1Activity.class));
            }
        });
        Button load_view = (Button)findViewById(R.id.loading_view);
        load_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondMain.this,LoadingActivity.class));
            }
        });
        Button bubble_view = (Button)findViewById(R.id.bubble_view);
        bubble_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondMain.this,BubbleActivity.class));
            }
        });
    }
}
