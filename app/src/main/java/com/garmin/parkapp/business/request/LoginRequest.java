package com.garmin.parkapp.business.request;

import com.garmin.parkapp.business.response.LoginResponse;
import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.model.Credentials;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class used to execute login.
 *
 * @author morari on 6/2/16.
 */
public class LoginRequest extends BaseRequest {

    private WeakReference<LoginRequestResponse> loginRequestResponseWeakReference;

    public LoginRequest(LoginRequestResponse loginRequestResponse) {
        this.loginRequestResponseWeakReference = new WeakReference<>(loginRequestResponse);
    }

    public void executeLogin(Credentials credentials) {
        Logger.INSTANCE.d("executeLogin(credentials = %s)", credentials);

        parkingServiceApi.login(credentials).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Logger.INSTANCE.d("onResponse()");

                if (loginRequestResponseWeakReference != null) {
                    if (loginRequestResponseWeakReference.get() != null) {
                        loginRequestResponseWeakReference.get().onResponse(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Logger.INSTANCE.d("onFailure()");

                if (loginRequestResponseWeakReference != null) {
                    if (loginRequestResponseWeakReference.get() != null) {
                        loginRequestResponseWeakReference.get().onError();
                    }
                }
            }
        });
    }

    public interface LoginRequestResponse {
        void onResponse(LoginResponse loginResponse);

        void onError();
    }
}
