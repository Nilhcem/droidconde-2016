package com.nilhcem.devoxxfr.scraper.model.output

data class Speaker(val id: Int, @Transient val uuid: String, val name: String, val title: String?,
                   val bio: String, val website: String?, val twitter: String?, val photo: String?)
