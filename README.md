# 코틀린 자료구조 & 알고리즘

Do it 자료구조와 함께 배우는 알고리즘 입문 자바편 코틀린으로 리팩토링하기

---

자료구조 : 데이터 단위와 데이터 자체 사이의 물리적 또는 논리적 관계

## 배열

```kotlin
val array1: IntArray = IntArray(5) { it } //IntArray 생성자
val array2 = Array(5) { it } //Array 생성자
```

배열은 같은 자료형의 변수로 이루어진 구성요소이다.

코틀린에는 원시 타입 + Array의 자료형이 존재한다. 하지만 Array<자료형>과 연관 관계는 없다.

Array의 생성자는 size, init(람다)이다.

배열의 선언의 의미는 해당 자료형의 배열 본체를 생성하고 그것을 변수가 참조하도록 설정한다는 뜻이다.

배열 안의 모든 고성 요소는 자료형이 같으며 직선 모양으로 줄지어 있다.

배열의 개별 요소에 접근하기 위해서는 연산자 []안에 정수형 인덱스를 넣는다.

```kotlin
array1.size //5
```

배열은 자신의 구성 요소의 개수를 나타내는 size라는 변수가 있다.

```kotlin
val array3 = IntArray(5)
```

IntArray의 기본 값은 0이다.

```kotlin
val array4 = intArrayOf(1, 2, 3) //팩토리 함수
val array5 = arrayOf(1, 2, 3)
```

코틀린에는 배열을 반환하는 팩토리 함수가 있어서 생성과 초기화를 한 번에 할 수 있다.

### 최댓값 구하기

```kotlin
fun findMax(array: IntArray): Int {
    var max = array[0]
    array.forEach {
        if (it > max) max = it
    }
    return max
}
//array.maxOrNull()
```

배열의 첫 번째 값을 max로 둔 뒤 배열의 요솟수 N-1까지 순회하며 max보다 크다면 max값을 바꾼다.

코틀린의 확장 함수 array.maxOrNull() 메소드가 이런 방식으로 구현되어 있다.

### 역순 정렬

```kotlin
fun reverseArray(array: IntArray): IntArray {
    for (i in 0 until array.size / 2) {
        val temp = array[i]
        array[i] = array[array.lastIndex - i]
        array[array.lastIndex-1]=temp
    }
    return array
}
```

배열의 순서를 뒤바꾼다. 이 때 교환 횟수는 요솟수/2이다.

코틀린의 확장 함수 array.reversedArray()가 이와 같은 방식으로 구현되어 있다.

### 두 배열의 비교

```kotlin
fun isEqual(array: IntArray, other:IntArray): Boolean {
    if (array.size != other.size)return false
    for (i in 0..array.lastIndex){
        if (array[i]!=other[i])return false
    }
    return true
}
```

두 배열의 길이를 비교하고 요소를 비교하여 같은지 반환한다.

### 진수 바꾸기

```kotlin
fun translateCardinalNumber(num: Int, cardinalNumber: Int): String {
    val dchar = "0123456789ABCDEF"
    var num = num
    var result = ""

    do {
        result += dchar[num % cardinalNumber]
        num /= cardinalNumber
    } while (num != 0)
    return result.reversed()
}
```

값과 원하는 진수를 받아서 해당 진수로 나눈 나머지 값을 미리 정의해둔 문자열 표에서 가져와 추가한다.

값이 해당 진수로 나누고 값이 0이 될 때까지 반복한다.

### 소수의 나열

어떤 수 N은 N-1까지 아무 소수로도 나누어떨어지지 않으면 소수이다.

