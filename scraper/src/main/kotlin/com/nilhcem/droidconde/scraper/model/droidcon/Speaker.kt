package com.nilhcem.droidconde.scraper.model.droidcon

data class Speaker(val id: Int, val name: String, val photo: String, val job: String, val company: String, val bio: String, val website: String?, val twitter: String?, val github: String?, val sessions: List<String>)
