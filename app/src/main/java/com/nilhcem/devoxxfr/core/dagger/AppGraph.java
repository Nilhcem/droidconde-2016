package com.nilhcem.devoxxfr.core.dagger;

import com.nilhcem.devoxxfr.receiver.BootReceiver;
import com.nilhcem.devoxxfr.ui.drawer.DrawerActivity;
import com.nilhcem.devoxxfr.ui.schedule.day.ScheduleDayFragment;
import com.nilhcem.devoxxfr.ui.schedule.pager.SchedulePagerFragment;
import com.nilhcem.devoxxfr.ui.sessions.details.SessionDetailsActivity;
import com.nilhcem.devoxxfr.ui.sessions.list.SessionsListActivity;
import com.nilhcem.devoxxfr.ui.settings.SettingsFragment;
import com.nilhcem.devoxxfr.ui.speakers.details.SpeakerDetailsDialogFragment;
import com.nilhcem.devoxxfr.ui.speakers.list.SpeakersListFragment;

/**
 * A common interface implemented by both the internal and production flavored components.
 */
public interface AppGraph {

    void inject(DrawerActivity activity);

    void inject(SchedulePagerFragment fragment);

    void inject(ScheduleDayFragment fragment);

    void inject(SessionsListActivity activity);

    void inject(SpeakersListFragment fragments);

    void inject(SessionDetailsActivity activity);

    void inject(SpeakerDetailsDialogFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(BootReceiver receiver);
}
