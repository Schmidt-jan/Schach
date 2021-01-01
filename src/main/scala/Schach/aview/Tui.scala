package Schach.aview

import Schach.controller.controllerComponent.ControllerInterface
import Schach.util.Observer

import scala.util.Success

class Tui(controller: ControllerInterface) extends Observer{

  controller.add(this)


  def interactWithUser(input: String) : Unit = {
    val args = input.split(" ")

    args(0) match {
      case "new" => Success(controller.createGameField)
      case "move" =>
        if (args.size == 3 && controller.controlInput(args(1)) && controller.controlInput(args(2))) {
          val command = args(1).concat(" ").concat(args(2))
          if (controller.moveIsValid(readInput(command))) {
            controller.movePiece(readInput(command))
            controller.changePlayer()
          } else {
            println("That Move is against the Rules!")
          }
        }
        else {
          println("Wrong Input: Invalid Move")
        }
      case "undo" => controller.undo()
      case "redo" => controller.redo()
      case "save" => controller.save()
      case "load" =>
        if (controller.caretakerIsCalled()){
          controller.restore()
        } else {
          println("No Save created yet")
        }
      case _ => println("No Valid Command")
    }
  }


  def readInput(line: String): Vector[Int] = {
    val fromX = getPoint(line.charAt(0))
    val fromY = getPoint(line.charAt(1))
    val toX = getPoint(line.charAt(3))
    val toY = getPoint(line.charAt(4))
    Vector(fromX, fromY, toX, toY)
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

  override def update: Unit = println(controller.gameFieldToString)
}