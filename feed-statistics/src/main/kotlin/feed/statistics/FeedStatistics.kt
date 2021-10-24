package feed.statistics

interface FeedStatistics {
    suspend fun computeStatistics(hashtag : String, timeIntervalInHours : Int) : List<Int>
}