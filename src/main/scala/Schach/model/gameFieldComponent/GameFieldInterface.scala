package Schach.model.gameFieldComponent

import Schach.model.figureComponent.Figure
import Schach.model.gameFieldComponent.gameFieldBaseImpl.GameField

import java.awt.Color



trait GameFieldInterface {

  def addFigures(figures : Vector[Figure]) : GameField
  def getFigures: Vector[Figure]
  def moveTo(xNow: Int, yNow: Int, xNext: Int, yNext: Int): GameField
  def moveValid(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean
  def moveToFieldAllowed(x: Int, y: Int, figure: Figure): Boolean
  def setSelfIntoCheck(figure: Figure, xNext : Int, yNext : Int, king: Figure): Boolean
  def isCheckmate: Boolean
  def wayToIsFreeStraight(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean
  def wayToIsFreeDiagonal(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean

  def getPlayer: Color
  def setPlayer(color: Color): Color
  def changePlayer(): Color
  def getFigure(xPos: Int, yPos: Int): Option[Figure]
  def clear() : Boolean
  def toString: String

}

trait ChessGameFieldBuilderInterface {

  def makeGameField() : Unit
  def getGameField: GameField
  def getNewGameField: GameField
}
