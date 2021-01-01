package Schach

import aview.{Gui, Tui}
import controller.controllerComponent.controllerBaseImpl.Controller
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

    var break = false
    val controller = new Controller()
    controller.createGameField()
    val gui = new Gui(controller)
    val tui = new Tui(controller)
    controller.notifyObservers

    println("Move the chess pieces: position they are at now -> position they should go to")
    println("Create a new GameField with 'new'")
    println("Usage example: move A2 A3")
    println("Type 'exit' to leave\n")

    while (break == false) {
      val line = scala.io.StdIn.readLine()

      if (line.equals("exit")) {
        break = true
        println("exiting...")
      } else {
        tui.interactWithUser(line)
      }
    }
  }
}