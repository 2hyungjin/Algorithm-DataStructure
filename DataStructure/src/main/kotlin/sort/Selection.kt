package sort

fun sortBySelection(array: IntArray): IntArray {
    array.forEachIndexed { index, i ->
        var least = i
        for (j in index..array.lastIndex) {
            least = if (array[j] < least) array[j] else least
        }
        swap(array, index, array.indexOf(least))
    }
    return array
}

fun main() {
    val array = intArrayOf(3, 5, 7, 2, 1)
    sortBySelection(array)
    println(array.contentToString())
}