package feed.client.vk

import feed.client.http.HttpClientWrapper
import feed.client.http.HttpException
import java.util.Map.entry


class VkApiClientImpl(
    private val accessToken: String,
    private val version: String,
    private val client: HttpClientWrapper,
) : VkApiClient {

    companion object {
        const val host = "https://api.vk.com"
    }


    override fun executeMethod(method: String, parameters: Map<String, String>): String {
        val url = "$host/method/$method?${joinParameters(parameters)}"
        return try {
            client.request(url)
        } catch (e: HttpException) {
            throw VkApiException("Error in http client during executing method", e)
        }
    }

    private fun joinParameters(parameters: Map<String, String>): String {
        return parameters.asSequence()
            .plus(entry("access_token", accessToken))
            .plus(entry("v", version))
            .joinToString(separator = "&") { "${it.key}=${it.value}" }
    }
}
