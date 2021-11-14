package recursion

fun hanoi(n: Int, from: Int = 1, to: Int = 3) {
    var by = 6 - (from + to)
    if (n == 1) println("$n : $from -> $to (1)")
    else {
        hanoi(n - 1, from, by)
        println("$n : $from -> $to (2)")
        hanoi(n - 1, by, to)
    }
}
fun main() {
    hanoi(10)
}