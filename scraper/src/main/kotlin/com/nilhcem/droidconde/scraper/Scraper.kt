package com.nilhcem.droidconde.scraper

import com.nilhcem.droidconde.scraper.model.droidcon.Session
import com.nilhcem.droidconde.scraper.model.droidcon.Speaker
import org.jsoup.Jsoup

class Scraper {

    companion object {
        val BASE_URL = "http://droidcon.de"
        val SPEAKERS_URL = "$BASE_URL/en/program/speaker"
    }

    fun getSpeakers(): List<Speaker> {
        System.out.println("Get speakers");
        return Jsoup.connect(SPEAKERS_URL).get().select("div.view-content .views-row")
                .map {
                    it.select(".views-field-field-profile-last-name .field-content a").attr("href")
                }
                .distinct()
                .mapIndexed { index, url ->
                    System.out.println("Get speaker: #" + Integer.toString(index) + " - url: " + url);
                    val speakerDoc = Jsoup.connect(url).get().select("#main-content")

                    val name = speakerDoc.select("#main-content-header h1").text()
                    val photo = speakerDoc.select(".image-style-profile-default").attr("src")
                    val job = speakerDoc.select(".views-field-field-profile-job-title div").text()
                    val company = speakerDoc.select(".views-field-field-profile-organization div a").text()
                    val bio = speakerDoc.select(".views-field-field-profile-vita div").text()

                    val links = speakerDoc.select(".views-field-field-profile-links a").map { it.attr("href") }
                    val sessions = speakerDoc.select(".view-ncms-speaker-sessions-current a").map { it.attr("href") }

                    Speaker(index + 1, name, photo, job, company, bio, links, sessions)
                }
    }

    fun getSessions(speakers: List<Speaker>): List<Session> {
        System.out.println("Get sessions");
        return speakers
                .flatMap { it.sessions }
                .distinct()
                .mapIndexed { index, url ->
                    val fullUrl = "$BASE_URL$url"
                    System.out.println("Get session: #" + Integer.toString(index) + " - url: " + fullUrl);
                    val sessionDoc = Jsoup.connect(fullUrl).get().select(".block-content")

                    val title = sessionDoc.select(".node-header h1 a").text()
                    val description = sessionDoc.select(".field-name-field-session-description .field-items").text()

                    val speakersIds = speakers
                            .filter { it.sessions.contains(url) }
                            .map { it.id }
                    Session(index, url, title, description, speakersIds)
                }
    }
}
