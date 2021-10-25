package feed.statistics

import feed.client.vk.VkApiClient
import org.json.JSONException
import org.json.JSONObject

class FeedStatisticsImpl(
    private val vkApiClient: VkApiClient,
) : FeedStatistics {

    companion object {
        const val method = "newsfeed.search"
    }

    override fun computeStatistics(hashtag: String, timeIntervalInHours: Int): List<Int> {
        if (timeIntervalInHours > 24 || timeIntervalInHours < 1) {
            throw FeedStatisticsException("Time interval should be in range 1..24")
        }

        val statistic = mutableListOf<Int>()
        var startTime = nowUnix().minusHours(timeIntervalInHours)

        repeat(timeIntervalInHours) {
            val endTime = startTime.plusOneHour()
            val parameters = buildParameters(hashtag, startTime, endTime)
            val json = vkApiClient.executeMethod(method, parameters)
            statistic.add(getPostsCountFromJson(json))
            startTime = endTime
        }
        return statistic
    }


    private fun getPostsCountFromJson(json: String): Int {
        try {
            return JSONObject(json).getJSONObject("response").getInt("total_count")
        } catch (e: JSONException) {
            throw FeedStatisticsException("Invalid response", e)
        }
    }


    private fun buildParameters(hashtag: String, startTime: Long, endTime: Long): Map<String, String> {
        return mapOf(
            "q" to hashtag,
            "start_time" to startTime.toString(),
            "end_time" to endTime.toString(),
            "count" to "0"
        )
    }
}


