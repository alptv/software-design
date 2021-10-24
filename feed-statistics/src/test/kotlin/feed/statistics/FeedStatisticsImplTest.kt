package feed.statistics

import feed.client.VkApiClient
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.*
import java.util.*

class FeedStatisticsImplTest {
    private val vkApiClient: VkApiClient = mock()
    private val hashtag = "hashtag"
    private val json = """{
            "response": {
                "items": [],
                "count": 1,
                "total_count": 10
            }
        }"""

    @Test
    fun `should get correct statistics for 1 hour`(): Unit = runBlocking {
        whenever(vkApiClient.executeMethod(any(), any())).thenReturn(json)
        val statistics = FeedStatisticsImpl(vkApiClient).computeStatistics(hashtag, 1)
        assertThat(statistics).isEqualTo(listOf(10))
        verify(vkApiClient).executeMethod(any(), any())
    }

    @Test
    fun `should get correct statistics for multiple hours`(): Unit = runBlocking {
        whenever(vkApiClient.executeMethod(any(), any())).thenReturn(json)
        val statistics = FeedStatisticsImpl(vkApiClient).computeStatistics(hashtag, 10)
        val expectedStatistics = (Array(10) { 10 }).toList()
        assertThat(statistics).isEqualTo(expectedStatistics)
        verify(vkApiClient, times(10)).executeMethod(any(), any())
    }

    @Test
    fun `should throw exception with incorrect json`(): Unit = runBlocking {
        val incorrectJson = """{
            "incorrect": {
                "a": 123,
                "b": 5,
                "c": []
            }
        }"""
        whenever(vkApiClient.executeMethod(any(), any())).thenReturn(incorrectJson)
        try {
            FeedStatisticsImpl(vkApiClient).computeStatistics(hashtag, 24)
            Assertions.fail("Should throw FeedStatisticsException")
        } catch (e : FeedStatisticsException) {
            assertThat(e.message).isEqualTo("Invalid response")
        }
        verify(vkApiClient).executeMethod(any(), any())
    }


}