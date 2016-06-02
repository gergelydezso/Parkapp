package com.garmin.parkapp.business;

import com.garmin.parkapp.business.response.LoginTypeResponse;
import com.garmin.parkapp.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author morari on 6/2/16.
 */
public class ParkingSpotFilter {

    public List<ParkingSpot> showYourParkingSpotsOnly(List<ParkingSpot> parkingSpots, LoginTypeResponse loginTypeResponse) {

        List<ParkingSpot> parkingSpots1 = new ArrayList<>();

        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getOwnerId().equals(loginTypeResponse.getUserId())) {
                parkingSpots1.add(parkingSpot);
            }
        }
        return parkingSpots1;
    }
}


