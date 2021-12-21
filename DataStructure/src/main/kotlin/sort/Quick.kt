package sort

fun sortByQuick(array: IntArray, start: Int = 0, end: Int = array.lastIndex): IntArray {
    val part = partition(array, start, end)

    if (start < part - 1) sortByQuick(array, start, part - 1)

    if (part < end) sortByQuick(array, part, end)
    return array
}

fun partition(array: IntArray, start: Int, end: Int): Int {
    var l = start
    var r = end

    var pivot = array[(l + r) / 2]
    while (l <= r) {
        while (array[l] < pivot) l++
        while (array[r] > pivot) r--
        if (l <= r) swap(array, l++, r--)
    }
    return l
}
fun main() {
    val array= intArrayOf(1,3,5,4,7,9,2,0)
    sortByQuick(array)
    println(array.contentToString())
}

