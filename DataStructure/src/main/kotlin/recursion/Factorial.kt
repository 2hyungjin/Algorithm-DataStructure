package recursion

fun factorial(n: Int): Int {
    return if (n > 0) n * factorial(n - 1)
    else 1
}
fun main() {
    println(factorial(3))
}