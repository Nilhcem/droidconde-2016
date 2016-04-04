package com.nilhcem.devoxxfr.scraper.model

import com.nilhcem.devoxxfr.scraper.model.devoxx.ScheduleDaySlot
import com.nilhcem.devoxxfr.scraper.model.output.Room
import java.text.SimpleDateFormat
import java.util.*

val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

val ScheduleDaySlot.outputRoomId: Int
    get() = Room.getRoomId(roomId)

val ScheduleDaySlot.outputStartAt: String
    get() = dateFormat.format(Date(fromTimeMillis))

val ScheduleDaySlot.outputDuration: Int
    get() = ((toTimeMillis - fromTimeMillis) / 60000).toInt()
