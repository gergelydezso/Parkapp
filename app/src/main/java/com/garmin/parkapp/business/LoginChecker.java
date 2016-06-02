package com.garmin.parkapp.business;

import android.text.TextUtils;

import com.garmin.parkapp.presentation.fragment.LoginFragment;

/**
 * @author morari on 6/2/16.
 */
public class LoginChecker {

    public boolean isLoggedIn(PreferenceManager preferenceManager) {
        return !TextUtils.isEmpty(preferenceManager.getString(LoginFragment.LOGIN_TOKEN));
    }

    public UserType getLoginType(PreferenceManager preferenceManager) {

        UserType userType;
        int typeOrdinal = preferenceManager.getInt(LoginFragment.LOGIN_TYPE);

        userType = UserType.values()[typeOrdinal];

        return userType;
    }

    public enum UserType {
        OWNER,
        GUEST
    }
}
