package Schach

object Schach {

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

  def readInput(line: String): Array[Int] = {
    val fromX = getPoint(line.charAt(0))
    val fromY = getPoint(line.charAt(1))
    val toX = getPoint(line.charAt(3))
    val toY = getPoint(line.charAt(4))
    Array(fromX, fromY, toX, toY)
  }

  def getPoint(input: Char): Int = {
    input match {
      case 'A' => 0
      case 'B' => 1
      case 'C' => 2
      case 'D' => 3
      case 'E' => 4
      case 'F' => 5
      case 'G' => 6
      case 'H' => 7
      case '1' => 0
      case '2' => 1
      case '3' => 2
      case '4' => 3
      case '5' => 4
      case '6' => 5
      case '7' => 6
      case '8' => 7
      case _ => -1
    }
  }

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
    println(gameField.toString());
    gameField.moveTo(1, 1, 1, 3)
    gameField.moveTo(0, 0, 0, 5)
    println(gameField.toString());

    var abbruch = false
    while (abbruch == false) {
      val line = scala.io.StdIn.readLine()

      if (line.equals("exit")) {
        abbruch = true
      } else {
        if (line.matches("([A-H][1-8]\\s[A-H][1-8])")) {
          val ret = readInput(line)
          gameField.moveTo(ret(0), ret(1), ret(2), ret(3))
          println(gameField.toString)
        } else {
          println("Wrong input")
        }
      }
    }
  }
}