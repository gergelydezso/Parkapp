package com.garmin.parkapp.business.response;

import com.garmin.parkapp.business.LoginChecker;

/**
 * @author morari on 6/2/16.
 */
public class LoginResponse {

    private String loginToken;

    private LoginChecker.UserType userType = LoginChecker.UserType.OWNER;

    public String getLoginToken() {
        return loginToken;
    }

    public LoginChecker.UserType getUserType() {
        return userType;
    }
}
