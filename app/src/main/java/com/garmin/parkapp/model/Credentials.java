package com.garmin.parkapp.model;

import android.text.TextUtils;

/**
 * @author morari on 6/2/16.
 */
public class Credentials {

    private String username;
    private String password;

    public boolean isValid() {
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);
    }

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
