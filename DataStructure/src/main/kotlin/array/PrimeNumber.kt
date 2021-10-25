package array

fun getPrimeNumbers(target: Int): IntArray {
    var primeArray = intArrayOf(2)
    for (num in 2..target) {
        for(prime in primeArray){
            if (num % prime==0)break
//            if ()
        }
    }
}