package recursion

fun getEightQueen(column: Int = 8) {
    var answer = 0


    fun setQueen(n: Int, FLAG_COL: BooleanArray, FLAG_CROSS_LEFT: BooleanArray, FLAG_CROSS_RIGHT: BooleanArray) {
        for (i in 0 until column) {
            if (FLAG_COL[i] && FLAG_CROSS_LEFT[i + n] && FLAG_CROSS_RIGHT[ n - i + 7]) {

                FLAG_COL[i] = false
                FLAG_CROSS_LEFT[i + n] = false
                FLAG_CROSS_RIGHT[ n - i + 7] = false

                if (n == 7) answer++
                else setQueen(n + 1, FLAG_COL, FLAG_CROSS_LEFT, FLAG_CROSS_RIGHT)

                FLAG_COL[i] = true
                FLAG_CROSS_LEFT[i + n] = true
                FLAG_CROSS_RIGHT[ n - i + 7] = true
            }
        }

    }

    setQueen(0, BooleanArray(8) { true }, BooleanArray(15) { true }, BooleanArray(15) { true })
    println(answer)

}


fun main() {
    getEightQueen()
}

