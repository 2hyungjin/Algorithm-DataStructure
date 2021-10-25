package array

fun isEqual(array: IntArray, other:IntArray): Boolean {
    if (array.size != other.size)return false
    for (i in 0..array.lastIndex){
        if (array[i]!=other[i])return false
    }
    return true
}