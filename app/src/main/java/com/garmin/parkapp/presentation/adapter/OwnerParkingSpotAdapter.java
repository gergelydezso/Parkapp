package com.garmin.parkapp.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garmin.parkapp.R;
import com.garmin.parkapp.model.ParkingSpot;

import java.util.List;

/**
 * @author morari on 6/2/16.
 */
public class OwnerParkingSpotAdapter extends RecyclerView.Adapter<OwnerParkingSpotAdapter.ViewHolder> {

    private List<ParkingSpot> parkingSpots;

    public OwnerParkingSpotAdapter(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_owner, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.spotTextView.setText(parkingSpots.get(position).getParkingDateTime());
    }

    @Override
    public int getItemCount() {
        return parkingSpots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView spotTextView;

        public ViewHolder(View view) {
            super(view);
            this.spotTextView = (TextView) view.findViewById(R.id.owner_item);
        }
    }
}
