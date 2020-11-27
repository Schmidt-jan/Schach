package Schach.controller

import Schach.model.GameField
import Schach.util.Observable
import Schach.model.Rules

class Controller(var gameField: GameField) extends Observable{
  val rule = new Rules(gameField)

  def createGameField: Unit = {
    gameField = new GameField()
    notifyObservers
  }

  def controlInput(line: String): Boolean = {
    line.matches("[A-H][1-8]")
  }

  def gameFieldToString: String = gameField.toString

  def movePiece(newPos: Vector[Int]): Unit = {
    gameField = gameField.moveTo(newPos(0), newPos(1), newPos(2), newPos(3))
    notifyObservers
  }

  def moveIsValid(newPos: Vector[Int]): Boolean = {
    rule.moveValid(newPos(0), newPos(1), newPos(2), newPos(3))
  }


}
