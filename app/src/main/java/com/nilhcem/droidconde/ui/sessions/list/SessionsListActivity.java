package com.nilhcem.droidconde.ui.sessions.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nilhcem.droidconde.DroidconApp;
import com.nilhcem.droidconde.R;
import com.nilhcem.droidconde.data.app.SelectedSessionsMemory;
import com.nilhcem.droidconde.data.app.model.ScheduleSlot;
import com.nilhcem.droidconde.data.app.model.Session;
import com.nilhcem.droidconde.ui.BaseActivity;
import com.nilhcem.droidconde.ui.sessions.details.SessionDetailsActivityIntentBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import se.emilsjolander.intentbuilder.Extra;
import se.emilsjolander.intentbuilder.IntentBuilder;

@IntentBuilder
public class SessionsListActivity extends BaseActivity<SessionsListPresenter> implements SessionsListMvp.View {

    @Extra ScheduleSlot slot;

    @Inject Picasso picasso;
    @Inject SelectedSessionsMemory selectedSessionsMemory;

    @BindView(R.id.sessions_list_recyclerview) RecyclerView recyclerView;

    @Override
    protected SessionsListPresenter newPresenter() {
        return new SessionsListPresenter(this, this, slot);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DroidconApp.get(this).component().inject(this);
        SessionsListActivityIntentBuilder.inject(getIntent(), this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessions_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void initToobar(String title) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void initSessionsList(List<Session> sessions) {
        SessionsListAdapter adapter = new SessionsListAdapter(sessions, picasso, selectedSessionsMemory, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void startSessionDetails(Session session) {
        startActivity(new SessionDetailsActivityIntentBuilder(session).build(this));
    }
}
