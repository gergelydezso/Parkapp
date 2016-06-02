package com.garmin.parkapp.business.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author morari on 6/2/16.
 */
public class RetrofitClient {

    private final static String BASE_URL = "http://10.68.162.137:8080/parkapp/";

    private static RetrofitClient instance;

    private Retrofit retrofit;

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private RetrofitClient() {

        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
