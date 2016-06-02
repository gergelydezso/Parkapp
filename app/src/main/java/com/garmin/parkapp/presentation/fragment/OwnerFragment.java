package com.garmin.parkapp.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.garmin.parkapp.R;
import com.garmin.parkapp.business.request.ParkingSpotRequest;
import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.model.ParkingSpot;
import com.garmin.parkapp.presentation.adapter.OwnerParkingSpotAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author morari on 6/2/16.
 */
public class OwnerFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestYourParkingSpots();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.owner_add_spot).setOnClickListener(this);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.owner_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        OwnerParkingSpotAdapter adapter = new OwnerParkingSpotAdapter(new ArrayList<ParkingSpot>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Logger.INSTANCE.d("onClick()");

        if (v.getId() == R.id.owner_add_spot) {
            Logger.INSTANCE.d("owner add spot click");

            showDatePickerDialog();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_owner;
    }

    private void showDatePickerDialog() {
        Logger.INSTANCE.d("showDatePickerDialog()");

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    private void requestYourParkingSpots() {
        Logger.INSTANCE.d("requestYourParkingSpots()");

        ParkingSpotRequest parkingSpotRequest = new ParkingSpotRequest(new ParkingSpotListener());
        parkingSpotRequest.execute();
    }

    private class ParkingSpotListener implements ParkingSpotRequest.ParkingSpotResponse {

        @Override
        public void onResponse(List<ParkingSpot> parkingSpotList) {
            Logger.INSTANCE.d("onResponse()");
        }

        @Override
        public void onError() {
            Logger.INSTANCE.d("onError()");
        }
    }
}
