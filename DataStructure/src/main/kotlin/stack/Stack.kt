package stack

class IntStack(capacity: Int) {
    var max: Int = capacity
    var ptr: Int = 0
    var stk: IntArray = IntArray(max)

    //실행 시 예외 (스택이 비어 있음)
    class EmptyIntStackException : RuntimeException()

    //실행 시 예외 (스택이 가득 참)
    class OverflowIntStackException : RuntimeException()

    fun push(data: Int): Int {
        if (ptr >= max) throw OverflowIntStackException()
        return data.also { stk[ptr++] = it }
    }

    fun pop(): Int {
        if (ptr <= 0) throw EmptyIntStackException()
        return stk[ptr--]
    }

    fun peak(): Int {
        if (ptr <= 0) throw EmptyIntStackException()
        return stk[ptr - 1]
    }

    fun indexOf(element: Int): Int {
        stk.forEachIndexed { index, i ->
            if (i == element) return index
        }
        return -1
    }

    fun clear() {
        ptr = 0
    }

    fun capacity(): Int = max
    fun size(): Int = ptr
    fun isEmpty(): Boolean = ptr <= 0
    fun isFull(): Boolean = ptr >= max

    fun dump() {
        for (i in 0 until ptr) {
            print("${stk[i]} ")
        }
        println()
    }
}