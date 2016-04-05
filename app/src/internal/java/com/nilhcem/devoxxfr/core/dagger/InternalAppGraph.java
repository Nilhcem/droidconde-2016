package com.nilhcem.devoxxfr.core.dagger;

import com.nilhcem.devoxxfr.InternalDevoxxApp;

public interface InternalAppGraph extends AppGraph {

    void inject(InternalDevoxxApp app);
}
