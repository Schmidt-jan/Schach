package Schach.controller

import java.awt.Color

import Schach.model.Figure
import Schach.util.Memento

class GameFieldMemento(field: Vector[Figure], player: Color) extends Memento{
  private val gameField = field
  private val gamePlayer = player

  override def getFigures : Vector[Figure] = {
    gameField
  }

  override def getPlayer : Color = {
    gamePlayer
  }
}
