package com.garmin.parkapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author morari on 6/2/16.
 */
public class ParkingSpot {

    @SerializedName("day")
    private String day;

    @SerializedName("ownerId")
    private String ownerId;

    @SerializedName("guestId")
    private String guestId;

    @SerializedName("slot_no")
    private String slotNumber;

    public String getParkingNumber() {
        return slotNumber;
    }

    public String getParkingDateTime() {

        return day;
    }

    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "day='" + day + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", guestId='" + guestId + '\'' +
                ", slotNumber='" + slotNumber + '\'' +
                '}';
    }
}
