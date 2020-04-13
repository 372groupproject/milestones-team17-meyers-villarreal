object p3_list {
  def list_ops(): Unit = {
    var A = List(1, 2, 3, 4)
    var B = List(5, 6, 7, 8)
    var C = Array(1, 2, 3, 4, 5, 6)

    println(A ++ B)
    println(A ++: B)
    println(A +: B)
    println(A :+ B)
    println(A :: B)
    println(A ::: B)
    println(A.contains(1))
    println(A.copyToArray(C, 0, 3))
    println(A.count(even))
    println(A.dropWhile(even))
    println(A.filter(even))
    println(A.find(even))
    println(A.forall(even))
    println(A.hashCode())
    println(A.indexOf(1))
    println(A.length)
    println(A.map(even))
    println(A.reverse)
    println(A.tail)
    println(A.isEmpty)
    println(A.take(2))
    println(A.takeWhile(even))
    println(A.zip(B))
  }

  def even(a: Int): Boolean = {
    return a % 2 == 0
  }
}
