package com.nilhcem.droidconit.ui.schedule.day;

import com.nilhcem.droidconit.data.app.model.ScheduleSlot;

import java.util.List;

public interface ScheduleDayView {

    void initSlotsList(List<ScheduleSlot> slots);

    void refreshSlotsList();
}
