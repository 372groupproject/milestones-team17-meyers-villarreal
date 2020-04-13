object p3_recursionExamples {
  def main(args: Array[String]){
    println(combinations(7, 3))
    println(intWithCommas(123456789))
    println(keepEvens(Array(1, 2, 3, 4, 5, 6)))
    println(arrayRange(Array(23, 45, 8678, 23, 5, 1, 65, 78)))
    println(isSorted(Array(1, 2, 3, 4, 54, 6, 7, 8)))
    println(found("hi", Array("sdf", "hi", "tyd")))
  }

  def combinations(n: Int, k: Int): Int ={
    if(k == 1) {
      return n
    }
    if(n == k){
      return 1
    }
    return combinations(n - 1, k - 1)
  }

  def intWithCommas(n: Int): String ={
    var str = ""

    if(n < 1000){
      return Integer.toString(n)
    }

    return intWithCommas(n / 1000) + "," + Integer.toString(n).substring(Integer.toString(n).length() - 3)
  }

  def keepEvens(A: Array[Int]): Array[Int] = {
    if(A.length == 0){
      return Array()
    }
    if(A.head % 2 == 0){
      return A.head +: keepEvens(A.tail)
    }
    return keepEvens(A.tail)
  }

  def arrayRange(A: Array[Int]): Int = {
    return arrayRangeHelper(A, Integer.MIN_VALUE, Integer.MAX_VALUE)
  }

  def arrayRangeHelper(A: Array[Int], max: Int, min: Int): Int = {
    var maxi = max
    var mini = min

    if(A.length == 0){
      return max - min
    }

    if(A.head > max){
      maxi = A.head
    }
    if(A.head < min){
      mini = A.head
    }

    return arrayRangeHelper(A.tail, maxi, mini)
  }

  def isSorted(A: Array[Int]): Boolean = {
    if(A.length <= 1){
      return true
    }
    return (A.head <= A(1)) && isSorted(A.tail)
  }

  def found(search: String, strs: Array[String]): Boolean = {
    if(strs.length == 0){
      return false;
    }
    if(strs.head.equals(search)){
      return true;
    }
    return found(search, strs.tail)
  }
}
