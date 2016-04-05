package com.nilhcem.droidconit.ui.sessions.list;

import com.nilhcem.droidconit.data.app.model.Session;

import java.util.List;

public interface SessionsListView {

    void initToobar(String title);

    void initSessionsList(List<Session> sessions);

    void startSessionDetails(Session session);
}
