package marc.com.definitionview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import marc.com.definitionview.Adapters.BaseAdapter;

public class TagActivity extends AppCompatActivity {

    private List<String> mItems;

    private TagLayout mTagLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        mTagLayout = (TagLayout) findViewById(R.id.my_tag);
        mItems = new ArrayList<>();
        for (int i=0;i<10;i++){
            mItems.add("adapter item "+ i +" tip");
        }

        mTagLayout.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                TextView v = (TextView) LayoutInflater.from(TagActivity.this).inflate(R.layout.tag_item,parent,false);
                v.setText(mItems.get(position));
                return v;
            }
        });
    }
}
