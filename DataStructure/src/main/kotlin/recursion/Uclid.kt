package recursion

fun getGcd(x: Int, y: Int): Int = if (x % y == 0) y else getGcd(y, x % y)
fun getLcm(x: Int, y: Int) = x * y / getGcd(x, y)