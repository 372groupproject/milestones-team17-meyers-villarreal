package small_snippets

object p2_for {
  def use_for(num : Int): Unit = {
    var i = 1;
    for(i <- 1 to num){
      println(i)
    }
    println("For loop ran " + num + " times")
  }
}
