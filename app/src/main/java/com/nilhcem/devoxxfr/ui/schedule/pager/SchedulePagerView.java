package com.nilhcem.devoxxfr.ui.schedule.pager;

import com.nilhcem.devoxxfr.data.app.model.Schedule;

public interface SchedulePagerView {

    void displaySchedule(Schedule schedule);

    void displayLoadingError();
}
