package com.garmin.parkapp.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.garmin.parkapp.R;
import com.garmin.parkapp.business.LoginChecker;
import com.garmin.parkapp.business.request.LoginRequest;
import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.model.Credentials;
import com.garmin.parkapp.presentation.LoginListener;

/**
 * Fragment used to display login screen.
 *
 * @author morari on 6/2/16.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private LoginListener loginListener;
    private TextInputEditText usernameInput;
    private TextInputEditText passwordInput;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        loginListener = (LoginListener) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usernameInput = (TextInputEditText) view.findViewById(R.id.login_username);
        passwordInput = (TextInputEditText) view.findViewById(R.id.login_password);

        view.findViewById(R.id.login_button).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onClick(View v) {
        Logger.INSTANCE.d("onClick()");

        if (v.getId() == R.id.login_button) {

            Credentials credentials = new Credentials(usernameInput.getText().toString(),
                    passwordInput.getText().toString());

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.executeLogin(credentials);

            loginListener.login(LoginChecker.UserType.OWNER);
        }
    }
}
