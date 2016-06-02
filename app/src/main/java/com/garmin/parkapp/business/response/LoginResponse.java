package com.garmin.parkapp.business.response;

import android.content.Context;

import com.garmin.parkapp.business.LoginChecker;
import com.garmin.parkapp.business.PreferenceManager;

/**
 * @author morari on 6/2/16.
 */
public class LoginResponse {

    public final static String LOGIN_TOKEN = "login.token";
    public final static String LOGIN_TYPE = "login.type";
    public final static String OWNER_ID = "owner.id";

    private String loginToken;
    private String ownerId;

    private LoginChecker.UserType userType = LoginChecker.UserType.OWNER;

    public LoginResponse(String loginToken, String ownerId, LoginChecker.UserType userType) {
        this.loginToken = loginToken;
        this.ownerId = ownerId;
        this.userType = userType;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public LoginChecker.UserType getUserType() {
        return userType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void saveLoginResponse(PreferenceManager preferenceManager) {

        preferenceManager.writeString(LOGIN_TOKEN, getLoginToken());
        preferenceManager.writeInt(LOGIN_TYPE, getUserType().ordinal());
        preferenceManager.writeString(OWNER_ID, getOwnerId());
    }

    public static LoginResponse getLoginResponse(PreferenceManager preferenceManager) {

        String loginToken = preferenceManager.getString(LOGIN_TOKEN);
        LoginChecker.UserType userType = new LoginChecker().getLoginType(preferenceManager);
        String ownerId = preferenceManager.getString(OWNER_ID);

        return new LoginResponse(loginToken, ownerId, userType);
    }
}
