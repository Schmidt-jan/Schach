package Schach
import aview.Tui
import model._
import controller.Controller

import scala.io.StdIn

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

    val controller = new Controller(new GameField)
    val tui = new Tui(controller)

    var stop = false

    while (!stop) {
      val line = StdIn.readLine()

      if (line.equals("exit")) {
        stop = true
      } else {
        tui.doCommand(line)
      }
    }
  }
}