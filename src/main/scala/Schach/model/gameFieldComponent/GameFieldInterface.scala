package Schach.model.gameFieldComponent

import java.awt.Color

import Schach.model.figureComponent.{Figure, Pawn}
import Schach.model.gameFieldComponent.gameFieldBaseImpl.GameField



trait GameFieldInterface {
  val RUNNING = 0
  val CHECKED = 1
  val CHECKMATE = 2
  val MOVE_ILLEGAL = 3
  val PAWN_REACHED_END = 4

  /** Adds the Figures to the gameField if the gameField does not contain them yet
   *
   * @param figures - Vector of Figures to adjust the current gameField
   * @return new gameField
   */
  def addFigures(figures : Vector[Figure]) : GameField

  /**
   *
   * @return All Figures from the gameField, also those who are checked
   */
  def getFigures: Vector[Figure]

  /**
   *  Converts one Figure to another
   *
   *  (Mostly used for converting a Pawn into Queen, Rook, Bishop or Knight after reaching the end)
   * @param figure to convert
   * @param toFigure of the wanted type
   */
  def convertFigure(figure : Figure, toFigure : Figure)

  /**
   * Moves Figure from (xNow, yNow) to (xNext, yNext).
   * If there is a (enemy) Figure at (xNext, yNext) it's going to be replaced.
   *
   * @param xNow x-value the Figure stands at the moment
   * @param yNow y-value the Figure stands at the moment
   * @param xNext x-value the Figure should move to
   * @param yNext y-value the Figure should move to
   * @return gameField
   */
  def moveTo(xNow: Int, yNow: Int, xNext: Int, yNext: Int): GameField

  /**
   * Confirms if right Player is moving. If true validates if the move is
   * allowed by the set rules.
   *
   * @param xNow x-value the Figure stands at the moment
   * @param yNow y-value the Figure stands at the moment
   * @param xNext x-value the Figure should move to
   * @param yNext y-value the Figure should move to
   * @return true if the right Player is moving and if the move is valid, else false
   */
  def moveValid(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean

  /**
   * Confirms if you set yourself into check with this move and
   * whether the Figure at (x, y) is a king of the other Player
   *
   * @param x value of the field
   * @param y value of the field
   * @param figure you want to move
   * @return true if you not set yourself into check and there is no
   *         king of the other Player
   */
  def moveToFieldAllowed(x: Int, y: Int, figure: Figure): Boolean

  /**
   *  Verifies if you set yourself into check by simulating that move and inspect
   *  if any figure of the enemy checks you.
   *
   * @param figure you want to move
   * @param xNext x-value the Figure should move to
   * @param yNext y-value the Figure should move to
   * @return true if a Player could check you after that move, else false
   */
  def setSelfIntoCheck(figure: Figure, xNext : Int, yNext : Int): Boolean

  /**
   *  Inspects if a Pawn of any color has reached the end of the field (y = 7 || y = 0)
   *
   * @return true if a Pawn has reached the end, else false
   */
  def pawnHasReachedEnd() : Boolean

  /**
   *
   * @return the Pawn at the end
   */
  def getPawnAtEnd(): Pawn

  /**
   *  Inspects if the Player is checked by confirming wether any of the other Player's Figures
   *  could move to the King
   *
   * @param playerCol color of the to be examined Player
   * @return true if checked, else false
   */
  def isChecked(playerCol: Color): Boolean

  /**
   *  Reviews wether there is no possibility for the king to get out of check by verifying
   *  if any cells around him are free that he can move to, to get out of check
   *
   * @param playerCol color of the Player to examine
   * @return true if the king can't move anymore, else false
   */
  def isCheckmate(playerCol: Color): Boolean

  /**
   *  Checks the adjacent cells and returns a list of those who are free
   *
   * @param figure which adjacent cells should be reviewed
   * @return List of free cells
   */
  def cellsFreeAroundFigure(figure: Figure) : List[(Int, Int)]

  /**
   *  Checks if there are no Figures on the straight way from (xNow, yNow) to (xNext, yNext).
   *  Straight means if xNow = xNext or yNow = yNext.
   *  It will not verify if the cell at (xNext, yNext) is free!
   *
   * @param xNow x-value the Figure stands at the moment
   * @param yNow y-value the Figure stands at the moment
   * @param xNext x-value the Figure should move to
   * @param yNext y-value the Figure should move to
   * @return true if the way is free
   */
  def wayToIsFreeStraight(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean

  /**
   *  Checks if there are no Figures on the diagonal way from (xNow, yNow) to (xNext, yNext).
   *  Diagonal means if xNow = xNext + n and yNow = yNext + n.
   *  It will not verify if the cell at (xNext, yNext) is free!
   *
   * @param xNow x-value the Figure stands at the moment
   * @param yNow y-value the Figure stands at the moment
   * @param xNext x-value the Figure should move to
   * @param yNext y-value the Figure should move to
   * @return true if the way is free
   */
  def wayToIsFreeDiagonal(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean

  /**
   *
   *
   * @return Color of the Player who has to make his turn now (the valid Player)
   */
  def getPlayer: Color

  /**
   *  Sets the Player who has to make his turn now (the valid Player)
   *
   * @param color of the valid Player
   * @return the old Player's Color
   */
  def setPlayer(color: Color): Color

  /**
   *  Changes the valid Player's color
   *
   *  e.g. If it was White's turn before it is now Black's turn
   *
   * White -> Black
   * Black -> White
   * @return The color of the new valid Player
   */
  def changePlayer(): Color

  /** Fetches the Figure at the specified position.
   *
   *  With {{{ getFigure(x, y).get }}} you are able to fetch the actual Figure.
   *  But an Exception will be thrown if the cell is empty and the call returns Option[None]!
   * @param xPos value of the field
   * @param yPos value of the field
   * @return Option[Figure] if there is a Figure, Option[None] if the cell is empty
   */
  def getFigure(xPos: Int, yPos: Int): Option[Figure]

  /**
   *  Sets the valid Player to White.
   *  Clears the gameField.
   *
   * @return true if the gameField is now empty
   */
  def clear() : Boolean

  /**
   *  Sets the status of the gameField/ ongoing Game
   *
   * @param newStatus
   */
  def setStatus(newStatus : Int)

  /**
   *
   * @return the gameField/ Game status
   */
  def getStatus() : Int

  /**
   *
   * @return String representation of the gameField
   */
  def toString: String

}

trait ChessGameFieldBuilderInterface {

  /**
   * Sets up a normal chess field
   */
  def makeGameField() : Unit

  /**
   *
   * @return actual gameField
   */
  def getGameField: GameField

  /**
   *  Sets up a new chess field.
   *
   * @return new gameField
   */
  def getNewGameField: GameField
}
