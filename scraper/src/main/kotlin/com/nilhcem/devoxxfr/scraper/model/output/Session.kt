package com.nilhcem.devoxxfr.scraper.model.output

data class Session(val id: Int, val startAt: String, val duration: Int, val roomId: Int, val speakersId: List<Int>?, val title: String?, val description: String?)
