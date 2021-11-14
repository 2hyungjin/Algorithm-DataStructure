package recursion

fun fibonacci(n: Int): Int = if (n == 1 || n == 2) 1 else fibonacci(n - 1) + fibonacci(n - 2)

tailrec fun fibonacci(n: Int, first: Int = 1, second: Int = 1): Int {
    return if (n == 2)second  else fibonacci(n - 1, second, first + second)
}

fun main() {
    println(fibonacci(10))
    println(fibonacci(10,1,1))
}