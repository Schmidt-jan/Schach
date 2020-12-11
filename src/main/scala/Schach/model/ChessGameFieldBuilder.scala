package Schach.model

import java.awt.Color

import Schach.util.GameFieldBuilder

class ChessGameFieldBuilder extends GameFieldBuilder {

  private val instance : GameField = GameField.getInstance

  private def buildWhite(): Unit = {
    instance.addFigures(Vector(
      Rook(0, 0, Color.WHITE), Knight(1, 0, Color.WHITE), Bishop(2, 0, Color.WHITE), King(3, 0, Color.WHITE),
      Queen(4, 0, Color.WHITE), Bishop(5, 0, Color.WHITE), Knight(6, 0, Color.WHITE), Rook(7, 0, Color.WHITE),
      Pawn(0, 1, Color.WHITE), Pawn(1, 1, Color.WHITE), Pawn(2, 1, Color.WHITE), Pawn(3, 1, Color.WHITE),
      Pawn(4, 1, Color.WHITE), Pawn(5, 1, Color.WHITE), Pawn(6, 1, Color.WHITE), Pawn(7, 1, Color.WHITE)))

  }

  private def buildBlack(): Unit = {
    instance.addFigures(Vector(
      Pawn(0, 6, Color.BLACK), Pawn(1, 6, Color.BLACK),
      Pawn(2, 6, Color.BLACK), Pawn(3, 6, Color.BLACK),
      Pawn(4, 6, Color.BLACK), Pawn(5, 6, Color.BLACK),
      Pawn(6, 6, Color.BLACK), Pawn(7, 6, Color.BLACK),
      Rook(0, 7, Color.BLACK), Knight(1, 7, Color.BLACK),
      Bishop(2, 7, Color.BLACK), King(3, 7, Color.BLACK),
      Queen(4, 7, Color.BLACK), Bishop(5, 7, Color.BLACK),
      Knight(6, 7, Color.BLACK), Rook(7, 7, Color.BLACK)))
  }

  def makeGameField() : Unit = {
    getGameField.clear()
    buildBlack()
    buildWhite()
  }

  override def getGameField: GameField = {
    instance
  }

  override def getNewGameField: GameField = {
    makeGameField()
    instance
  }

  /*
  override def setGameField(newField : GameField) : GameField = {
    getNewGameField
    instance.addFigures(newField.getFigures)
  }
   */

}