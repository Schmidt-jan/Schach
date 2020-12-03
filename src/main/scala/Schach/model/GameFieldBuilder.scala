package Schach.model

trait GameFieldBuilder {

  def makeGameField() : Unit

  def getGameField : GameField
}
