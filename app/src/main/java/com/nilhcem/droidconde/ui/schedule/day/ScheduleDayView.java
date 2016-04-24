package com.nilhcem.droidconde.ui.schedule.day;

import com.nilhcem.droidconde.data.app.model.ScheduleSlot;

import java.util.List;

public interface ScheduleDayView {

    void initSlotsList(List<ScheduleSlot> slots);

    void refreshSlotsList();
}
