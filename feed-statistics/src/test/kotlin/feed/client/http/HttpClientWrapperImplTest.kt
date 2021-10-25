package feed.client.http

import com.xebialabs.restito.builder.stub.StubHttp.whenHttp
import com.xebialabs.restito.semantics.Action.status
import com.xebialabs.restito.semantics.Action.stringContent
import com.xebialabs.restito.server.StubServer
import org.assertj.core.api.Assertions.*
import org.glassfish.grizzly.http.util.HttpStatus
import org.junit.Test

class HttpClientWrapperImplTest {
    private val port = 8080
    private val host = "http://localhost:"
    private val url = "$host$port/data"
    private val json = """{
            "response": {
                "items": [],
                "count": 1,
                "total_count": 1
            }
        }"""

    @Test
    fun `should return data which server gives`() {
        val client = HttpClientWrapperImpl()

        withStubServer {
            whenHttp(this).match().then(stringContent(json))

            val actualJson = client.request(url)
            assertThat(actualJson).isEqualTo(json)

        }
    }

    @Test
    fun `should throw exception on not 200 status code`() {
        val client = HttpClientWrapperImpl()

        withStubServer {
            whenHttp(this).match().then(status(HttpStatus.BAD_REQUEST_400))

            try {
                client.request(url)
                fail("Http exception was not thrown")
            } catch (e : HttpException) {
                assertThat(e.message).isEqualTo("Http status code is not OK")
            }
        }
    }


    private fun withStubServer(callback: StubServer.() -> Unit) {
        var stubServer: StubServer? = null
        try {
            stubServer = StubServer(port).run()
            callback(stubServer)
        } finally {
            stubServer?.stop()
        }
    }
}