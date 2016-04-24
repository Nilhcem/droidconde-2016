package com.nilhcem.droidconde.core.dagger;

import com.nilhcem.droidconde.InternalDroidconApp;

public interface InternalAppGraph extends AppGraph {

    void inject(InternalDroidconApp app);
}
