object p3_runtimeError {
  def func(): Unit = {
    val list = List(1, 2, 3)

    println(list(5))
  }
}
