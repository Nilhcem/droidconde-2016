package com.nilhcem.devoxxfr.scraper.model.devoxx

data class ScheduleDay(val slots: List<ScheduleDaySlot>)

data class ScheduleDaySlot(val roomId: String, val fromTimeMillis: Long)
