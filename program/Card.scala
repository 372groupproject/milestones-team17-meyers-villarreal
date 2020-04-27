package csc372

//demo of deck construction and prints the deck
object cardMain{
  def main (args: Array[String]): Unit = {
    /*
    val test = new Deck()
    print(test)
    test.shuffle()
    println(test)
    */
  }
}

//Creates the card class
class Card(var suit: String, var value: Int) {
  override def toString() : String =  {
    return value + " of " + suit;
  }



}

trait DealCards{
  def dealToPlayer(player : List[Card]) : Unit
  def dealNCards(nCards : Int): Unit
}

//Creates the deck class
class Deck(var deck: List[Card] = List.empty)  {
  //If deck is blank/empty it makes the basic deck
  if (deck.isEmpty)
    deck = makeDeck()
  //prints out a deck for debugging
  override def toString() : String =  {
    val temp = for(i<- deck) yield (i)
    temp.mkString("\n")
  }


  //returns the deck
  def getDeck(): List[Card] = {
    return deck
  }

  //Makes a basic 52 card deck
  def makeDeck() : List[Card] =  {
    val suits = List("Diamond", "Spade", "Club", "Heart")
    var values = for(i <- 1 to 13) yield i

    val deck = for(i <- suits; j <- values)
      yield new Card(i, j)

    val gameDeck = new Deck(deck)
    return gameDeck.deck
  }

  //shuffles the deck
  def shuffle(): Unit ={
    var tempDeck = List[Card]()
    val rand = scala.util.Random
    while(deck.nonEmpty){
      val tempCard = deck.apply(rand.nextInt(deck.size))
      tempDeck = tempCard :: tempDeck
      deck = deck.filter(tempCard != _)
    }
    deck = tempDeck
  }

  def drawCard(): Card = {
    val returnCard = deck.apply(0)
    deck = deck.filter(_ != returnCard)
    return returnCard
  }






}

