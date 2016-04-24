package com.nilhcem.droidconde.core.dagger;

import com.nilhcem.droidconde.receiver.BootReceiver;
import com.nilhcem.droidconde.ui.drawer.DrawerActivity;
import com.nilhcem.droidconde.ui.schedule.day.ScheduleDayFragment;
import com.nilhcem.droidconde.ui.schedule.pager.SchedulePagerFragment;
import com.nilhcem.droidconde.ui.sessions.details.SessionDetailsActivity;
import com.nilhcem.droidconde.ui.sessions.list.SessionsListActivity;
import com.nilhcem.droidconde.ui.settings.SettingsFragment;
import com.nilhcem.droidconde.ui.speakers.details.SpeakerDetailsDialogFragment;
import com.nilhcem.droidconde.ui.speakers.list.SpeakersListFragment;

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
