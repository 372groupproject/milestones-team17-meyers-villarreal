/*
 * Author: Graysen Meyers (gpmeyers@email.arizona.edu)
 * ScalaCodeSnippet1.scala
 * First Code Snippet Demo
 */
object ScalaCodeSnippet1 {
  def main(args: Array[String]){
    println("Hello World!");

    var evenList = for { i <- 1 to 20
                         if(i % 2) == 0
    } yield i

    val randLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    for(i <- 0 until randLetters.length){
      println(randLetters(i))
    }

    val aList = List(1,2,3,4,5)
    for(i <- aList){
      println("List items " + i)
    }

  }
}
