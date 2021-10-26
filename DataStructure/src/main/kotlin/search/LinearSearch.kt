package search

fun linearSearch(array: IntArray, key: Int): Int {
    array.forEachIndexed { index, num ->
        if (num == key) return index
    }
    return -1
}

fun linearSearchSentinel(array: IntArray, key: Int): Int {
    var array = array
    array += key
    var index = 0
    while (true) {
        if (array[index] == key) break
        index++
    }
    return if (index == array.lastIndex) -1 else index
}
fun main() {
    println(linearSearchSentinel(intArrayOf(1,3,4,5),6))
}