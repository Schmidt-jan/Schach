package Schach.aview

import Schach.controller.Controller
import Schach.util.Observer

class Tui(controller: Controller) extends Observer {
  val regex = "[A-H][1-8]"

  controller.add(this)

  def doCommand(input: String) : Unit = {
    val args = input.split(" ")
    args(0) match {
      //move
      case 'm' =>
        if (args.size == 3 && args(1).matches(regex) && args(2).matches(regex)) {
          val xFrom = getPoint(args(1).charAt(0))
          val xTo = getPoint(args(1).charAt(1))
          val yFrom = getPoint(args(2).charAt(0))
          val yTo = getPoint(args(2).charAt(1))
          controller.moveTo(xFrom, yFrom, xTo, yTo)
        }

      //resign
      case "resign" =>

      //new game
      case 'n' =>

      case _ => println("Wrong input")

    }
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

  override def update: Unit = println(controller.gameFieldToString)
}
