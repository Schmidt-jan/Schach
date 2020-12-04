package Schach.controller

import Schach.model.Figure
import Schach.util.Memento

class GameFieldMemento(field: Vector[Figure]) extends Memento{
  private val gameField = field

  override def getFigures : Vector[Figure] = {
    gameField
  }
}
