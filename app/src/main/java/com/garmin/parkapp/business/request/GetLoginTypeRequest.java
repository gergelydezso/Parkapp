package com.garmin.parkapp.business.request;

import com.garmin.parkapp.business.response.LoginTypeResponse;
import com.garmin.parkapp.logger.Logger;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author morari on 6/2/16.
 */
public class GetLoginTypeRequest extends BaseRequest {

    private WeakReference<LoginTypeListener> loginTypeListenerWeakReference;

    public GetLoginTypeRequest(LoginTypeListener loginTypeListener) {
        this.loginTypeListenerWeakReference = new WeakReference<>(loginTypeListener);
    }

    public void execute(String username, String authorization) {
        Logger.INSTANCE.d("execute(username = %s, authorization = %s)", username, authorization);

        parkingServiceApi.getLoginType(authorization, username).enqueue(new Callback<LoginTypeResponse>() {
            @Override
            public void onResponse(Call<LoginTypeResponse> call, Response<LoginTypeResponse> response) {
                Logger.INSTANCE.d("onResponse(loginTypeResponse = %s)", response.body());

                Logger.INSTANCE.d("response code = %d", response.code());

                if (response.code() == 200) {
                    if (loginTypeListenerWeakReference != null && loginTypeListenerWeakReference.get() != null) {
                        loginTypeListenerWeakReference.get().onResponse(response.body());
                    }
                } else {
                    if (loginTypeListenerWeakReference != null && loginTypeListenerWeakReference.get() != null) {
                        loginTypeListenerWeakReference.get().onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginTypeResponse> call, Throwable t) {
                Logger.INSTANCE.e("onFailure(t = %s)", t);

                if (loginTypeListenerWeakReference != null && loginTypeListenerWeakReference.get() != null) {
                    loginTypeListenerWeakReference.get().onError();
                }
            }
        });

    }

    public interface LoginTypeListener {

        void onResponse(LoginTypeResponse loginTypeResponse);

        void onError();
    }
}
