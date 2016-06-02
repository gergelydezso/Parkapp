package com.garmin.parkapp.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.garmin.parkapp.R;
import com.garmin.parkapp.business.PreferenceManager;
import com.garmin.parkapp.business.request.ParkingSpotRequest;
import com.garmin.parkapp.business.response.LoginResponse;
import com.garmin.parkapp.business.response.LoginTypeResponse;
import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.model.ParkingSpot;
import com.garmin.parkapp.presentation.adapter.OwnerParkingSpotAdapter;

import java.util.List;

/**
 * @author morari on 6/2/16.
 */
public class OwnerFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private TextView emptyView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTitleInActionBar(R.string.owner);
        view.findViewById(R.id.owner_add_spot).setOnClickListener(this);

        emptyView = (TextView) view.findViewById(R.id.owner_empty_view);

        recyclerView = (RecyclerView) view.findViewById(R.id.owner_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        showEmptyListMessage();
        requestYourParkingSpots();
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

        LoginResponse loginResponse = LoginResponse.getLoginResponse(PreferenceManager.getInstance(getContext()));
        LoginTypeResponse loginTypeResponse = LoginTypeResponse.getLoginTypeResponse(PreferenceManager.getInstance(getContext()));

        ParkingSpotRequest parkingSpotRequest = new ParkingSpotRequest(new ParkingSpotListener());
        parkingSpotRequest.execute(loginResponse.getAuthorizationBearer(), loginTypeResponse);
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
            Logger.INSTANCE.d("onResponse(parkingSpotList = %d)", parkingSpotList == null ? 0 : parkingSpotList.size());

            OwnerParkingSpotAdapter adapter = new OwnerParkingSpotAdapter(parkingSpotList);
            recyclerView.setAdapter(adapter);

            showEmptyListMessage();
        }

        @Override
        public void onError() {
            Logger.INSTANCE.d("onError()");
        }
    }
}
