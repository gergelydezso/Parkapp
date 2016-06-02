package com.garmin.parkapp.presentation;

import com.garmin.parkapp.business.LoginChecker;

/**
 * @author morari on 6/2/16.
 */
public interface LoginListener {

    void login(LoginChecker.UserType userType);
}
