package tree

import kotlin.math.min

class MinHeap {
    private var heapArray: IntArray = intArrayOf(Int.MIN_VALUE)
    var size: Int = 0
    val ROOT = 1

    fun parent(index: Int) = index / 2
    fun left(index: Int) = index * 2
    fun right(index: Int) = index * 2 + 1

    fun swap(from: Int, to: Int) {
        val temp = heapArray[from]
        heapArray[from] = heapArray[to]
        heapArray[to] = temp
    }

    fun insert(data: Int) {
        heapArray += data
        var current = ++size

        while (heapArray[current] < heapArray[parent(current)]) {
            swap(current, parent(current))
            current = parent(current)
        }
    }

    fun delete(): Int {
        if (size <= 0) return 0

        val root = heapArray[ROOT]

        heapArray[ROOT] = heapArray[size--]

        var pos = ROOT

        while (left(pos) <= size) {
            val min = if (heapArray[left(pos)] < heapArray[right(pos)]) left(pos) else right(pos)
            swap(min, pos)
            pos = min
        }
        return root
    }

    fun print(){
        for (i in 1..size){
            println(heapArray[i])
        }
    }

}

fun main() {
    val minHeap=MinHeap()
    minHeap.insert(2)
    minHeap.insert(3)
    minHeap.insert(5)
    minHeap.insert(4)
    minHeap.insert(1)
    minHeap.delete()
    minHeap.print()
}