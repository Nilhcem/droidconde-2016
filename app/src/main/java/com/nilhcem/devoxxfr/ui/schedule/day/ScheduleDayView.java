package com.nilhcem.devoxxfr.ui.schedule.day;

import com.nilhcem.devoxxfr.data.app.model.ScheduleSlot;

import java.util.List;

public interface ScheduleDayView {

    void initSlotsList(List<ScheduleSlot> slots);

    void refreshSlotsList();
}
