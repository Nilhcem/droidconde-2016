package com.nilhcem.droidconde.scraper

import com.nilhcem.droidconde.scraper.model.droidcon.Session
import com.nilhcem.droidconde.scraper.model.droidcon.Speaker
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Whitelist
import org.jsoup.select.Elements

class Scraper {

    companion object {
        val BASE_URL = "http://droidcon.de"
        val SPEAKERS_URL = "$BASE_URL/en/program/speaker"
    }

    fun getSpeakers(): List<Speaker> {
        System.out.println("Get speakers");
        return jsoup(SPEAKERS_URL).select("div.view-content .views-row")
                .map {
                    it.select(".views-field-field-profile-last-name .field-content a").attr("href")
                }
                .distinct()
                .mapIndexed { index, url ->
                    System.out.println("Get speaker: #" + Integer.toString(index) + " - url: " + url);
                    val speakerDoc = jsoup(url).select("#main-content")

                    val name = speakerDoc.select("#main-content-header h1").fmtText()
                    val photo = speakerDoc.select(".image-style-profile-default").attr("src")
                    val job = speakerDoc.select(".views-field-field-profile-job-title div").fmtText()
                    val company = speakerDoc.select(".views-field-field-profile-organization div a").fmtText()
                    val bio = speakerDoc.select(".views-field-field-profile-vita div").fmtText()

                    val links = speakerDoc.select(".views-field-field-profile-links a").map { it.attr("href") }
                    val twitter = links.filter { it.contains("twitter.com", true) }.firstOrNull()
                    val github = links.filter { it.contains("github.com", true) }.firstOrNull()
                    val website = links.filter { !it.equals(twitter) && !it.equals(github) }.firstOrNull()

                    val sessions = speakerDoc.select(".view-ncms-speaker-sessions-current a").map { it.attr("href") }

                    Speaker(index + 1, name, photo, job, company, bio, website, twitter, github, sessions)
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
                    val sessionDoc = jsoup(fullUrl).select(".block-content")

                    val title = sessionDoc.select(".node-header h1 a").fmtText()
                    val description = sessionDoc.select(".field-name-field-session-description .field-items").fmtText()

                    val speakersIds = speakers
                            .filter { it.sessions.contains(url) }
                            .map { it.id }
                    Session(index, url, title, description, speakersIds)
                }
    }

    private fun jsoup(url: String, nbRetries: Int = 3): Document {
        (0..nbRetries).forEach {
            try {
                return Jsoup.connect(url).get()
            } catch (e: Exception) {
                System.err.println("Error: ${e.message}. Retry");
                if (it == nbRetries - 1) {
                    throw e
                }
            }
        }
        throw IllegalStateException("This should not happen")
    }

    private fun Elements.fmtText(): String {
        var html = Jsoup.clean(html(), "", Whitelist.basic(), Document.OutputSettings().prettyPrint(false))
        html = html.replace(Regex("<br[ /]*>", RegexOption.IGNORE_CASE), "\n")
        html = html.replace("<p>", "").replace("</p>", "\n")
        html = html.replace(Regex("\\s*\n\\s*"), "\n")
        html = html.replace(Regex("^\n"), "")
        html = html.replace(Regex("\n$"), "")
        return html
    }
}
