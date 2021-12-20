package sort

fun swap(array: IntArray, from: Int, to: Int) {
    val temp = array[from]
    array[from] = array[to]
    array[to] = temp
}

fun swapToDebug(array: IntArray, from: Int, to: Int){
    println("${array[from]}($from) -> ${array[to]}($to)")
    val temp = array[from]
    array[from] = array[to]
    array[to] = temp
}