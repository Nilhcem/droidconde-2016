package com.nilhcem.droidconde.ui.sessions.details;

import android.support.annotation.StringRes;

import com.nilhcem.droidconde.data.app.model.Session;

public interface SessionDetailsView {

    void bindSessionDetails(Session session);

    void updateFabButton(boolean isSelected, boolean animate);

    void showSnackbarMessage(@StringRes int resId);
}
