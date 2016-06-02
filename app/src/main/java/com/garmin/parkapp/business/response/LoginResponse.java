package com.garmin.parkapp.business.response;

import com.garmin.parkapp.business.PreferenceManager;
import com.google.gson.annotations.SerializedName;

/**
 * @author morari on 6/2/16.
 */
public class LoginResponse {

    public final static String LOGIN_TOKEN = "login.token";
    private final static String AUTH_BEARER = "Bearer %s";

    @SerializedName("access_token")
    private String accessToken;

    public LoginResponse(String loginToken) {
        this.accessToken = loginToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void saveLoginResponse(PreferenceManager preferenceManager) {
        preferenceManager.writeString(LOGIN_TOKEN, getAccessToken());
    }

    public String getAuthorizationBearer() {
        return String.format(AUTH_BEARER, accessToken);
    }

    public static LoginResponse getLoginResponse(PreferenceManager preferenceManager) {

        String loginToken = preferenceManager.getString(LOGIN_TOKEN);

        return new LoginResponse(loginToken);
    }
}
