package com.garmin.parkapp.business;

/**
 * @author morari on 6/2/16.
 */
public class LoginChecker {

    public boolean isLoggedIn() {
        return false;
    }

    public UserType getLoginType() {
        return UserType.OWNER;
    }

    public enum UserType {
        OWNER,
        GUEST
    }
}
