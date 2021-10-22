package cache

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LRUCacheTest {

    @Test
    fun `should store 1 element`() {
        val cache = LRUCache<Int, Int>(1)
        cache[0] = 0
        assertThat(cache[0]).isEqualTo(0)
    }

    @Test
    fun `should store last 1 element`() {
        val cache = LRUCache<Int, Int>(1)
        cache[0] = 0
        cache[1] = 1
        cache[2] = 2
        assertThat(cache[2]).isEqualTo(2)
    }

    @Test
    fun `should store last multiple elements`() {
        val cache = LRUCache<Int, Int>(3)
        cache[0] = 0
        cache[1] = 1
        cache[2] = 2
        cache[3] = 3
        cache[4] = 4
        cache[5] = 5
        assertThat(cache[3]).isEqualTo(3)
        assertThat(cache[4]).isEqualTo(4)
        assertThat(cache[5]).isEqualTo(5)
    }

    @Test
    fun `should not cache deleted items`() {
        val cache = LRUCache<Int, Int>(3)
        cache[0] = 0
        cache[1] = 1
        cache[2] = 2
        cache[3] = 3
        cache[4] = 4
        cache[5] = 5
        assertThat(cache[0]).isNull()
        assertThat(cache[1]).isNull()
        assertThat(cache[2]).isNull()
    }

    @Test
    fun `should rewrite elements`() {
        val cache = LRUCache<Int, Int>(10)
        cache[0] = 0
        cache[1] = 1
        cache[2] = 2
        cache[0] = 3
        cache[1] = 4
        cache[2] = 5

        assertThat(cache)
        assertThat(cache[0]).isEqualTo(3)
        assertThat(cache[1]).isEqualTo(4)
        assertThat(cache[2]).isEqualTo(5)
    }

    @Test
    fun `should update remove order after get`() {
        val cache = LRUCache<Int, Int>(3)
        cache[0] = 0
        cache[1] = 1
        cache[2] = 2
        cache[0]
        cache[1]
        cache[3] = 3
        assertThat(cache[0]).isEqualTo(0)
        assertThat(cache[1]).isEqualTo(1)
        assertThat(cache[2]).isNull()
    }

}