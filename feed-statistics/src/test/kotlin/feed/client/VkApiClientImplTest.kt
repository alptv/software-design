package feed.client

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Test
import java.util.*

class VkApiClientImplTest {
    private val accessToken = "access_token"
    private val version = "version"
    private val method = "methodname"
    private val parameters = TreeMap(mapOf("p1" to "v1", "p2" to "v2", "p3" to "v3"))
    private val url = "https://api.vk.com/method/$method?p1=v1&p2=v2&p3=v3&access_token=$accessToken&v=$version"
    private val json = """{
            "response": {
                "items": [],
                "count": 1,
                "total_count": 1
            }
        }"""
    private val client = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                when (request.url.toString()) {
                    url -> respond(json)
                    else -> respondBadRequest()
                }
            }
        }
    }

    @Test
    fun `should return json which server gives`(): Unit = runBlocking {
        val vkApiClient = VkApiClientImpl(accessToken, version, client)
        val actualJson = vkApiClient.executeMethod(method, parameters)
        assertThat(actualJson).isEqualTo(json)
    }

    @Test
    fun `should throw exception on bad request`() : Unit = runBlocking {
        val vkApiClient = VkApiClientImpl(accessToken, version, client)
        try {
            vkApiClient.executeMethod("incorrect", parameters)
            fail("Should throw VkApiException")
        } catch (e : VkApiException) {
            assertThat(e.message).isEqualTo("Error during http request")
        }
    }

}
