package com.garmin.parkapp.business;

import android.content.Context;

import com.garmin.parkapp.business.response.LoginResponse;
import com.garmin.parkapp.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author morari on 6/2/16.
 */
public class ParkingSpotFilter {

    public List<ParkingSpot> showYourParkingSpotsOnly(List<ParkingSpot> parkingSpots, Context context) {

        List<ParkingSpot> parkingSpots1 = new ArrayList<>();
        LoginResponse loginResponse = LoginResponse.getLoginResponse(PreferenceManager.getInstance(context));

        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getOwnerId().equals(loginResponse.getOwnerId())) {
                parkingSpots1.add(parkingSpot);
            }
        }
        return parkingSpots1;
    }
}
