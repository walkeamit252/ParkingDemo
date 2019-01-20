package app.com.parkingdemo.parking;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;
import java.util.ArrayList;
import app.com.parkingdemo.R;
import app.com.parkingdemo.database.DBHelper;
import app.com.parkingdemo.database.ParkingTable;
import app.com.parkingdemo.utils.AppConstants;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    RecyclerView recyclerView;
    Button btnScan,btnAllocate,btnClear;
    ParkingViewAdapter parkingViewAdapter;
    DBHelper dbHelper;
    ArrayList<ParkingTable> parkingTables;

    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DBHelper(this);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        updateParkingStatus();

        btnScan=findViewById(R.id.btn_scan);
        btnAllocate=findViewById(R.id.btn_allocate);
        btnClear=findViewById(R.id.btn_clear);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        parkingViewAdapter = new ParkingViewAdapter(parkingTables,new ParkingViewAdapter.OnParkingSoltClicked() {
            @Override
            public void onPrkingItemClicked(ParkingTable parkingTable) {

                if(sharedPref.getString(AppConstants.LOGGEDIN_USER_ID,"").equals(parkingTable.getUserId()))
                {
                    Toast.makeText(MainActivity.this,"ALREADY BOOKED",Toast.LENGTH_SHORT).show();
                }else
                {
                    dbHelper.updateParkingStatus(parkingTable.getParkingId(),sharedPref.getString(AppConstants.LOGGEDIN_USER_ID,""));
                    updateParkingStatus();
                    parkingViewAdapter.notifyDataSetChanged();
                }


            }
        });
        recyclerView.setAdapter(parkingViewAdapter);


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ParkingTable> parkingTables=dbHelper.getParkingStatus();
                for(ParkingTable parkingTable:parkingTables){
                    Log.i(TAG, "onClick: "+parkingTable.getParkingId()+"=>"+parkingTable.getParkingStatus());
                }

            }
        });
    }


    public void updateParkingStatus(){
        if(parkingTables!=null) {
            parkingTables.clear();
        }else {
            parkingTables=new ArrayList<>();
        }
        parkingTables.addAll(dbHelper.getParkingStatus());
    }
}
