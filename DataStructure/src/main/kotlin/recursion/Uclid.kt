package recursion

fun getGcd(x: Int, y: Int): Int = if (x % y == 0) y else getGcd(y, x % y)
fun main() {
    println(getGcd(8,22))
}