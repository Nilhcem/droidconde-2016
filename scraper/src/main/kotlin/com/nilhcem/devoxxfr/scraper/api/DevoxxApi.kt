package com.nilhcem.devoxxfr.scraper.api

import com.nilhcem.devoxxfr.scraper.model.devoxx.ScheduleDay
import com.nilhcem.devoxxfr.scraper.model.devoxx.Speaker
import com.nilhcem.devoxxfr.scraper.model.devoxx.SpeakerUUID
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DevoxxApi {

    companion object {
        private val ENDPOINT = "https://cfp.devoxx.fr/api/conferences/devoxxFR2016/"

        val MOSHI = Moshi.Builder().build()

        val SERVICE = Retrofit.Builder()
                .client(OkHttpClient())
                .baseUrl(ENDPOINT)
                .addConverterFactory(MoshiConverterFactory.create(MOSHI))
                .build()
                .create(DevoxxApi::class.java)
    }

    @GET("schedules/{day}")
    fun getScheduleForDay(@Path("day") day: String): Call<ScheduleDay>

    @GET("speakers")
    fun getSpeakersUUID(): Call<List<SpeakerUUID>>

    @GET("speakers/{uuid}")
    fun getSpeakerDetails(@Path("uuid") uuid: String): Call<Speaker>
}
