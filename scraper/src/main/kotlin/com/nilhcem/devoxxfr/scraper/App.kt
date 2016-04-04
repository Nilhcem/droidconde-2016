package com.nilhcem.devoxxfr.scraper

import com.nilhcem.devoxxfr.scraper.api.DevoxxApi
import com.nilhcem.devoxxfr.scraper.model.Mapper
import com.nilhcem.devoxxfr.scraper.model.output.Speaker
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import java.io.File

fun main(args: Array<String>) {
    val api = DevoxxApi.SERVICE
    val days = listOf("wednesday", "thursday", "friday")
    File("output").mkdir()

    val speakers = api.getSpeakersUUID().execute().body()
            .map { api.getSpeakerDetails(it.uuid).execute().body() }
            .mapIndexed { id, speaker -> Mapper.convertSpeaker(id + 1, speaker) }
    saveSpeakers(speakers)

    val speakersMap = speakers.associateBy({ it.uuid }, { it.id })
}

fun saveSpeakers(speakers: List<Speaker>) {
    val moshi = DevoxxApi.MOSHI

    File("output/speakers.json").printWriter().use { out ->
        val adapter: JsonAdapter<List<Speaker>> = moshi.adapter(Types.newParameterizedType(List::class.java, Speaker::class.java))
        out.println(adapter.toJson(speakers))
    }
}
