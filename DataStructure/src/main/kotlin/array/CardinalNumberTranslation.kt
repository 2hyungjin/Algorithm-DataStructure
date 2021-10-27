package array


fun translateCardinalNumber(num: Int, cardinalNumber: Int): String {
    val dchar = "0123456789ABCDEF"
    var num = num
    var result = ""

    do {
        result += dchar[num % cardinalNumber]
        num /= cardinalNumber
    } while (num != 0)
    return result.reversed()
}

fun main() {
    println(translateCardinalNumber(32, 16))
}
