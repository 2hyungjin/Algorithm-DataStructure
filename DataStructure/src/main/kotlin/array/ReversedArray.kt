package array

fun reverseArray(array: IntArray): IntArray {
    for (i in 0 until array.size / 2) {
        val temp = array[i]
        array[i] = array[array.lastIndex - i]
        array[array.lastIndex - i] = temp
    }
    return array
}

fun main() {
    reverseArray(intArrayOf(1, 2, 3, 4, 5)).forEach { println(it) }

}
