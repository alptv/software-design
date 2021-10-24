package feed.client

interface VkApiClient {
    suspend fun executeMethod(method: String, parameters: Map<String, String>): String
}