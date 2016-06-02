package com.garmin.parkapp.business.request;

import com.garmin.parkapp.business.response.LoginResponse;
import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.model.Credentials;
import com.google.gson.annotations.SerializedName;

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

    private WeakReference<LoginRequestResponse> loginRequestResponseWeakReference;

    public LoginRequest(LoginRequestResponse loginRequestResponse) {
        this.loginRequestResponseWeakReference = new WeakReference<>(loginRequestResponse);
    }

    public void executeLogin(Credentials credentials) {
        Logger.INSTANCE.d("executeLogin(credentials = %s)", credentials);

        LoginData loginData = new LoginData(credentials);

        parkingServiceApi.login(
                CONTENT_TYPE,
                AUTHORIZATION,
                ACCEPT,
                loginData).enqueue(new Callback<LoginResponse>() {
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

    public static class LoginData {

        @SerializedName("username")
        private String username;

        @SerializedName("password")
        private String password;

        @SerializedName("grant_type")
        private String grantType;

        @SerializedName("scope")
        private String scope;

        @SerializedName("client_id")
        private String clientId;

        @SerializedName("client_secrete")
        private String cliendSecrete;

        public LoginData(Credentials credentials) {
            this.username = credentials.getUsername();
            this.password = credentials.getPassword();

            this.grantType = "password";
            this.scope = "read";
            this.clientId = "parkapp-mobile";
            this.cliendSecrete = "123456";
        }
    }
}
