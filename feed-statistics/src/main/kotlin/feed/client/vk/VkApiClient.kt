package feed.client.vk

interface VkApiClient {
    fun executeMethod(method: String, parameters: Map<String, String>): String
}