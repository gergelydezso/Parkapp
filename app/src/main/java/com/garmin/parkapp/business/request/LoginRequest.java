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

    private final static String ACCEPT = "application/json";
    private final static String AUTHORIZATION = "Basic cGFya2FwcC1tb2JpbGU6MTIzNDU2";
    private final static String CONTENT_TYPE = "application/x-www-form-urlencoded";

    private final static String GRANT_TYPE = "password";
    private final static String SCOPE = "read";
    private final static String CLIENT_ID = "parkapp-mobile";
    private final static String CLIENT_SECRET = "123456";

    private WeakReference<LoginRequestResponse> loginRequestResponseWeakReference;

    public LoginRequest(LoginRequestResponse loginRequestResponse) {
        this.loginRequestResponseWeakReference = new WeakReference<>(loginRequestResponse);
    }

    public void executeLogin(Credentials credentials) {
        Logger.INSTANCE.d("executeLogin(credentials = %s)", credentials);

        parkingServiceApi.login(
                CONTENT_TYPE,
                AUTHORIZATION,
                ACCEPT,
                credentials.getUsername(),
                credentials.getPassword(),
                GRANT_TYPE,
                SCOPE,
                CLIENT_ID,
                CLIENT_SECRET).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Logger.INSTANCE.d("onResponse()");

                if (loginRequestResponseWeakReference != null) {
                    if (loginRequestResponseWeakReference.get() != null) {

                        if (response.code() == 200) {
                            loginRequestResponseWeakReference.get().onResponse(response.body());
                        } else {
                            loginRequestResponseWeakReference.get().onError();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Logger.INSTANCE.e("onFailure(t = %s)", t);

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
