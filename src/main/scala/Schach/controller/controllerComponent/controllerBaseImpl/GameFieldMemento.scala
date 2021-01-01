package Schach.controller.controllerComponent.controllerBaseImpl

import Schach.model.figureComponent.Figure
import Schach.util.Memento

import java.awt.Color

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
