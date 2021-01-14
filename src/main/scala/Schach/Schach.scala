package Schach

import com.google.inject.Guice
import controller.controllerComponent._
import aview._

object Schach {


  def main(args: Array[String]) {

    var break = false
    val injector = Guice.createInjector(new GameFieldModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
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