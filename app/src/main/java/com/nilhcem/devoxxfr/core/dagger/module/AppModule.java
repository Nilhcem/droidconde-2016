package com.nilhcem.devoxxfr.core.dagger.module;

import android.app.Application;

import com.nilhcem.devoxxfr.DevoxxApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {

    private final DevoxxApp app;

    public AppModule(DevoxxApp app) {
        this.app = app;
    }

    @Provides @Singleton Application provideApplication() {
        return app;
    }
}
