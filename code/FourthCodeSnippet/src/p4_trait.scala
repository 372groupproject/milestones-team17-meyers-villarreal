object p4_trait {
  def main(args: Array[String]): Unit ={

  }

  trait Flying{
    def fly: String
  }

  class Bird(val name: String) extends Flying{
    def fly = "%s is able to fly".format(this.name)
  }
}
