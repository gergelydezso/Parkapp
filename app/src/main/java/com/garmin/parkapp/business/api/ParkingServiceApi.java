package com.garmin.parkapp.business.api;

import com.garmin.parkapp.business.request.LoginRequest;
import com.garmin.parkapp.business.response.LoginResponse;
import com.garmin.parkapp.model.ParkingSpot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @author morari on 6/2/16.
 */
public interface ParkingServiceApi {

    @POST("oauth/token")
    Call<LoginResponse> login(@Header("Content-Type") String contentType,
                              @Header("Authorization") String authorization,
                              @Header("Accept") String accept,
                              @Body LoginRequest.LoginData credentials);

    @GET("/parkingSpots")
    Call<List<ParkingSpot>> getParkingSpots();
}
