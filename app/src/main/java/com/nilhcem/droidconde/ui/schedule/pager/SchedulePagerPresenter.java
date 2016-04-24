package com.nilhcem.droidconde.ui.schedule.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nilhcem.droidconde.data.app.DataProvider;
import com.nilhcem.droidconde.data.app.model.Schedule;
import com.nilhcem.droidconde.ui.BaseFragmentPresenter;

import icepick.State;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class SchedulePagerPresenter extends BaseFragmentPresenter<SchedulePagerView> {

    @State Schedule schedule;

    private final DataProvider dataProvider;
    private Subscription scheduleSubscription;

    public SchedulePagerPresenter(SchedulePagerView view, DataProvider dataProvider) {
        super(view);
        this.dataProvider = dataProvider;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (schedule == null) {
            loadData();
        } else {
            this.view.displaySchedule(schedule);
        }
    }

    public void reloadData() {
        loadData();
    }

    @Override
    public void onStop() {
        if (scheduleSubscription != null) {
            scheduleSubscription.unsubscribe();
        }
        super.onStop();
    }

    private void loadData() {
        scheduleSubscription = dataProvider.getSchedule()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(scheduleDays -> {
                    schedule = scheduleDays;
                    view.displaySchedule(schedule);
                }, throwable -> Timber.e(throwable, "Error getting schedule"), () -> {
                    if (schedule == null) {
                        view.displayLoadingError();
                    }
                });
    }
}
