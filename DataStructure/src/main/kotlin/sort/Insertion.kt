package sort

fun sortByInsertion(array: IntArray): IntArray {
    array.forEachIndexed { index, i ->
        var j = index
        while (j > 0 && array[j - 1] > i){
            array[j] = array[j - 1]
            j--
        }
        array[j] = i
    }
    return array
}
fun main() {
    val array= intArrayOf(9,7,5,3,4,2)
    sortByInsertion(array)
    println(array.contentToString())
}