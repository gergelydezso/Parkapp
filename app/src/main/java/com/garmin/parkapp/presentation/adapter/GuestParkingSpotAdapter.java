package com.garmin.parkapp.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.garmin.parkapp.R;
import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.model.ParkingSpot;

import java.util.List;

/**
 * @author morari on 6/2/16.
 */
public class GuestParkingSpotAdapter extends RecyclerView.Adapter<GuestParkingSpotAdapter.ViewHolder> {

    private OccupySpotListener occupySpotListener;

    private List<ParkingSpot> parkingSpots;

    public GuestParkingSpotAdapter(List<ParkingSpot> parkingSpots, OccupySpotListener occupySpotListener) {
        this.parkingSpots = parkingSpots;
        this.occupySpotListener = occupySpotListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_owner, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ParkingSpot parkingSpot = parkingSpots.get(position);

        holder.spotTextView.setText(parkingSpot.getParkingDateTime());
        holder.occupySpotButton.setOnClickListener(new OccupySpotClickListener(parkingSpot));
        holder.spotNumberTextView.setText(parkingSpot.getParkingNumber());
    }

    @Override
    public int getItemCount() {
        return parkingSpots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView spotTextView;
        public TextView spotNumberTextView;
        public Button occupySpotButton;

        public ViewHolder(View view) {
            super(view);

            this.spotTextView = (TextView) view.findViewById(R.id.guest_item_date);
            this.occupySpotButton = (Button) view.findViewById(R.id.guest_occupy_place);
            this.spotNumberTextView = (TextView) view.findViewById(R.id.guest_spot_no);
        }
    }

    private class OccupySpotClickListener implements View.OnClickListener {

        private ParkingSpot parkingSpot;

        public OccupySpotClickListener(ParkingSpot parkingSpot) {
            this.parkingSpot = parkingSpot;
        }

        @Override
        public void onClick(View v) {
            Logger.INSTANCE.d("onClick()");

            if (occupySpotListener != null) {
                occupySpotListener.onSpotOccupied(parkingSpot);
            }
        }
    }

    public interface OccupySpotListener {
        void onSpotOccupied(ParkingSpot parkingSpot);
    }
}