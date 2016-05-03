package com.nilhcem.droidconde.scraper

import com.nilhcem.droidconde.scraper.model.Room
import com.nilhcem.droidconde.scraper.model.Session
import com.nilhcem.droidconde.scraper.model.Speaker
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Whitelist
import org.jsoup.select.Elements
import java.util.concurrent.TimeUnit
import kotlin.text.RegexOption.IGNORE_CASE

class Scraper {

    companion object {
        val BASE_URL = "http://droidcon.de"
        val SPEAKERS_URL = "$BASE_URL/en/program/speaker"
        val SLOT_TIME_REGEX = """(\d{2})/(\d{2})/(\d{4}) - (\d{2}):(\d{2}) to (\d{2}):(\d{2})""".toRegex()
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
                    val photo = speakerDoc.select(".image-style-profile-default").attr("src").replace(Regex("\\?itok=.*$"), "")
                    val bio = speakerDoc.select(".views-field-field-profile-vita div").fmtText()
                    val job = speakerDoc.select(".views-field-field-profile-job-title div").fmtText()
                    val company = speakerDoc.select(".views-field-field-profile-organization div a").fmtText()
                    val links = speakerDoc.select(".views-field-field-profile-links a").map { it.attr("href") }
                    val sessions = speakerDoc.select(".view-ncms-speaker-sessions-current a").map { it.attr("href") }

                    val title = getSpeakerTitle(job, company)
                    val twitter = links.filter { it.contains("twitter.com", true) }.firstOrNull()
                    val github = links.filter { it.contains("github.com", true) }.firstOrNull()
                    val website = links.filter { !it.equals(twitter) && !it.equals(github) }.firstOrNull()

                    Speaker(index + 1, name, title, photo, bio, website, twitter, github, sessions)
                }
    }

    private fun getSpeakerTitle(job: String, company: String): String? {
        val sb = StringBuilder()
        if (!job.isEmpty()) {
            sb.append(job)
        }
        if (!company.isEmpty()) {
            if (!sb.isEmpty()) {
                sb.append(" @ ")
            }
            sb.append(company)
        }

        if (sb.isEmpty()) {
            return null
        } else {
            return sb.toString()
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
                    val roomId = Room.getRoomId(sessionDoc.select(".field-name-field-session-room").text())
                    val dateRange = sessionDoc.select(".field-name-field-session-datetime").text()

                    val speakersId = speakers
                            .filter { it.sessions.contains(url) }
                            .map { it.id }
                    val startAt = getStartAt(dateRange)
                    val duration = getDuration(dateRange)

                    Session(index + 1, title, description, speakersId, startAt, duration, roomId)
                }
    }

    private fun getStartAt(dateRange: String): String? {
        val values = SLOT_TIME_REGEX.matchEntire(dateRange)?.groupValues?.subList(1, 6)
        if (values != null && values.size == 5) {
            return "${values[2]}-${values[0]}-${values[1]} ${values[3]}:${values[4]}"
        }
        return null
    }

    private fun getDuration(dateRange: String): Int {
        val values = SLOT_TIME_REGEX.matchEntire(dateRange)?.groupValues?.subList(4, 8)?.map { it.toLong() }
        if (values != null && values.size == 4) {
            return ((TimeUnit.HOURS.toMinutes(values[2]) + values[3]) - (TimeUnit.HOURS.toMinutes(values[0]) + values[1])).toInt()
        }
        return 0
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
        return Jsoup.clean(html(), "", Whitelist.basic(),
                Document.OutputSettings().prettyPrint(false))
                .replace(Regex("&nbsp;", IGNORE_CASE), " ")
                .replace(Regex("&amp;", IGNORE_CASE), "&")
                .replace(Regex("&gt;", IGNORE_CASE), ">").replace(Regex("&lt;", IGNORE_CASE), "<")
                .replace(Regex("<br[\\s/]*>", IGNORE_CASE), "\n")
                .replace(Regex("<p>", IGNORE_CASE), "").replace(Regex("</p>", IGNORE_CASE), "\n")
                .replace(Regex("</?ul>", IGNORE_CASE), "")
                .replace(Regex("<li>", IGNORE_CASE), "• ").replace(Regex("</li>", IGNORE_CASE), "\n")
                .replace(Regex("\n\n• ", IGNORE_CASE), "\n• ")
                .replace(Regex("<a\\s[^>]*>", IGNORE_CASE), "").replace(Regex("</a>", IGNORE_CASE), "")
                .replace(Regex("</?strong>", IGNORE_CASE), "")
                .replace(Regex("</?em>", IGNORE_CASE), "")
                .replace(Regex("\\s*\n\\s*"), "\n").replace(Regex("^\n"), "").replace(Regex("\n$"), "")
    }
}
