package com.garmin.parkapp.business;

import android.text.TextUtils;

import com.garmin.parkapp.business.response.LoginResponse;

/**
 * @author morari on 6/2/16.
 */
public class LoginChecker {

    public boolean isLoggedIn(PreferenceManager preferenceManager) {
        return !TextUtils.isEmpty(preferenceManager.getString(LoginResponse.LOGIN_TOKEN));
    }

    public UserType getLoginType(PreferenceManager preferenceManager) {

        UserType userType;
        int typeOrdinal = preferenceManager.getInt(LoginResponse.LOGIN_TYPE);

        userType = UserType.values()[typeOrdinal];

        return userType;
    }

    public enum UserType {
        OWNER,
        GUEST
    }
}
