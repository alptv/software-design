package feed.client.http

class HttpException : RuntimeException {
    constructor(message: String, cause: Exception) : super(message, cause)

    constructor(message: String) : super(message)
}