package com.garmin.parkapp.business;

import android.text.TextUtils;

import com.garmin.parkapp.business.response.LoginResponse;
import com.garmin.parkapp.business.response.LoginTypeResponse;

/**
 * @author morari on 6/2/16.
 */
public class LoginChecker {

    public boolean isLoggedIn(PreferenceManager preferenceManager) {
        return !TextUtils.isEmpty(preferenceManager.getString(LoginResponse.LOGIN_TOKEN));
    }

    public LoginTypeResponse.UserType getLoginType(PreferenceManager preferenceManager) {
        return LoginTypeResponse.getUserType(preferenceManager);
    }
}
