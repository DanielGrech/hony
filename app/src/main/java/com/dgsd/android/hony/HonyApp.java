package com.dgsd.android.hony;

import android.app.Application;

import timber.log.Timber;

public class HonyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
