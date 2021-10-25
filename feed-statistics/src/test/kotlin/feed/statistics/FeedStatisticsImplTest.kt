package feed.statistics

import feed.client.vk.VkApiClient
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import org.mockito.kotlin.*

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
    fun `should get correct statistics for 1 hour`() {
        whenever(vkApiClient.executeMethod(any(), any())).thenReturn(json)
        val statistics = FeedStatisticsImpl(vkApiClient).computeStatistics(hashtag, 1)
        assertThat(statistics).isEqualTo(listOf(10))
        verify(vkApiClient).executeMethod(any(), any())
    }

    @Test
    fun `should get correct statistics for multiple hours`() {
        whenever(vkApiClient.executeMethod(any(), any())).thenReturn(json)
        val statistics = FeedStatisticsImpl(vkApiClient).computeStatistics(hashtag, 10)
        val expectedStatistics = (Array(10) { 10 }).toList()
        assertThat(statistics).isEqualTo(expectedStatistics)
        verify(vkApiClient, times(10)).executeMethod(any(), any())
    }

    @Test
    fun `should throw exception on time interval less than 1`() {
        assertThatThrownBy {
            FeedStatisticsImpl(vkApiClient).computeStatistics(hashtag, 0)
        }.isInstanceOf(FeedStatisticsException::class.java).hasMessage("Time interval should be in range 1..24")
    }

    @Test
    fun `should throw exception on time interval greater than 24`() {
        assertThatThrownBy {
            FeedStatisticsImpl(vkApiClient).computeStatistics(hashtag, 25)
        }.isInstanceOf(FeedStatisticsException::class.java).hasMessage("Time interval should be in range 1..24")
    }


    @Test
    fun `should throw exception with incorrect json`() {
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
        } catch (e: FeedStatisticsException) {
            assertThat(e.message).isEqualTo("Invalid response")
        }
        verify(vkApiClient).executeMethod(any(), any())
    }


}