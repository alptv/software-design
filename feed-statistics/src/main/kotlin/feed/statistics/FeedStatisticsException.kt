package feed.statistics

class FeedStatisticsException : RuntimeException {
    constructor(message: String, cause: Exception) : super(message, cause)

    constructor(message: String) : super(message)
}