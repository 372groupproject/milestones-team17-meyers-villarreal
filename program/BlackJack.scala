package csc372

object bjMain {
  def main(args: Array[String]): Unit = {

    val blackJack = new BlackJack(new Deck())
    blackJack.shuffle()
    blackJack.startGame()
    while(blackJack.getGameStatus() == 0){
      print("Hit or stay?: ")
      var result = readLine().toLowerCase()

      //Check immediately for a win or loss
      blackJack.getSumCards(blackJack.playerHand) match {
        case lose if lose > 21 => blackJack.setGameStatus(2)
        case win  if blackJack.getSumCards(blackJack.computerHand) > 21 => blackJack.setGameStatus(1)
        case win  if win == 21 && blackJack.getSumCards(blackJack.computerHand) != 21
          => blackJack.setGameStatus(1)
        case _ =>
        {
          //Decide if what action to take hit or stay
          result match {
            //If hit is chosen player draws and computer determines if it should draw
            case "hit" => {
              blackJack.drawPlayer()
              if (blackJack.willDealerDraw())
                blackJack.drawComputer()
            }
            //If stay is chosen the computer will determine to draw
            case "stay" => {
              if (blackJack.willDealerDraw())
                blackJack.drawComputer()

              //Check for win or loss
              blackJack.getSumCards(blackJack.playerHand) match {
                case lose if lose > 21 => blackJack.setGameStatus(2)
                case win if win == 21 | blackJack.getSumCards(blackJack.computerHand) > 21 => blackJack.setGameStatus(1)
                case win if win > blackJack.getSumCards(blackJack.computerHand) => blackJack.setGameStatus(1)
                case lose if lose <= blackJack.getSumCards(blackJack.computerHand)
                  | blackJack.getSumCards(blackJack.computerHand) == 21 => blackJack.setGameStatus(2)

              }
            }
            case _ =>
          }
        }
      }

      //Prints the new hands
      blackJack.printHands()
    }

  }

  //BlackJack class that has useful functions for making a game of BlackJack
  class BlackJack(gameDeck : Deck) {
    var playerHand = List[Card]()
    var computerHand = List[Card]()

    //0 means no one is winning, 1 player wins, 2 computer wins
    var gameStatus = 0;

    def shuffle(): Unit ={
      gameDeck.shuffle()
    }

    def getGameStatus(): Int = {
      return gameStatus
    }

    //Sets the status of the game
    def setGameStatus(newStatus : Int){
      gameStatus = newStatus
      gameStatus match{
        case 1 => println("\nYou win!")
        case 2 => println("\nDealer wins!")
        case _ => println("ERROR!")
      }
    }

    //Draws a card for the player
    def drawPlayer(): Unit ={
      playerHand ::= gameDeck.drawCard()
    }

    //Draws a card for the computer
    def drawComputer(): Unit ={
      computerHand ::= gameDeck.drawCard()
    }

    //Draws 2 cards for the dealer and player and shows their hands
    def startGame(): Unit ={
      for(i <- 1 to 2){
        drawPlayer()
        drawComputer()
      }
      printHands()
    }

    //Prints both the dealer's and player's hand
    def printHands(): Unit ={
      //print(computerHand)
      println("Dealer\t| " + (for(i <- computerHand) yield i).mkString("\t\t | "))
      println("You:\t| " + (for(i <- playerHand) yield i).mkString("\t\t | "))
    }

    //Determines if the dealer should draw by not going over 21
    def willDealerDraw(): Boolean ={
      val totalCardValue = getSumCards(computerHand)
      if (totalCardValue >= 21)
          return false
      return true
    }

    //Calculates the sum of a given hand
    def getSumCards(hand: List[Card]): Int ={
      return  (for(i <- hand) yield i.value).sum
    }
  }
}
