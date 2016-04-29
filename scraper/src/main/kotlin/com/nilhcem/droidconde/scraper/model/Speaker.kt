package com.nilhcem.droidconde.scraper.model

data class Speaker(val id: Int, val name: String, val title: String?, val photo: String, val bio: String, val website: String?, val twitter: String?, val github: String?, @Transient val sessions: List<String>)
