package Schach.controller

import Schach.model.GameField
import Schach.util.Memento

class GameFieldMemento(field: GameField) extends Memento{
  private val gameField = field

  override def getField: GameField = {
    gameField
  }
}
