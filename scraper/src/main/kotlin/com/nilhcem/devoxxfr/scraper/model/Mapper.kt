package com.nilhcem.devoxxfr.scraper.model

import com.nilhcem.devoxxfr.scraper.model.devoxx.ScheduleDaySlot
import com.nilhcem.devoxxfr.scraper.model.output.Room

val ScheduleDaySlot.roomOutputId: Int
    get() = Room.getRoomId(roomId)
