package Schach.model.gameFieldComponent.gameFieldBaseImpl

import java.awt.Color
import Schach.model.figureComponent._
import Schach.model.gameFieldComponent.ChessGameFieldBuilderInterface
import Schach.util.GameFieldBuilder

/** Responsible of initialising a new GameField
 *
 */
class ChessGameFieldBuilder extends GameFieldBuilder with ChessGameFieldBuilderInterface {

  private val instance : GameField = new GameField()

  private def buildWhite(): Unit = {
    instance.addFigures(Vector(
      Figure("rook", 0, 0, Color.WHITE), Figure("Knight", 1, 0, Color.WHITE),
      Figure("Bishop", 2, 0, Color.WHITE), Figure("Queen", 3, 0, Color.WHITE),
      Figure("King", 4, 0, Color.WHITE), Figure("Bishop", 5, 0, Color.WHITE),
      Figure("Knight", 6, 0, Color.WHITE), Figure("Rook", 7, 0, Color.WHITE),
      Figure("Pawn", 0, 1, Color.WHITE), Figure("Pawn", 1, 1, Color.WHITE),
      Figure("Pawn", 2, 1, Color.WHITE), Figure("Pawn", 3, 1, Color.WHITE),
      Figure("Pawn", 4, 1, Color.WHITE), Figure("Pawn", 5, 1, Color.WHITE),
      Figure("Pawn", 6, 1, Color.WHITE), Figure("Pawn", 7, 1, Color.WHITE)))
  }

  private def buildBlack(): Unit = {
    instance.addFigures(Vector(
      Pawn(0, 6, Color.BLACK), Pawn(1, 6, Color.BLACK),
      Pawn(2, 6, Color.BLACK), Pawn(3, 6, Color.BLACK),
      Pawn(4, 6, Color.BLACK), Pawn(5, 6, Color.BLACK),
      Pawn(6, 6, Color.BLACK), Pawn(7, 6, Color.BLACK),
      Rook(0, 7, Color.BLACK), Knight(1, 7, Color.BLACK),
      Bishop(2, 7, Color.BLACK), Queen(3, 7, Color.BLACK),
      King(4, 7, Color.BLACK), Bishop(5, 7, Color.BLACK),
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

}