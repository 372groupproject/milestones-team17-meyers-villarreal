package small_snippets

object p2_case {
  def use_case(num : Int): Unit = {
    num match{
      case 0 => println("0 was input to case")
      case 1 => println("1 input to case")
    }
  }
}
