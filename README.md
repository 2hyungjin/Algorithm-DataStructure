# 코틀린 자료구조 & 알고리즘

Do it 자료구조와 함께 배우는 알고리즘 입문 자바편 코틀린으로 리팩토링하기

---

자료구조 : 데이터 단위와 데이터 자체 사이의 물리적 또는 논리적 관계

## 복잡도

알고리즘의 성능을 객관적으로 평가하는 기준이며 시간 복잡도와 공간 복잡도가 있다.

- 시간 복잡도 : 실행에 필요한 시간을 평가한 것

- 공간 복잡도 : 기억 영역과 파일 공간이 얼마나 필요한가를 평가한 것

## 배열

```kotlin
val array1: IntArray = IntArray(5) { it } //IntArray 생성자
val array2 = Array(5) { it } //Array 생성자
```

배열은 같은 자료형의 변수로 이루어진 구성요소이다.

코틀린에서 그냥 array는 다양한 자료형이 들어갈 수 있다.

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

```kotlin
fun getPrimeNumbers(target: Int): IntArray {
    var primeArray = intArrayOf()
    for (num in 2..target) {
        for (i in 2..num) {
            if (i == num) primeArray += num
            if (num % i == 0) break
        }
    }
    return primeArray
}
```

어떤 수 N은 2부터 N-1까지 어떤 수로도 나누어 떨어지지 않으면 소수이다.

```kotlin
fun getPrimeNumbers(target: Int): IntArray {
    var primeArray = intArrayOf(2)
    for (num in 2..target) {
        for (i in primeArray.indices) {
            if (num % primeArray[i] == 0) break
            if (i == primeArray.lastIndex) primeArray += num
        }
    }
    return primeArray
}
```

(개선1) 어떤 수 N은 N-1까지 어떤 소수로도 나누어 떨어지지 않으면 소수이다.

```kotlin
fun getPrimeNumbers(target: Int): IntArray {
    var primeArray = intArrayOf(2)
    for (num in 3..target step 2) { //소수는 2를 제외하면 모두 홀수이므로 홀수만 검사
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
```

(개선2) 어떤 수 N은 N의 제곱근까지의 어떤 소수로도 나누어 떨어지지 않으면 소수이다.

### 경과 일 수 구하기

```kotlin
fun getDayUntilNow(isLeap: Boolean, month: Int, day: Int): Int {
    val daysOfYear = arrayOf(
        intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31),
        intArrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31))
    val thisYear=if (isLeap) daysOfYear[0] else daysOfYear[1]
    return thisYear.filterIndexed { index, _ -> index < month - 1 }.sum() + day
}
```

다차원 배열로 각 일 수를 윤년, 평년 별로 저장한다.

n-1개월까지 월 수 더한 뒤, 일 수를 더한 후 반환한다.

## 검색

### 선형 검색

```kotlin
fun linearSearch(array: IntArray, key: Int): Int {
    array.forEachIndexed { index, num ->
        if (num == key) return index
    }
    return -1
}
```

선형 구조에서 앞에서 차례로 요소를 검색하는 방법이다.

검색 종료 조건은 다음과 같다.

- 원하는 값을 찾았을 때
- 원하는 값을 발견하지 못한 채, 끝까지 검색했을 때

#### 복잡도

평균 탐색 횟수는 n/2이므로 복잡도는 O(n)이다.

#### 보초법

```kotlin
fun linearSearchSentinel(array: IntArray, key: Int): Int {
    var array = array
    array += key
    var index = 0
    while (true) {
        if (array[index] == key) break
        index++
    }
    return if (index == array.lastIndex) -1 else index
}
```

보초법을 사용하면 종료 판단 횟수를 2회에서 1회로 줄인다.

보초법은 검색할 배열의 길이를 하나 늘려 원하는 키 값을 저장한다.

검색 종료 조건은 다음과 같아진다.

- 원하는 값을 찾았을 때 (추가된 인덱스이면  -1반환)

### 이진 검색

```kotlin
fun binarySearch(sortedArray: IntArray, key: Int): Int {
    var first = 0
    var last = sortedArray.lastIndex
    var mid = first + last / 2
    do {
        if (sortedArray[mid] == key) return mid
        if (sortedArray[mid] > key) last = mid - 1
        if (sortedArray[mid] < key) first = mid + 1
    } while (first <= last)
    return -1
}
```

이진 검색은 요소가 오름 또는 내림차순으로 정렬된 선형 구조에서 요소를 검색하는 방법이다.

이진 검색은 다음과 같은 순서로 진행된다.

1. 배열의 처음 인덱스를 first, 마지막 인덱스를 last, first+last/2를 mid로 둔다.

2. 배열의 mid 값과 키 값을 비교하여 같다면 반환한다.
3. 키 값보다 배열의 mid 값이 작다면 mid 값보다 큰 값만 검색하면 되므로 배열의 first를 mid+1, 크다면 작은 값만 검색하면 되므로 last를 mid-1로 바꾼다.
4. 1~3 과정을 반복하다 first가 last보다 커지면 검색을 그만둔다. (검색 실패)

#### 복잡도

O(log n)

### 표준 라이브러리 사용하기

```kotlin
public fun IntArray.binarySearch(element: Int, fromIndex: Int = 0, toIndex: Int = size): Int

intArrayOf(1,5,6).binarySearch(5)
```

코틀린에는 Array.binarySearch() 확장 함수가 있다.

이는 내부적으로 java.util.Arrays.binarySearch()를 호출하며 검색에 성공할 경우 인덱스 (여러 개라면 무작위의 인덱스)를 반환하고

-실패한 경우 key보다 큰 요소 중 첫번째 + -1을 반환합니다. (모두 key보다 작다면 -배열의 길이 -1)

## 스택과 큐

