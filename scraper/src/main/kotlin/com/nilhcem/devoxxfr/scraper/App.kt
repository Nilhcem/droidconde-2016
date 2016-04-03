package com.nilhcem.devoxxfr.scraper

import com.nilhcem.devoxxfr.scraper.api.DevoxxApi

fun main(args: Array<String>) {
    val api = DevoxxApi.SERVICE
    val me = api.getSpeakerDetails("9cc5353ec72a6d4614a652eae245ff5bdd260b58").execute().body()
    println(me)
}
