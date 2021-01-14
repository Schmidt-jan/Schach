package Schach.model.gameFieldComponent

import java.awt.Color

import Schach.model.figureComponent.{Figure, Pawn}
import Schach.model.gameFieldComponent.gameFieldBaseImpl.GameField



trait GameFieldInterface {
  final val RUNNING = 0
  final val CHECKED = 1
  final val CHECKMATE = 2
  final val MOVE_ILLEGAL = 3
  final val PAWN_REACHED_END = 4

  def addFigures(figures : Vector[Figure]) : GameField
  def getFigures: Vector[Figure]
  def convertFigure(figure : Figure, toFigure : Figure)
  def moveTo(xNow: Int, yNow: Int, xNext: Int, yNext: Int): GameField
  def moveValid(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean
  def moveToFieldAllowed(x: Int, y: Int, figure: Figure): Boolean
  def setSelfIntoCheck(figure: Figure, xNext : Int, yNext : Int): Boolean
  def pawnHasReachedEnd() : Boolean
  def getPawnAtEnd(): Pawn
  def isChecked(playerCol: Color): Boolean
  def isCheckmate(playerCol: Color): Boolean
  def cellsFreeAroundFigure(figure: Figure) : List[(Int, Int)]
  def wayToIsFreeStraight(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean
  def wayToIsFreeDiagonal(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean

  def getPlayer: Color
  def setPlayer(color: Color): Color
  def changePlayer(): Color
  def getFigure(xPos: Int, yPos: Int): Option[Figure]
  def clear() : Boolean
  def setStatus(newStatus : Int)
  def getStatus() : Int
  def toString: String

}

trait ChessGameFieldBuilderInterface {

  def makeGameField() : Unit
  def getGameField: GameField
  def getNewGameField: GameField
}
