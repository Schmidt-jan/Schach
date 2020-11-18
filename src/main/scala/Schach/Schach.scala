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


    val gameField = GameField()

    /*
    println(gameField.toString());
    gameField.moveTo(1, 1, 1, 3)
    gameField.moveTo(0, 0, 0, 5)
     */
    println(gameField.toString())


    var abbruch = false
    val tui = new Tui
    while (!abbruch) {
      val line = scala.io.StdIn.readLine()

      if (line.equals("exit")) {
        abbruch = true
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