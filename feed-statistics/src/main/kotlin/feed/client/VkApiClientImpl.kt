package feed.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.util.Map.entry


class VkApiClientImpl(
    private val accessToken: String,
    private val version: String,
    private val client: HttpClient = HttpClient(),
) : VkApiClient {

    companion object {
        const val host = "https://api.vk.com"
    }


    override suspend fun executeMethod(method: String, parameters: Map<String, String>): String {
        val url = "$host/method/$method?${joinParameters(parameters)}"
        val response: HttpResponse = client.request(url)
        if (response.status != HttpStatusCode.OK) {
            throw VkApiException("Error during http request")
        }
        return response.receive()

    }

    private fun joinParameters(parameters: Map<String, String>): String {
        return parameters.asSequence()
            .plus(entry("access_token", accessToken))
            .plus(entry("v", version))
            .joinToString(separator = "&") { "${it.key}=${it.value}" }
    }
}
