package com.garmin.parkapp.business.api;

import com.garmin.parkapp.business.response.LoginResponse;
import com.garmin.parkapp.business.response.LoginTypeResponse;
import com.garmin.parkapp.model.ParkingSpot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author morari on 6/2/16.
 */
public interface ParkingServiceApi {

    @FormUrlEncoded
    @POST("oauth/token")
    Call<LoginResponse> login(@Header("Content-Type") String contentType,
                              @Header("Authorization") String authorization,
                              @Header("Accept") String accept,
                              @Field("username") String username,
                              @Field("password") String password,
                              @Field("grant_type") String grantType,
                              @Field("scope") String scope,
                              @Field("client_id") String cliendId,
                              @Field("client_secret") String clientSecret);

    @GET("oauth/check-role")
    Call<LoginTypeResponse> getLoginType(
            @Header("Authorization") String authorization,
            @Query("username") String username);

    @GET("/parkingSpots")
    Call<List<ParkingSpot>> getParkingSpots();
}
