package Schach

object Schach {
/*
  def createGameField(): String = {
    val aToH = List("a", "b", "c", "d", "e", "f", "g", "h")
    val numbers = List("8", "7", "6", "5", "4", "3", "2", "1")
    val cellSide = " │   │   │   │   │   │   │   │   │"
    val cellMiddle = "├───┼───┼───┼───┼───┼───┼───┼───┤"
    val cellBottom = "  └───┴───┴───┴───┴───┴───┴───┴───┘"
    val field = new StringBuilder(" ")

    for (i <- 0 to 7) field.append("   " + aToH(i))
    field.append("\n")

    field.append("  ┌───┬───┬───┬───┬───┬───┬───┬───┐\n")


    for (x <- 0 to 7) {
      for (i <- 0 to 1) {
        if (i == 0) field.append(numbers(x)).append(cellSide).append("\n")
        else {
          if (x < 7) field.append("  ").append(cellMiddle).append("\n")
          else field.append(cellBottom)
        }
      }

    }
    //Test

    field.toString()
  }

 */

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


    var gameField = new GameField()

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
          gameField = gameField.moveTo(ret(0), ret(1), ret(2), ret(3))
          println(gameField.toString)
        } else {
          println("Wrong input")
        }
      }
    }
  }
}