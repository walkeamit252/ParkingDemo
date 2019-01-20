package app.com.parkingdemo.parking;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.parkingdemo.R;
import app.com.parkingdemo.database.ParkingTable;

public class ParkingViewAdapter extends RecyclerView.Adapter<ParkingViewAdapter.SimpleViewHolder> {
    OnParkingSoltClicked onParkingSoltClicked;

    ArrayList<ParkingTable> parkingTables;


static class SimpleViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnItemTouchListener {
 
    private ImageView imgView;
    private TextView txtViewParkingNumber;

    SimpleViewHolder(View itemView) {
        super(itemView);
        imgView = itemView.findViewById(R.id.imageView);
        txtViewParkingNumber=itemView.findViewById(R.id.txt_view_parking_number);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}


public ParkingViewAdapter(ArrayList<ParkingTable> parkingTables,OnParkingSoltClicked onParkingSoltClicked){
    this.onParkingSoltClicked=onParkingSoltClicked;
    this.parkingTables=parkingTables;
}


    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_parking_view, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder rawHolder, final int position) {
        final SimpleViewHolder holder = rawHolder;
        holder.txtViewParkingNumber.setText(position+1+"");

        if(parkingTables.get(position).getParkingStatus().equals("yes")){
            holder.imgView.setSelected(true);
        }else {
            holder.imgView.setSelected(false);
        }

        holder.imgView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                onParkingSoltClicked.onPrkingItemClicked(parkingTables.get(position));
            }
        });
    }
    @Override
    public int getItemCount() {
        return parkingTables.size();
    }


    public interface OnParkingSoltClicked{
     void onPrkingItemClicked(ParkingTable parkingTable);
    }
}