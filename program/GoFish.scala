package csc372


object gfMain{
  def main(args: Array[String]): Unit = {
   new GoFish().startGame()
  }

}


class GoFish {
  val gameDeck = new Deck()

  private var playerHand = List[Card]()
  private var computerHand = List[Card]()

  private var intPairsFound = 0
  private var playerPairs = 0
  private var computerPairs = 0
  private var maxDeckSize = gameDeck.deck.size
  private var playerTurn = false
  private var gameStatus = false

  def startGame(): Unit ={
    gameDeck.shuffle()
    dealNCards(5, false) //Deal 5 cards to the player
    dealNCards(5, true) //Deal 5 cards to the computer

    promptFish()
  }

  def printHand(): Unit ={
    println("You:\t| " + (for(i <- playerHand) yield i).mkString("\t\t | "))
    //println("CPU:\t| " + (for(i <- computerHand) yield i).mkString("\t\t | "))
  }

  def takeCardPlayer(cardToTake: Card): Boolean = {
    playerHand.contains(cardToTake) match {
      case true => {
        playerHand = playerHand.filter(_ != cardToTake)
        return true
      }
      case false => return false
    }
  }

  def takeCardComputer(cardToTake: Card): Boolean ={
    computerHand.contains(cardToTake) match{
      case true => {
        computerHand = computerHand.filter(_ != cardToTake)
        return true
      }
      case false => return false//Can't take card
    }
  }

  def handToList(hand : List[Card]) : List[Int] = {
      return for(i <- hand) yield i.value
    }

  def promptFish(): Unit ={
    while(!gameStatus){
      printHand()
      println("Cards left in deck: " + gameDeck.deck.size)
      playerTurn match{
        case false => //The player will try to take a card
          {
            println("\nYour turn:")
            //If player has pairs it will alert the player and remove the pairs
            while(hasPairInHand(playerHand)){
              var pairValue = returnFirstPair(playerHand)
              println("pair of " + pairValue + "'s found!")
              takeCardPlayer(cardFromValue(pairValue, playerHand))
              takeCardPlayer(cardFromValue(pairValue, playerHand))
              updatePairs()
            }
            playerTurn = promptFishHelper(playerHand, computerHand)
            //playerTurn = true; //Computer's turn
          }
        case true => {
            println("\nCPU turn:")
          //If player has pairs it will alert the player and remove the pairs
          while(hasPairInHand(computerHand)){
            var pairValue = returnFirstPair(computerHand)
            println("pair of " + pairValue + "'s found!")
            takeCardComputer(cardFromValue(pairValue, computerHand))
            takeCardComputer(cardFromValue(pairValue, computerHand))
            updatePairs()
          }
            playerTurn = promptFishHelper(computerHand, playerHand)
            //playerTurn = false //Player's turn
            }
          }
        }
    println(f"You: $playerPairs%s pairs \t CPU: $computerPairs%d pairs")

  }

  //Deals n amount of cards to a deal target
  def dealNCards(n : Int, dealTarget: Boolean): Unit ={
    n match{
      case n if n <= 0 => //Nothing
      case _ => for (i <- 1 to n) dealCard(dealTarget)
    }

  }

  //Deals a card to a deal target will not deal anymore if deck is empty
  def dealCard(dealTarget: Boolean): Unit ={
    gameDeck.deck.size match{
      case 0 => //No cards left to deal
      case _ => //Deal some cards
      {
        dealTarget match {
          case false => playerHand ::= gameDeck.drawCard()//Deal to player
          case true => computerHand ::= gameDeck.drawCard() //Deal to computer
        }
      }
    }
  }

  //Updates the pairs found until the game is over
  def updatePairs(): Unit = {
  intPairsFound += 1
    playerTurn match {
      case false => playerPairs += 1
      case true => computerPairs += 1
    }
    if (intPairsFound >= maxDeckSize / 2)
      gameStatus = true
  }

  //Returns the first card with the value requested
  def cardFromValue(value : Int, hand : List[Card]) : Card = {
    hand.filter(_.value == value) match{
      case n if n.size == 0 =>
      case n if n.size  > 0 => return n.head
    }
    return null
  }

  //Returns true if a pair exists in the hand given, false otherwise
  def hasPairInHand(hand : List[Card]): Boolean = {
    var foundPair = false
    for (i <- 0 to hand.size - 1 if countValues(hand.splitAt(i)._2, hand.apply(i).value) >= 2)
      foundPair = true;
    return foundPair
  }

  //Returns first value of a card that has a pair, otherwise -1
  def returnFirstPair(hand : List[Card]): Int = {
    for (i <- 0 to hand.size - 1 if countValues(hand.splitAt(i)._2, hand.apply(i).value) >= 2)
      return hand.apply(i).value
    return -1
  }

  //Returns how many times the value n exists in a given hand of cards
  def countValues(hand : List[Card], n : Int) : Int = {
    var count = 0
    for(i <- hand if i.value == n) count += 1
    return count
  }

  def promptFishHelper(hand : List[Card], otherHand : List[Card]): Boolean ={
    {
      hand.size match{
        case 0 => {
          println("\tHand is empty! Automatic GO FISH!")
          dealNCards(1, playerTurn)
          return !playerTurn
        }
        case _ => //The player has cards in hand
        {
          var result = -1
          playerTurn match{
            case false => //If it is the player ask to choose a card
            {
              print("\tChoose a number for fishing: (" + handToList(hand).mkString(", ") + ") ")
              var resultString = scala.io.StdIn.readLine()
              result = resultString.trim().toInt
            }
            case true => //If it is the computer it will choose a random card from hand to ask
            {
              val randomVal = scala.util.Random.nextInt(hand.size)
              result = hand.apply(randomVal).value
              println("\tComputer wants to know if you have " + result + "? [Press Enter to Continue]")
              scala.io.StdIn.readLine() //Used to pause until player presses enter
            }
          }
          //Checks if the selected card is in their hand for a pair to occur
          handToList(hand).filter(_ == result).size match{
            case 0 => //Player entered a wrong card value
            case n if n > 0 && takeCardComputer(cardFromValue(result, otherHand)) => {
              takeCardPlayer(cardFromValue(result, hand))
              println("\tGot a pair of " + result + "'s\n")
              updatePairs()
            }
            case n if n > 0 && takeCardPlayer(cardFromValue(result, otherHand)) => {
              takeCardComputer(cardFromValue(result, hand))
              println("\tGot a pair of " + result + "'s\n")
              updatePairs()
            }
            case _ =>
              println("\tGO FISH!\n")
              dealNCards(1, playerTurn)

              return !playerTurn
          }
        }
      }
    }
    return playerTurn
  }

}
