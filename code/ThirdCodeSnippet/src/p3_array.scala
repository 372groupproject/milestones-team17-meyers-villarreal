object p3_array {
  def array_ops(): Unit = {
    val A = Array(1, 2, 3, 4)
    val B = Array(5, 6, 7, 8)

    println(A ++ B)
    println(A ++: B)
    println(A +: B)
    println(A :+ 5)
    println(A.foreach(f))
    println(A.indexOf(1))
    println(A.length)
    println(A.map(f))
  }

  def f(x: Int): Int = {
    return x + 3
  }
}
