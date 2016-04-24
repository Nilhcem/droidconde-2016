package com.nilhcem.droidconde.ui.schedule.pager;

import com.nilhcem.droidconde.data.app.model.Schedule;

public interface SchedulePagerView {

    void displaySchedule(Schedule schedule);

    void displayLoadingError();
}
