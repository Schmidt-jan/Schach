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
          controller.movePiece(readInput(command))
        }
        else {
          println("Wrong Input: Invalid Move")
        }
      case "switch" => convertPawn(args(1))
      case "undo" => controller.undo()
      case "redo" => controller.redo()
      case "save" => controller.save()
      case "load" =>
        if (controller.caretakerIsCalled()){
          controller.restore()
        } else {
          println("No Save created yet")
        }
      case "save_game" => controller.saveGame()
      case "load_game" => controller.loadGame()
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

  def printGameStatus(): Unit = {
    controller.getGameStatus() match {
      case 0 => println("RUNNING")
      case 1 => println("PLAYER " + { if (controller.getPlayer().getRed == 0) "Black"
                                      else "WHITE"} + "IS CHECKED")
      case 2 => println({if (controller.getPlayer().getRed == 0) "BLACK "
                          else "WHITE "} + "IS CHECKMATE")
      case 3 => println("INVALID MOVE")
      case 4 =>
      //println("PAWN HAS REACHED THE END")
    }
  }

  def convertPawn(line: String) ={
    controller.changePlayer()
    println({if (controller.getPlayer().getRed == 0) "Black's "
            else "White's "} + "player has reached the end of the game field.\n" +
            "Change it to a 'queen', 'rook', 'knight' or 'bishop' by typing into the console")

    line match {
      case "queen" => controller.convertPawn("queen")
      case "rook" => controller.convertPawn("rook")
      case "knight" => controller.convertPawn("knight")
      case "bishop" => controller.convertPawn("bishop")
      case _ => println("Wrong Input")
    }

    controller.checkStatus()
    printGameStatus()
    controller.changePlayer()
  }

  override def update: Unit = {
    printGameStatus()
    println(controller.gameFieldToString)
  }
}