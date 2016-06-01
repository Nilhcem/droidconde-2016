package com.nilhcem.droidconde.ui.schedule.pager;

import com.nilhcem.droidconde.data.app.model.Schedule;

public interface SchedulePagerMvp {

    interface View {
        void displaySchedule(Schedule schedule);

        void displayLoadingError();
    }

    interface Presenter {
        void reloadData();
    }
}
