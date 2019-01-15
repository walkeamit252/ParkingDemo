package app.com.parkingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnScan,btnAllocate,btnClear;
    ParkingViewAdapter parkingViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScan=findViewById(R.id.btn_scan);
        btnAllocate=findViewById(R.id.btn_allocate);
        btnClear=findViewById(R.id.btn_clear);
        recyclerView=findViewById(R.id.recycler_view);

      //  GridLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        parkingViewAdapter = new ParkingViewAdapter(new ParkingViewAdapter.OnParkingSoltClicked() {
            @Override
            public void onPrkingItemClicked(int position) {
                Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(parkingViewAdapter);
    }
}
