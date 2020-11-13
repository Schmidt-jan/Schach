package Schach
import aview.Tui
import model._

object Schach {


  def main(args: Array[String]) {
    /*
    println("Welcome to Chess")
    println("What is your name?")
    val name = scala.io.StdIn.readLine()

    val playerOne = Player(name)
    println("Hello " + playerOne.name + "\n")
    println("This is the Chess Board\n")
    val field = createGameField()
    println(field)
    println(field.length)
    */


    val gameField = new GameField()
    gameField.init()
    /*
    println(gameField.toString());
    gameField.moveTo(1, 1, 1, 3)
    gameField.moveTo(0, 0, 0, 5)
     */
    println(gameField.toString());

    println("Move the chess pieces: position they are at now -> position they should go to")
    println("Usage example: A2 A3")
    println("Type 'exit' to leave\n")
    var abbruch = false
    val tui = new Tui
    while (abbruch == false) {
      val line = scala.io.StdIn.readLine()

      if (line.equals("exit")) {
        abbruch = true
        println("exiting...")
      } else {
        if (tui.controlInput(line)) {
          val ret = tui.readInput(line)
          gameField.moveTo(ret(0), ret(1), ret(2), ret(3))
          println(gameField.toString)
        } else {
          println("Wrong input")
        }
      }
    }
  }
}