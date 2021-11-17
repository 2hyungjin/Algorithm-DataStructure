package sort

fun sortByBubble(array: IntArray): IntArray {
    var cnt = 0
    for (i in array.indices) {
        for (j in 0 until array.lastIndex - i) {
            cnt++
            if (array[j] >= array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
        }
    }
    println("비교 횟수 $cnt")
    return array
}

fun sortByBubbleV2(array: IntArray): IntArray {
    var cnt = 0
    for (i in array.indices) {
        var flag = false
        for (j in 0 until array.lastIndex - i) {
            cnt++
            if (array[j] >= array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp

                flag = true
            }
        }
        if (!flag) break
    }
    println("비교 횟수 $cnt")
    return array
}

fun main() {
    println(sortByBubbleV2(intArrayOf(1, 3, 6, 4, 7, 8, 9)).contentToString())
}