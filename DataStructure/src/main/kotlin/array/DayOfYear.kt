package array

fun getDayUntilNow(isLeap: Boolean, month: Int, day: Int): Int {
    val daysOfYear = arrayOf(
        intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31),
        intArrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31))
    val thisYear=if (isLeap) daysOfYear[0] else daysOfYear[1]
    return thisYear.filterIndexed { index, _ -> index < month - 1 }.sum() + day
}
fun main() {
    println(getDayUntilNow(true,3,4))
}