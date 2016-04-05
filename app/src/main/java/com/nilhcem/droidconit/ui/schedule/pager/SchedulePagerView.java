package com.nilhcem.droidconit.ui.schedule.pager;

import com.nilhcem.droidconit.data.app.model.Schedule;

public interface SchedulePagerView {

    void displaySchedule(Schedule schedule);

    void displayLoadingError();
}
