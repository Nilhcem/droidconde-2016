package com.nilhcem.droidconde.scraper.model.droidcon

data class Session(val id: Int, val url: String, val title: String, val description: String, val speakersIds: List<Int>)
