package app.com.parkingdemo.parking;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.parkingdemo.R;

public class ParkingViewAdapter extends RecyclerView.Adapter<ParkingViewAdapter.SimpleViewHolder> {
    OnParkingSoltClicked onParkingSoltClicked;
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


public ParkingViewAdapter(OnParkingSoltClicked onParkingSoltClicked){
    this.onParkingSoltClicked=onParkingSoltClicked;
}


    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_parking_view, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder rawHolder, final int position) {
        final SimpleViewHolder holder = rawHolder;
        holder.txtViewParkingNumber.setText(position+1+"");
        holder.imgView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                onParkingSoltClicked.onPrkingItemClicked(holder.txtViewParkingNumber.getText().toString());
            }
        });
    }
    @Override
    public int getItemCount() {
        return 50;
    }


    public interface OnParkingSoltClicked{
     void onPrkingItemClicked(String position);
    }
}