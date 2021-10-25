package feed.client.vk

import feed.client.http.HttpClientWrapper
import feed.client.http.HttpException
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

class VkApiClientImplTest {
    private val accessToken = "access_token"
    private val version = "version"
    private val method = "methodname"
    private val client = mock<HttpClientWrapper>()
    private val parameters = TreeMap(mapOf("p1" to "v1", "p2" to "v2", "p3" to "v3"))
    private val url = "https://api.vk.com/method/$method?p1=v1&p2=v2&p3=v3&access_token=$accessToken&v=$version"
    private val json = """{
            "response": {
                "items": [],
                "count": 1,
                "total_count": 1
            }
        }"""

    @Test
    fun `should return json which server gives`() {
        whenever(client.request(url)).thenReturn(json)

        val vkApiClient = VkApiClientImpl(accessToken, version, client)
        val actualJson = vkApiClient.executeMethod(method, parameters)

        assertThat(actualJson).isEqualTo(json)

        verify(client).request(url)
    }

    @Test
    fun `should throw exception on bad request`() {
        whenever(client.request(url)).thenThrow(HttpException::class.java)

        val vkApiClient = VkApiClientImpl(accessToken, version, client)
        try {
            vkApiClient.executeMethod(method, parameters)
            fail("VkApiException was not thrown")
        } catch (e : Exception) {
            assertThat(e).isInstanceOf(VkApiException::class.java)
            assertThat(e.cause).isInstanceOf(HttpException::class.java)
            assertThat(e.message).isEqualTo("Error in http client during executing method")
        }

        verify(client).request(url)
    }

}