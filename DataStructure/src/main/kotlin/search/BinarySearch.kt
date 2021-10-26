package search

fun binarySearch(sortedArray: IntArray, key: Int): Int {
    var first = 0
    var last = sortedArray.lastIndex
    var mid = first + last / 2
    do {
        if (sortedArray[mid] == key) return mid
        if (sortedArray[mid] > key) last = mid - 1
        if (sortedArray[mid] < key) first = mid + 1
    } while (first <= last)
    return -1
}
fun main() {
    

}