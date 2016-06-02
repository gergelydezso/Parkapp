package com.garmin.parkapp.presentation.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.garmin.parkapp.R;
import com.garmin.parkapp.business.LoginChecker;
import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.presentation.LoginListener;
import com.garmin.parkapp.presentation.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity implements LoginListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.INSTANCE.d("onCreate())");

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            showFragment();
        }
    }

    private void showFragment() {
        Logger.INSTANCE.d("showFragment()");

        LoginChecker loginChecker = new LoginChecker();
        if (!loginChecker.isLoggedIn()) {
            showLoginFragment();
        } else {
            switch (loginChecker.getLoginType()) {
                case GUEST:
                    showGuestFragment();
                    break;
                case OWNER:
                    showOwnerFragment();
                    break;
            }
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
    }

    private void showGuestFragment() {
        Logger.INSTANCE.d("showGuestFragment()");
    }


    @Override
    public void login(LoginChecker.UserType userType) {
        Logger.INSTANCE.d("login(userType = %s)", userType.name());
    }
}
