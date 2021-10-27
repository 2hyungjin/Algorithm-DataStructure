package queue

class Test {
    var a = 0
        set(value) {
            if (value > 2) field = 0 else field = value
        }
}

fun main() {
    val t = Test()
    for (i in 0..10) {
        println(t.a++)
    }
}