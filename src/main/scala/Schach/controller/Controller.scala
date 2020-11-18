package Schach.controller

import Schach.model.GameField
import Schach.util.Observable

class Controller(var gameField: GameField) extends Observable{
  def createGameField(): Unit = {
    gameField = new GameField
    notifyObservers
  }

  def gameFieldToString : String = gameField.toString

  def moveTo(posXNow: Int, posYNow: Int, posXNext: Int, posYNext: Int): Unit = {
    gameField.moveTo(posXNow, posYNow, posXNext, posYNext)
    notifyObservers
  }
}
