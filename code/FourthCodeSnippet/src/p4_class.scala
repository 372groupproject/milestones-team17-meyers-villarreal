object p4_class {
  def main(args: Array[String]): Unit ={

  }

  abstract class Mammal(val name: String){
    var diet: String
    def getDiet: String
  }

  class Bear(name: String) extends Mammal(name){
    var diet = "Omnivore"

    def getDiet(): String ={
      return "The bear %s is an %s".format(name, diet)
    }
  }
}
