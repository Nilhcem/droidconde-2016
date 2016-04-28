package com.nilhcem.droidconde.scraper

import com.nilhcem.droidconde.scraper.model.droidcon.Speaker
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.File

fun main(args: Array<String>) {
    val scraper = Scraper()
    val speakers = scraper.getSpeakers()

    createJson(speakers)
}

fun createJson(speakers: List<Speaker>) {
    val moshi = Moshi.Builder().build()
    File("output").mkdir()

    File("output/speakers.json").printWriter().use { out ->
        val adapter: JsonAdapter<List<Speaker>> = moshi.adapter(Types.newParameterizedType(List::class.java, Speaker::class.java))
        out.println(adapter.toJson(speakers))
    }
}
