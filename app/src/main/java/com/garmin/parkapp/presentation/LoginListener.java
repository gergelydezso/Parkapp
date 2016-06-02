package com.garmin.parkapp.presentation;

import com.garmin.parkapp.business.response.LoginTypeResponse;

/**
 * @author morari on 6/2/16.
 */
public interface LoginListener {

    void login(LoginTypeResponse.UserType userType);
}
