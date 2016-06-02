package com.garmin.parkapp.business.response;

import com.garmin.parkapp.business.PreferenceManager;
import com.google.gson.annotations.SerializedName;

/**
 * @author morari on 6/2/16.
 */
public class LoginTypeResponse {

    private final static String LOGIN_TYPE = "login.type";

    @SerializedName("userID")
    private String userId;

    @SerializedName("userRole")
    private String userRole;

    @SerializedName("canOpenBarrier")
    private String canOpenBarrier;

    @SerializedName("assignedSpot")
    private String assignedSpot;

    public LoginTypeResponse(String loginType) {
        this.userRole = loginType;
    }

    public void saveResponse(PreferenceManager preferenceManager) {
        preferenceManager.writeString(LOGIN_TYPE, userRole);
    }

    public static UserType getUserType(PreferenceManager preferenceManager) {

        String savedUserTYpe = preferenceManager.getString(LOGIN_TYPE);
        return UserType.getFromString(savedUserTYpe);
    }

    public UserType getUserType() {
        return UserType.OWNER;
    }

    @Override
    public String toString() {
        return "LoginTypeResponse{" +
                "userId='" + userId + '\'' +
                ", userRole='" + userRole + '\'' +
                ", canOpenBarrier=" + canOpenBarrier +
                ", assignedSpot='" + assignedSpot + '\'' +
                '}';
    }

    public enum UserType {

        OWNER("OWNER"),
        GUEST("GUEST");

        private final String userType;

        UserType(String userType) {
            this.userType = userType;
        }

        public static UserType getFromString(String type) {
            for (UserType userType : values()) {
                if (userType.userType.equals(type)) {
                    return userType;
                }
            }

            return OWNER;
        }
    }
}
