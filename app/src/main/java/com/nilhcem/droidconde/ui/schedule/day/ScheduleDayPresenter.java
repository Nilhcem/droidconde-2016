package com.nilhcem.droidconde.ui.schedule.day;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nilhcem.droidconde.data.app.model.ScheduleDay;
import com.nilhcem.droidconde.ui.BaseFragmentPresenter;

public class ScheduleDayPresenter extends BaseFragmentPresenter<ScheduleDayView> {

    private final ScheduleDay scheduleDay;

    public ScheduleDayPresenter(ScheduleDayView view, ScheduleDay scheduleDay) {
        super(view);
        this.scheduleDay = scheduleDay;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view.initSlotsList(scheduleDay.getSlots());
    }

    @Override
    public void onResume() {
        super.onResume();
        view.refreshSlotsList();
    }
}
