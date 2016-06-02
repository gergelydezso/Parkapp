package com.garmin.parkapp.business.request;

import com.garmin.parkapp.business.api.ParkingServiceApi;
import com.garmin.parkapp.business.api.RetrofitClient;

/**
 * @author morari on 6/2/16.
 */
public abstract class BaseRequest {

    protected ParkingServiceApi parkingServiceApi;

    public BaseRequest() {

        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        parkingServiceApi = retrofitClient.getRetrofit().create(ParkingServiceApi.class);
    }
}
