package marc.com.definitionview.Ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import marc.com.definitionview.R;

public class QichezhijiaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qichezhijia);
        initData();

        recyclerView = (RecyclerView) findViewById(R.id.my_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new A());

    }

    private void initData(){
        for (int i=0;i<100;i++){
            datas.add("this is data -> "+i);
        }
    }

    class A extends RecyclerView.Adapter<A.AHolder> {

        @Override
        public AHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(QichezhijiaActivity.this).inflate(R.layout.tv,parent,false);
            AHolder holder = new AHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(AHolder holder, int position) {
            holder.textView.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class AHolder extends RecyclerView.ViewHolder{

            private TextView textView;

            public AHolder(View itemView) {
                super(itemView);
                textView = (TextView)itemView.findViewById(R.id.com_tv);
            }
        }
    }
}
