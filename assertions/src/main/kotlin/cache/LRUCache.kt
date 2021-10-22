package cache

class LRUCache<K : Any, V : Any>(private val maxCachedElementCount: Int) {
    private val cachedElements: MutableMap<K, Node<K, V>> = mutableMapOf()
    private val removalQueue = RemovalQueue<K, V>()

    operator fun get(key: K): V? {
        val value = if (key in cachedElements) processExistingElement(key).value else null
        checkInvariants()
        return value
    }

    operator fun set(key: K, value: V) {
        if (key in cachedElements) {
            processExistingElement(key).value = value
            return
        }
        if (cachedElements.size >= maxCachedElementCount) {
            val keyToRemove = removalQueue.poll().key
            cachedElements.remove(keyToRemove)
        }
        val node = removalQueue.push(key, value)
        cachedElements[key] = node

        assert(key in cachedElements && cachedElements[key]!!.value === value) { "Element wasn't set" }
        checkInvariants()
    }

    private fun processExistingElement(key: K): Node<K, V> {
        val node = cachedElements[key]!!
        removalQueue.moveToTail(node)
        return node
    }

    private fun checkInvariants() {
        assert(cachedElements.size <= maxCachedElementCount) { "Cache size is greater than given bound" }
    }

}

private class RemovalQueue<K : Any, V : Any> {
    private var tail = Node<K, V>(null, null)
    private var head = Node<K, V>(null, null)

    init {
        head.previous = tail
        tail.next = head
    }

    fun push(key: K, value: V): Node<K, V> {
        val newNode = Node(key, value)
        pushNode(newNode)

        checkInvariants()
        assert(tail.next?.value === value && tail.next?.key === key) { "Element {$key, $value} was not pushed" }

        return newNode
    }


    fun poll(): Node<K, V> {
        return removeNode(head.previous!!).also { checkInvariants() }
    }

    fun moveToTail(node: Node<K, V>) {
        removeNode(node)
        pushNode(node)

        checkInvariants()
        assert(node === tail.next) { "Element {${node.key}, ${node.value}} was not moved to tail" }
    }

    private fun pushNode(node: Node<K, V>) {
        val afterTailNode = tail.next!!
        afterTailNode.previous = node
        node.next = afterTailNode

        tail.next = node
        node.previous = tail

    }

    private fun removeNode(node: Node<K, V>): Node<K, V> {
        assert(tail.next !== head) { "Can't remove node from empty queue" }

        val previousNode = node.previous!!
        val nextNode = node.next!!
        previousNode.next = nextNode
        nextNode.previous = previousNode
        return node
    }

    private fun checkInvariants() {
        assert(tail.previous == null) { "Tail is not last node" }
        assert(head.next == null) { "Head is not first node" }
        assert(tail.key == null && tail.value == null) { "Tail key or value is not null" }
        assert(head.key == null && head.value == null) { "Head key or value is not null" }

        val checkConditionOnNodes = fun(conditionChecker: (Node<K, V>) -> Boolean): Boolean {
            var node = tail
            while (node !== head) {
                if (!conditionChecker(node)) {
                    return false
                }
                node = node.next!!
            }
            return true
        }
        assert(checkConditionOnNodes {
            (it.next != null) && (it.next!!.previous === it)
        })
        { "Links invariant is not correct" }

        assert(checkConditionOnNodes {
            (it === tail) || (it.key != null && it.value != null)
        })
        { "Key or value is null for inner node" }
    }
}

private data class Node<K, V>(val key: K?, var value: V?) {
    var next: Node<K, V>? = null
    var previous: Node<K, V>? = null
}
