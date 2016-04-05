package com.nilhcem.devoxxfr.ui.settings;

import android.content.Context;

import com.nilhcem.devoxxfr.receiver.BootReceiver;
import com.nilhcem.devoxxfr.receiver.reminder.SessionsReminder;
import com.nilhcem.devoxxfr.ui.BasePresenter;
import com.nilhcem.devoxxfr.utils.App;

public class SettingsPresenter extends BasePresenter<SettingsView> {

    private final Context context;
    private final SessionsReminder sessionsReminder;

    public SettingsPresenter(Context context, SettingsView view, SessionsReminder sessionsReminder) {
        super(view);
        this.context = context;
        this.sessionsReminder = sessionsReminder;
    }

    public void onCreate() {
        view.setAppVersion(App.getVersion());
    }

    public boolean onNotifySessionsChange(boolean checked) {
        view.setNotifySessionsCheckbox(checked);

        if (checked) {
            sessionsReminder.enableSessionReminder();
            BootReceiver.enable(context);
        } else {
            sessionsReminder.disableSessionReminder();
            BootReceiver.disable(context);
        }
        return true;
    }
}
