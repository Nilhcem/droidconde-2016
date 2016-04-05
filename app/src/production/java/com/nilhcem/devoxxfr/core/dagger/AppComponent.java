package com.nilhcem.devoxxfr.core.dagger;

import com.nilhcem.devoxxfr.DevoxxApp;
import com.nilhcem.devoxxfr.core.dagger.module.ApiModule;
import com.nilhcem.devoxxfr.core.dagger.module.AppModule;
import com.nilhcem.devoxxfr.core.dagger.module.DataModule;
import com.nilhcem.devoxxfr.core.dagger.module.DatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, DataModule.class, DatabaseModule.class})
public interface AppComponent extends AppGraph {

    /**
     * An initializer that creates the production graph from an application.
     */
    final class Initializer {

        private Initializer() {
            throw new UnsupportedOperationException();
        }

        public static AppComponent init(DevoxxApp app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .apiModule(new ApiModule())
                    .dataModule(new DataModule())
                    .databaseModule(new DatabaseModule())
                    .build();
        }
    }
}
