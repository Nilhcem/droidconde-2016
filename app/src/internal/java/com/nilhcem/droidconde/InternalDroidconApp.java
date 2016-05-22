package com.nilhcem.droidconde;

import android.os.Build;

import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.nilhcem.droidconde.core.dagger.AppComponent;
import com.nilhcem.droidconde.debug.lifecycle.ActivityProvider;
import com.nilhcem.droidconde.debug.stetho.StethoInitializer;

import javax.inject.Inject;

import jp.wasabeef.takt.Takt;

public class InternalDroidconApp extends DroidconApp {

    /**
     * Change it manually when you want to display the FPS.
     * Useful to test the frame rate.
     */
    private static final boolean DISPLAY_FPS = false;

    /**
     * Change it manually when you want to enable AndroidDevMetrics
     */
    private static final boolean ENABLE_ANDROID_DEV_METRICS = false;

    @Inject StethoInitializer stetho;
    @Inject ActivityProvider activityProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent.Initializer.init(this).inject(this);
        displayFps(true);
        initAndroidDevMetrics();
        stetho.init();
        activityProvider.init(this);
    }

    @Override
    public void onTerminate() {
        displayFps(false);
        super.onTerminate();
    }

    private void displayFps(boolean enable) {
        if (DISPLAY_FPS && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (enable) {
                Takt.stock(this).play();
            } else {
                Takt.finish();
            }
        }
    }

    private void initAndroidDevMetrics() {
        if (ENABLE_ANDROID_DEV_METRICS) {
            AndroidDevMetrics.initWith(this);
        }
    }
}
