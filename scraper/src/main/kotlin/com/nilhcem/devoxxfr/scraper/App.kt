package com.nilhcem.devoxxfr.scraper

import com.nilhcem.devoxxfr.scraper.api.DevoxxApi
import com.nilhcem.devoxxfr.scraper.model.Mapper.convertSession
import com.nilhcem.devoxxfr.scraper.model.Mapper.convertSpeaker
import com.nilhcem.devoxxfr.scraper.model.output.Session
import com.nilhcem.devoxxfr.scraper.model.output.Speaker
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import java.io.File

fun main(args: Array<String>) {
    val api = DevoxxApi.SERVICE

    val speakers = api.getSpeakersUUID().execute().body()
            .map { api.getSpeakerDetails(it.uuid).execute().body() }
            .mapIndexed { id, speaker -> convertSpeaker(id, speaker) }

    val speakersMap = speakers.associateBy({ it.uuid }, { it.id })

    var sessionId = 0
    val sessions = listOf("wednesday", "thursday", "friday").flatMap {
        api.getScheduleForDay(it).execute().body().slots
                .map { convertSession(++sessionId, it, speakersMap) }
    }

    createJsons(speakers, sessions)
}

fun createJsons(speakers: List<Speaker>, sessions: List<Session>) {
    val moshi = DevoxxApi.MOSHI
    File("output").mkdir()

    File("output/speakers.json").printWriter().use { out ->
        val adapter: JsonAdapter<List<Speaker>> = moshi.adapter(Types.newParameterizedType(List::class.java, Speaker::class.java))
        out.println(adapter.toJson(speakers))
    }

    File("output/sessions.json").printWriter().use { out ->
        val adapter: JsonAdapter<List<Session>> = moshi.adapter(Types.newParameterizedType(List::class.java, Session::class.java))
        out.println(adapter.toJson(sessions))
    }
}
