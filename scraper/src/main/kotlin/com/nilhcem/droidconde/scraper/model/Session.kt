package com.nilhcem.droidconde.scraper.model

data class Session(val id: Int, val title: String, val description: String, val speakersId: List<Int>, val startAt: String?, val duration: Int, val roomId: Int)
