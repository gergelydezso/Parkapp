package com.garmin.parkapp.business.request;

import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.model.ParkingSpot;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author morari on 6/2/16.
 */
public class ParkingSpotRequest extends BaseRequest {

    private WeakReference<ParkingSpotResponse> parkingSpotResponseWeakReference;

    public ParkingSpotRequest(ParkingSpotResponse parkingSpotResponse) {
        this.parkingSpotResponseWeakReference = new WeakReference<>(parkingSpotResponse);
    }

    public void execute() {
        Logger.INSTANCE.d("execute()");

        parkingServiceApi.getParkingSpots().enqueue(new Callback<List<ParkingSpot>>() {
            @Override
            public void onResponse(Call<List<ParkingSpot>> call, Response<List<ParkingSpot>> response) {
                Logger.INSTANCE.d("onResponse()");

                if (parkingSpotResponseWeakReference != null) {
                    if (parkingSpotResponseWeakReference.get() != null) {
                        parkingSpotResponseWeakReference.get().onResponse(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ParkingSpot>> call, Throwable t) {
                Logger.INSTANCE.d("onFailure()");

                if (parkingSpotResponseWeakReference != null) {
                    if (parkingSpotResponseWeakReference.get() != null) {
                        parkingSpotResponseWeakReference.get().onError();
                    }
                }
            }
        });
    }

    public interface ParkingSpotResponse {
        void onResponse(List<ParkingSpot> parkingSpotList);

        void onError();
    }
}
