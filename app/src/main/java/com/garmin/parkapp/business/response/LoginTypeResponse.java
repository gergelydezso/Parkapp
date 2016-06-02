package com.garmin.parkapp.business.response;

import com.garmin.parkapp.business.PreferenceManager;
import com.google.gson.annotations.SerializedName;

/**
 * @author morari on 6/2/16.
 */
public class LoginTypeResponse {

    private final static String USER_ID = "user.id";
    private final static String USER_ROLE = "user.role";
    private final static String CAN_OPEN_DOOR = "can.open.door";
    private final static String ASSIGNED_SPOT = "assigned.spot";

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

    public LoginTypeResponse(String userId, String userRole, String canOpenBarrier, String assignedSpot) {
        this.userId = userId;
        this.userRole = userRole;
        this.canOpenBarrier = canOpenBarrier;
        this.assignedSpot = assignedSpot;
    }

    public void saveResponse(PreferenceManager preferenceManager) {
        preferenceManager.writeString(USER_ID, userId);
        preferenceManager.writeString(USER_ROLE, userRole);
        preferenceManager.writeString(CAN_OPEN_DOOR, canOpenBarrier);
        preferenceManager.writeString(ASSIGNED_SPOT, assignedSpot);
    }

    public static UserType getUserType(PreferenceManager preferenceManager) {

        String savedUserTYpe = preferenceManager.getString(USER_ROLE);
        return UserType.getFromString(savedUserTYpe);
    }

    public static LoginTypeResponse getLoginTypeResponse(PreferenceManager preferenceManager) {

        String saveUserType = preferenceManager.getString(USER_ROLE);
        String userId = preferenceManager.getString(USER_ID);
        String canOpenBarrier = preferenceManager.getString(CAN_OPEN_DOOR);
        String assignedSpot = preferenceManager.getString(ASSIGNED_SPOT);

        return new LoginTypeResponse(userId,
                saveUserType,
                canOpenBarrier,
                assignedSpot);
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

    public String getUserId() {
        return userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getCanOpenBarrier() {
        return canOpenBarrier;
    }

    public String getAssignedSpot() {
        return assignedSpot;
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
