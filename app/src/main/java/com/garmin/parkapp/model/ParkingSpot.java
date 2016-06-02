package com.garmin.parkapp.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author morari on 6/2/16.
 */
public class ParkingSpot {

    private long date = 0;
    private String ownerId;
    private String guestId;

    private String parkingNumber;

    public String getParkingDateTime() {

        Date date = new Date(this.date);

        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.getDefault());
        return format.format(date);
    }

    public String getOwnerId() {
        return ownerId;
    }
}
