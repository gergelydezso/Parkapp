package com.garmin.parkapp.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garmin.parkapp.logger.Logger;
import com.garmin.parkapp.presentation.activity.MainActivity;

/**
 * Base class for all fragments in this application.
 *
 * @author ioana.morari on 04/2/2016.
 */
public abstract class BaseFragment extends Fragment {

    protected Logger logger = Logger.INSTANCE;

    protected View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        logger.d("onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logger.d("onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logger.d("onCreateView()");

        this.rootView = inflater.inflate(getLayoutId(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logger.d("onViewCreated()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        logger.d("onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();

        logger.d("onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();

        logger.d("onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();

        logger.d("onPause()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        logger.d("onSaveInstanceState()");
    }

    @Override
    public void onStop() {
        super.onStop();

        logger.d("onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        logger.d("onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        logger.d("onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        logger.d("onDetach()");
    }

    protected abstract int getLayoutId();

    protected void setTitleInActionBar(int title) {
        Logger.INSTANCE.d("setTitleInActionBar(title = %s)", getString(title));

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    protected void showError(int errorMessage) {
        Logger.INSTANCE.d("showError(errorMessage = %s)", getString(errorMessage));

        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG).show();
    }
}
