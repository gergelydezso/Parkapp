package com.garmin.parkapp.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.garmin.parkapp.R;
import com.garmin.parkapp.business.request.ParkingSpotRequest;
import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.model.ParkingSpot;
import com.garmin.parkapp.presentation.adapter.GuestParkingSpotAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author morari on 6/2/16.
 */
public class GuestFragment extends BaseFragment implements View.OnClickListener, GuestParkingSpotAdapter.OccupySpotListener {

    private RecyclerView recyclerView;
    private TextView emptyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestYourParkingSpots();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTitleInActionBar(R.string.guest);

        view.findViewById(R.id.guest_call_to_open_gate).setOnClickListener(this);

        emptyView = (TextView) view.findViewById(R.id.guest_empty_view);

        recyclerView = (RecyclerView) view.findViewById(R.id.guest_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        GuestParkingSpotAdapter adapter = new GuestParkingSpotAdapter(new ArrayList<ParkingSpot>(), this);
        recyclerView.setAdapter(adapter);

        showEmptyListMessage();
    }

    @Override
    public void onClick(View v) {
        Logger.INSTANCE.d("onClick()");

        if (v.getId() == R.id.guest_call_to_open_gate) {
            Logger.INSTANCE.d("guest clicked call to open gate");

            callToOpenGate();
        }
    }

    @Override
    public void onSpotOccupied(ParkingSpot parkingSpot) {
        Logger.INSTANCE.d("onSpotOccupied(parkingSpot = %s)", parkingSpot);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_guest;
    }

    private void callToOpenGate() {
        Logger.INSTANCE.d("callToOpenGate()");

    }

    private void requestYourParkingSpots() {
        Logger.INSTANCE.d("requestYourParkingSpots()");

        ParkingSpotRequest parkingSpotRequest = new ParkingSpotRequest(new ParkingSpotListener());
        parkingSpotRequest.execute();
    }

    private void showEmptyListMessage() {
        Logger.INSTANCE.d("showEmptyListMessage()");

        if (recyclerView.getChildCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private class ParkingSpotListener implements ParkingSpotRequest.ParkingSpotResponse {

        @Override
        public void onResponse(List<ParkingSpot> parkingSpotList) {
            Logger.INSTANCE.d("onResponse()");

            showEmptyListMessage();
        }

        @Override
        public void onError() {
            Logger.INSTANCE.d("onError()");
        }
    }
}