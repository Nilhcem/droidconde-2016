package com.nilhcem.devoxxfr.scraper.model.devoxx

import com.squareup.moshi.Json

data class ScheduleDay(val slots: List<ScheduleDaySlot>)

data class ScheduleDaySlot(val roomId: String, val fromTimeMillis: Long, @Json(name = "break") val breakData: ScheduleDaySlotBreak?, val talk: ScheduleDaySlotTalk?, val toTimeMillis: Long)

data class ScheduleDaySlotBreak(val nameFR: String)

data class ScheduleDaySlotTalk(val title: String, val summary: String, val speakers: List<ScheduleDaySlotTalkSpeaker>)

data class ScheduleDaySlotTalkSpeaker(val link: ScheduleDaySlotTalkSpeakerLink)

data class ScheduleDaySlotTalkSpeakerLink(val href: String)
