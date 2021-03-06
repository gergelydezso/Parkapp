package com.garmin.parkapp.presentation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.garmin.parkapp.R;
import com.garmin.parkapp.business.LoginChecker;
import com.garmin.parkapp.business.PreferenceManager;
import com.garmin.parkapp.business.response.LoginTypeResponse;
import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.presentation.LoginListener;
import com.garmin.parkapp.presentation.fragment.GuestFragment;
import com.garmin.parkapp.presentation.fragment.LoginFragment;
import com.garmin.parkapp.presentation.fragment.OwnerFragment;
import com.pushwoosh.fragment.PushEventListener;
import com.pushwoosh.fragment.PushFragment;

public class MainActivity extends AppCompatActivity implements LoginListener, PushEventListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.INSTANCE.d("onCreate())");

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            showFragment();
        }

        PushFragment.init(this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        PushFragment.onNewIntent(this, intent);
    }

    @Override
    public void login(LoginTypeResponse.UserType userType) {
        Logger.INSTANCE.d("login(userType = %s)", userType.name());

        showFragmentAfterLogin(userType);
    }

    private void showFragment() {
        Logger.INSTANCE.d("showFragment()");

        LoginChecker loginChecker = new LoginChecker();
        if (!loginChecker.isLoggedIn(PreferenceManager.getInstance(this))) {
            showLoginFragment();
        } else {
            showFragmentAfterLogin(loginChecker.getLoginType(PreferenceManager.getInstance(this)));
        }
    }

    private void showLoginFragment() {
        Logger.INSTANCE.d("showLoginFragment()");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new LoginFragment())
                .commit();
    }

    private void showOwnerFragment() {
        Logger.INSTANCE.d("showOwnerFragment()");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new OwnerFragment())
                .commit();
    }

    private void showGuestFragment() {
        Logger.INSTANCE.d("showGuestFragment()");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new GuestFragment())
                .commit();
    }

    private void showFragmentAfterLogin(LoginTypeResponse.UserType userType) {
        Logger.INSTANCE.d("showFragmentAfterLogin()");

        switch (userType) {
            case GUEST:
                showGuestFragment();
                break;
            case OWNER:
                showOwnerFragment();
                break;
        }
    }

    @Override
    public void doOnUnregisteredError(String s) {

    }

    @Override
    public void doOnRegisteredError(String s) {

    }

    @Override
    public void doOnRegistered(String s) {

    }

    @Override
    public void doOnMessageReceive(String s) {

    }

    @Override
    public void doOnUnregistered(String s) {

    }
}
