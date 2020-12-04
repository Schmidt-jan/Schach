package Schach.util

import Schach.model.GameField

trait GameFieldBuilder {

  def makeGameField(): Unit

  def getGameField: GameField
}
