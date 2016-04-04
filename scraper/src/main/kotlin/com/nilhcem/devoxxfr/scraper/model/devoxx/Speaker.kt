package com.nilhcem.devoxxfr.scraper.model.devoxx

data class SpeakerUUID(val uuid: String)

data class Speaker(val uuid: String, val bio: String, val company: String, val firstName: String,
                   val lastName: String, val blog: String, val avatarURL: String, val twitter: String)
