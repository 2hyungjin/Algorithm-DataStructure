fun main() {
    val array1: IntArray = IntArray(5) { it } //IntArray 생성자
    val array2 = Array(5) { it } //Array 생성자

    array1.size //5

    val array3 = IntArray(5)
    for (i in array3) println(i)

    val array4 = intArrayOf(1, 2, 3) //팩토리 함수
    val array5 = arrayOf(1, 2, 3)


}