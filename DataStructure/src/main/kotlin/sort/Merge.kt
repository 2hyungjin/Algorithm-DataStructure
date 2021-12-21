package sort

lateinit var sorted: IntArray

fun sortByMerge(array: IntArray) {
    sorted = IntArray(array.size)

    sortByMerge(array, 0, array.lastIndex)
}

private fun sortByMerge(array: IntArray, left: Int, right: Int) {
    if (left < right) {
        val mid = (left + right) / 2
        sortByMerge(array, left, mid)
        sortByMerge(array, mid + 1, right)

        merge(array, left, right)
    }
}

fun merge(array: IntArray, left: Int, right: Int) {
    var mid = (left + right)/2

    var lStart = left
    var rStart = mid + 1

    var sortedIndex = left


    while (lStart <= mid && rStart <= right) {
        sorted[sortedIndex++] = if (array[lStart] < array[rStart]) array[lStart++] else array[rStart++]
    }
    while (lStart <= mid) sorted[sortedIndex++] = array[lStart++]
    while (rStart <= right) sorted[sortedIndex++] = array[rStart++]

    for (i in left..right)array[i]= sorted[i]
}

fun main() {
    val array= intArrayOf(1,3,4,7,2,3,0)
    sortByMerge(array)
    println(array.contentToString())
}