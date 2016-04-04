package com.nilhcem.devoxxfr.scraper.model

import com.nilhcem.devoxxfr.scraper.model.devoxx.ScheduleDaySlot
import com.nilhcem.devoxxfr.scraper.model.output.Room
import com.nilhcem.devoxxfr.scraper.model.output.Speaker
import java.text.SimpleDateFormat
import java.util.*

val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

val ScheduleDaySlot.outputRoomId: Int
    get() = Room.getRoomId(roomId)

val ScheduleDaySlot.outputStartAt: String
    get() = dateFormat.format(Date(fromTimeMillis))

val ScheduleDaySlot.outputDuration: Int
    get() = ((toTimeMillis - fromTimeMillis) / 60000).toInt()

object Mapper {
    fun convertSpeaker(id: Int, speaker: com.nilhcem.devoxxfr.scraper.model.devoxx.Speaker): Speaker {
        val name = "${speaker.firstName} ${speaker.lastName}".trim()
        val company = speaker.company?.trim()
        val bio = speaker.bio.trim()
        val blog = speaker.blog?.trim()
        val twitter = speaker.twitter?.trim()?.replace("@", "")

        val avatarURL = speaker.avatarURL?.trim()
        val picture = if (avatarURL == null || avatarURL.length == 0 || avatarURL.startsWith("data:image")) null else avatarURL

        return Speaker(id, speaker.uuid, name, company, bio, blog, twitter, picture)
    }
}
