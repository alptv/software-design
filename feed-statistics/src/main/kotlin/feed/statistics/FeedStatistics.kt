package feed.statistics

interface FeedStatistics {
    fun computeStatistics(hashtag: String, timeIntervalInHours: Int): List<Int>
}