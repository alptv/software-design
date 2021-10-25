package feed.client.http

interface HttpClientWrapper {
    fun request(url: String): String
}