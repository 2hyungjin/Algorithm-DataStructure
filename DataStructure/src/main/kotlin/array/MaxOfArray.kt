package array

fun findMax(array: IntArray): Int {
    var max = array[0]
    array.forEach {
        if (it > max) max = it
    }
    return max
}
fun main() {
    println(findMax(intArrayOf(1,3,5,6,7,9,312,7,756,132)))
}