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

## 스택

```kotlin
class IntStack(capacity: Int) {
  	//용량
    var max: Int = capacity
		//top
  	var ptr: Int = 0
  	//stack
    var stk: IntArray = IntArray(max)

    //실행 시 예외 (스택이 비어 있음)
    class EmptyIntStackException : RuntimeException()

    //실행 시 예외 (스택이 가득 참)
    class OverflowIntStackException : RuntimeException()

    fun push(data: Int): Int {
        if (ptr >= max) throw OverflowIntStackException()
        return data.also { stk[ptr++] = it }
    }

    fun pop(): Int {
        if (ptr <= 0) throw EmptyIntStackException()
        return stk[ptr--]
    }

    fun peak(): Int { 
        if (ptr <= 0) throw EmptyIntStackException()
        return stk[ptr - 1]
    }

    fun indexOf(element: Int): Int {
        stk.forEachIndexed { index, i ->
            if (i == element) return index
        }
        return -1
    }

    fun clear() {
        ptr = 0
    }

    fun capacity(): Int = max
    fun size(): Int = ptr
    fun isEmpty(): Boolean = ptr <= 0
    fun isFull(): Boolean = ptr >= max

    fun dump() {
        for (i in 0 until ptr) {
            print("${stk[i]} ")
        }
        println()
    }
}
```

스택은 가장 나중에 넣은 데이터를 가장 먼저 꺼내는 후입선출(Last In First Out)의 방식으로 데이터의 입출력이 일어난다.

스택에 데이터를 넣는 작업을 push, 꺼내는 작업을 pop이라고 한다.

스택의 가장 윗 부분을 top, 아랫 부분을 bottom이라고 한다.

자바 프로그램에서 메소드가 호출될 때 내부적으로 스택 방식을 사용한다.

## 큐

```kotlin
class IntQueue(capacity: Int) {
    val max = capacity
    var front = 0
    var rear = 0
    var num = 0
    val que = IntArray(capacity)

    class EmptyIntQueueException : RuntimeException()
    class OverflowIntQueueException : RuntimeException()

    fun enqueue(data: Int): Int {
        if (num >= max) throw OverflowIntQueueException()
        que[rear] = data
        num++
        rear = (rear + 1) % max

        return data
    }

    fun dequeue(): Int {
        if (num <= 0) throw EmptyIntQueueException()
        val data = que[front++]
        num--
        front = (front + 1) % max
        return data
    }

    fun peek(): Int {
        if (num <= 0) throw EmptyIntQueueException()
        return que[rear]
    }

    fun indexOf(element: Int): Int {
        for (i in 0..num) {
            val index = (i + front) % max
        }
        return -1
    }

    fun clear() {
        num = 0
        front = 0
        rear = 0
    }

    fun capacity() = max
    fun size() = num
    fun isEmpty() = num <= 0
    fun isFull() = num >= max

    fun dump() {
        if (num <= 0) println("큐가 비어있음")
        else {
            for (i in 0 until num) {
                print("${que[(i + front) % max]} ")
            }
            println()
        }
    }
}
```

큐는 가장 먼저 넣은 데이터를 가장 먼저 꺼내는 선입선출(First In First Out)의 방식으로 데이터의 입출력이 일어난다.

큐에 데이터를 넣는 작업을 enqueue, 꺼내는 작업을 dequeue라고 한다.

데이터를 꺼내는 쪽을 front, 넣는 쪽을 rear라고 한다.

데이터를 꺼낼 때마다 배열 요소를 앞으로 옮겨야 하는 작업이 귀찮으므로 링 버퍼를 사용한다.

링 버퍼는 배열의 처음과 끝이 연결되었다고 보는 자료구조이다.

링 버퍼를 활용한 큐에서는 front와 rear가 같고 현재 데이터 양이 최대 데이터 양과 같으면 큐가 가득 찬 상태이다.

## 재귀

재귀 함수는 함수 내부에서 같은 함수를 호출하는 것이다.

해당 함수에서 다시 호출하는 것을 직접 재귀, 다른 함수를 거쳐 호출하는 것을 간접 재귀라고 한다.

### 팩토리얼

```kotlin
fun factorial(n: Int): Int {
    return if (n > 0) n * factorial(n - 1)
    else 1
}
```

!3 = 3 * !2 = 3 * !(3-1)이므로 n-1을 인자로 factorial 함수를 재귀 호출한다.

### 유클리드 호제법

```kotlin
fun getGcd(x: Int, y: Int): Int = if (x % y == 0) y else getGcd(y, x % y)
```

큰 값을 작은 값으로 나누었을 때 나누어 떨어지는 가장 작은 값 최대 공약수이다.

나누어지지 않는다면 작은 값에 대해 나누어떨어질 때까지 같은 과정을 반복한다.

### 꼬리 재귀

```kotlin
fun fibonacci(n: Int): Int = if (n == 1 || n == 2) 1 else fibonacci(n - 1) + fibonacci(n - 2)

tailrec fun fibonacci(n: Int, first: Int = 1, second: Int = 1): Int {
    return if (n == 2)second  else fibonacci(n - 1, second, first + second)
}
```

꼬리 재귀는 재귀 호출의 결과에 연산을 하지 않고 결과만 반환하는 방법이다.

코틀린 컴파일러에서는 tailrec 키워드를 사용하여 꼬리 재귀 함수를 최적화할 수 있다. 

재귀 함수를 반복문으로 바꾸어주기 때문에 재귀 함수의 스택 오버플로우 문제를 해결할 수 있다.

### 하노이 탑

```kotlin
fun hanoi(n: Int, from: Int = 1, to: Int = 3) {
    var by = 6 - (from + to)
    if (n == 1) println("$n : $from -> $to")
    else {
        hanoi(n - 1, from, by)
        println("$n : $from -> $to")
        hanoi(n - 1, by, to)
    }
}
```

n개의 원판을 from에서 to로 옮기는 방법 

1. n - 1개를 중간 단계의 막대기로 옮김
2. n 번째 원반을 to로 옮김
3. 중간 단계의  n - 1을 to로 옮김

### 8퀸

```kotlin
fun getEightQueen(column: Int = 8) {
    var answer = 0


    fun setQueen(n: Int, FLAG_COL: BooleanArray, FLAG_CROSS_LEFT: BooleanArray, FLAG_CROSS_RIGHT: BooleanArray) 		{
        for (i in 0 until column) {
            if (FLAG_COL[i] && FLAG_CROSS_LEFT[i + n] && FLAG_CROSS_RIGHT[n - i + 7]) {

                FLAG_COL[i] = false
                FLAG_CROSS_LEFT[i + n] = false
                FLAG_CROSS_RIGHT[n - i + 7] = false

                if (n == 7) answer++
                else setQueen(n + 1, FLAG_COL, FLAG_CROSS_LEFT, FLAG_CROSS_RIGHT)

                FLAG_COL[i] = true
                FLAG_CROSS_LEFT[i + n] = true
                FLAG_CROSS_RIGHT[n - i + 7] = true
            }
        }

    }

    setQueen(0, BooleanArray(8) { true }, BooleanArray(15) { true }, BooleanArray(15) { true })
    println(answer)

}
```

8개의 퀸을 서로 죽이지 않고 놓는 방법

1. 첫 번째 행부터 마지막 행까지 퀸을 놓음
2. 첫 번째 열부터 8번쨰 열까지 놓는 과정을 모두 재귀적으로 호출함
3. 해당 열에 퀸이 없거나(FLAG_COL), 왼쪽 대각선에 퀸이 없거나(FLAG_CROSS_LEFT), 오른쪽 대각선에 퀸이 없을 경우(FLAG_CROSS_RIGHT) 그 칸에만 퀸을 놓을 수 있음
4. 퀸을 놓으면 해당 열(i), 왼쪽 대각선(i+n), 오른쪽 대각선(n-i+7)의 FLAG를 false로 만듦 (해당 열, 대각선에 포함된 칸은 퀸을 놓지 못함)
5. 호출이 끝나면 다시 true로 바꿈
6. 7번째 칸을 놓게 될 경우 답을 하나 증가시킴

## 정렬

key의 대소 관계에 따라 데이터 집합을 일정한 순서로 줄지어 늘어서도록 바꾸는 작업

안정된 정렬 : 같은 값의 키를 가진 요소 순서가 정렬 전후에도 유지됨

불안정한 정렬 : 같은 키를 가져도 요소 순서의 순서가 확신되지 않음

내부 정렬 : 정렬할 모든 데이터를 하나의 배열에 저장할 수 있는 경우 사용

외부 정렬 : 정렬할 데이터가 너무 많아 하나의 배열에 저장할 수 없는 경우 사용

### Bubble Sort

```kotlin
fun sortByBubble(array: IntArray): IntArray {
    for (i in array.indices) {
        for (j in  0 until array.lastIndex - i)
            if (array[j] >= array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
    }
    return array
}
```

이웃한 두 요소의 대소 관계를 비교하여 다음 인덱스의 수보다 현재 수가 더 크면 두 수를 교환

n개의 배열의 경우 처음은 n-1개의 수를 비교하여 배열의 가장 큰 수를 맨 끝으로 이동

다음 부터는 n-2..n-3..개의 수를 비교하며 배열을 오름차순으로 정렬

#### 개선

```kotlin
fun sortByBubbleV2(array: IntArray): IntArray {
    var cnt = 0
    for (i in array.indices) {
        var flag = false
        for (j in 0 until array.lastIndex - i) {
            cnt++
            if (array[j] >= array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp

                flag = true
            }
        }
        if (!flag) break
    }
    println("비교 횟수 $cnt")
    return array
}
```

교환이 한 번도 실행되지 않았을 경우, 이미 정렬된 배열이므로 비교를 멈춤.

### Selection Sort

```kotlin
fun sortBySelection(array: IntArray): IntArray {
    array.forEachIndexed { index, i ->
        var least = i
        for (j in index..array.lastIndex) {
            least = if (array[j] < least) array[j] else least
        }
        swap(array, index, array.indexOf(least))
    }
    return array
}
```

수를 선택하여 적절한 인덱스로 옮긴다.

오름차순일 경우 최솟값을 0번 인덱스로, 그 다음 작은 수를 1번 인덱스로 옮기는 방법

### Insertion Sort

```kotlin
fun sortByInsertion(array: IntArray): IntArray {
    array.forEachIndexed { index, i ->
        var j = index
        while (j > 0 && array[j - 1] > i){
            array[j] = array[j - 1]
            j--
        }
        array[j] = i
    }
    return array
}
```

카드의 순서를 바꾸는 것과 비슷한 방법

선택 정렬과 유사하지만선택한 요소를 알맞은 위치에 삽입한다.

수를 선택하여 해당 인덱스의 아래 수들과 비교하여 알맞은 위치에 삽입한다.

오름차순일 경우 자신보다 작은 수와 큰 수 사이에 삽입하는 방법

### Quick Sort

```kotlin
fun sortByQuick(array: IntArray, start: Int = 0, end: Int = array.lastIndex): IntArray {
    val part = partition(array, start, end)

    if (start < part - 1) sortByQuick(array, start, part - 1)

    if (part < end) sortByQuick(array, part, end)
    return array
}

fun partition(array: IntArray, start: Int, end: Int): Int {
    var l = start
    var r = end
    var pivot = array[(l + r) / 2]
    while (l <= r) {
        while (array[l] < pivot) l++
        while (array[r] > pivot) r--
        if (l <= r) swap(array, l++, r--)
    }
    return l
}
```

피벗을 설정하여 피벗보다 작은 그룹과 큰 그룹으로 나눈다.

그룹 내에서 피펏을 또 설정, 나눔을 반복하며 각 그룹이 1명이 되면 정렬을 마친다.

피벗을 정하면 배열을 피벗을 기준으로 왼쪽(피벗>수) 오른쪽(피벗<수)으로 나누어 조건에 맞게 수를 교환한다.


### Merge Sort
```kotlin
fun sortByMerge(array: IntArray) {
    sorted = IntArray(array.size)

    sortByMerge(array, 0, array.lastIndex)
}

private fun sortByMerge(array: IntArray, left: Int, right: Int) {
    if (left < right) {
        val mid = (left + right) / 2
        sortByMerge(array, left, mid)
        sortByMerge(array, mid + 1, right)

        merge(array, left, right)
    }
}

fun merge(array: IntArray, left: Int, right: Int) {
    var mid = (left + right)/2

    var lStart = left
    var rStart = mid + 1

    var sortedIndex = left


    while (lStart <= mid && rStart <= right) {
        sorted[sortedIndex++] = if (array[lStart] < array[rStart]) array[lStart++] else array[rStart++]
    }
    while (lStart <= mid) sorted[sortedIndex++] = array[lStart++]
    while (rStart <= right) sorted[sortedIndex++] = array[rStart++]

    for (i in left..right)array[i]= sorted[i]
}
```

배열의 앞과 뒤를 나누어 각각 정렬한 다음 병합하는 작업을 반복하여 정렬하는 방법

정렬을 마친 두 배열을 비교하여 작은 값을 순서대로 꺼내 새로운 배열에 넣는다.

분할 정복을 사용한 방법

정렬의 방식 또한 배열을 앞 뒤로 나누는 함수를 호출하는 것으로 한다. (배열 크기가 2보다 크다면)
.



