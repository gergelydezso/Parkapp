package com.garmin.parkapp.business;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Shared preferences wrapper.
 *
 * @author ioana.morari on 12/11/15.
 */
public class PreferenceManager {

    private final static String PREFERENCES_NAME = "parking_spot";

    private SharedPreferences sharedPreferences;

    private static PreferenceManager instance;

    private PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
    }

    public static synchronized PreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceManager(context);
        }

        return instance;
    }

    public void writeString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void writeInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }
}
