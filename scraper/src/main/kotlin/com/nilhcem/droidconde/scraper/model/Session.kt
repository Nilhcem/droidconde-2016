package com.nilhcem.droidconde.scraper.model

data class Session(val id: Int, val title: String, val description: String, val speakersIds: List<Int>, val startAt: String = "", val duration: Int = 0, val roomId: Int = 0)
