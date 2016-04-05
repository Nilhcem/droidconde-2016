package com.nilhcem.droidconit.core.dagger;

import com.nilhcem.droidconit.InternalDroidconApp;

public interface InternalAppGraph extends AppGraph {

    void inject(InternalDroidconApp app);
}
