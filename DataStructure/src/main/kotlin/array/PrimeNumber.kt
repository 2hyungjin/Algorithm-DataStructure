package array

fun getPrimeNumbers(target: Int): IntArray {
    var primeArray = intArrayOf(2)
    for (num in 3..target step 2) {
        for (i in primeArray) {
            if (num % i == 0) break
            if (i*i >= num){
                primeArray+=num
                break
            }
        }
    }
    return primeArray
}

fun main() {
    val result = getPrimeNumbers(30)

}