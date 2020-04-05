package small_snippets

object p2_while {
  def use_while(num : Int): Unit = {
    while(num >= 0){
      println(num);
    }
    println("While loop ran " + num + " times");
  }
}
