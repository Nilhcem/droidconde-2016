package com.nilhcem.droidconit.core.dagger;

import com.nilhcem.droidconit.receiver.BootReceiver;
import com.nilhcem.droidconit.ui.drawer.DrawerActivity;
import com.nilhcem.droidconit.ui.schedule.day.ScheduleDayFragment;
import com.nilhcem.droidconit.ui.schedule.pager.SchedulePagerFragment;
import com.nilhcem.droidconit.ui.sessions.details.SessionDetailsActivity;
import com.nilhcem.droidconit.ui.sessions.list.SessionsListActivity;
import com.nilhcem.droidconit.ui.settings.SettingsFragment;
import com.nilhcem.droidconit.ui.speakers.details.SpeakerDetailsDialogFragment;
import com.nilhcem.droidconit.ui.speakers.list.SpeakersListFragment;

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
