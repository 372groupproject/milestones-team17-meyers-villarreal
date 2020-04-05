package small_snippets

object p2_append {
  def main(args: Array[String]){
    val L1 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val L2 = List(7, 8, 9)

    val L = append(L1, L2)

    println(L)
  }

  def append(L1 : List[Int], L2 : List[Int]): List[Int] = {
    if(L1 == List()){
      return L2
    }
    else{
      return L1.head :: append(L1.tail, L2)
    }
  }
}
