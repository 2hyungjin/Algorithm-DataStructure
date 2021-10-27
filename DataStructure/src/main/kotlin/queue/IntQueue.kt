package queue

class IntQueue(capacity: Int) {
    val max = capacity
    var front = 0
    var rear = 0
    var num = 0
    val que = IntArray(capacity)

    class EmptyIntQueueException : RuntimeException()
    class OverflowIntQueueException : RuntimeException()

    fun enqueue(data: Int): Int {
        if (num >= max) throw OverflowIntQueueException()
        que[rear] = data
        num++
        rear = (rear + 1) % max

        return data
    }

    fun dequeue(): Int {
        if (num <= 0) throw EmptyIntQueueException()
        val data = que[front++]
        num--
        front = (front + 1) % max
        return data
    }

    fun peek(): Int {
        if (num <= 0) throw EmptyIntQueueException()
        return que[rear]
    }

    fun indexOf(element: Int): Int {
        for (i in 0..num) {
            val index = (i + front) % max
        }
        return -1
    }

    fun clear() {
        num = 0
        front = 0
        rear = 0
    }

    fun capacity() = max
    fun size() = num
    fun isEmpty() = num <= 0
    fun isFull() = num >= max

    fun dump() {
        if (num <= 0) println("큐가 비어있음")
        else {
            for (i in 0 until num) {
                print("${que[(i + front) % max]} ")
            }
            println()
        }
    }
}

fun main() {
    val q = IntQueue(3)
    q.enqueue(1)
    q.enqueue(2)
    q.enqueue(3)
    q.dump()
    q.dequeue()
    q.dequeue()
    q.dump()
}
