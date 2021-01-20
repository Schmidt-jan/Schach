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

  /**
   * Adds the figures to the gameField if the gameField contains no one of the array
   * @param figures
   * @return gameField
   */
  def addFigures(figures : Vector[Figure]) : GameField

  /**
   *
   * @return all figures from the gameField, also those who are checked
   */
  def getFigures: Vector[Figure]

  /**
   *asdfddf
   * @param figure to convert
   * @param toFigure of the wanted type
   */
  def convertFigure(figure : Figure, toFigure : Figure)

  /**
   * Moves figure from (xNow, yNow) to (xNext, yNext).
   * If there is a figure at (xNext, yNext) it's gonna set checked.
   *
   * @param xNow x-value the figure stands at the moment
   * @param yNow y-value the figure stands at the moment
   * @param xNext x-value the figure should move to
   * @param yNext y-value the figure should move to
   * @return gameField
   */
  def moveTo(xNow: Int, yNow: Int, xNext: Int, yNext: Int): GameField

  /**
   * Checks if right player is moving. If true checks if the move is
   * allowed by the set rules
   *
   * @param xNow x-value the figure stands at the moment
   * @param yNow y-value the figure stands at the moment
   * @param xNext x-value the figure should move to
   * @param yNext y-value the figure should move to
   * @return true if the right player is moving and if the move is valid, else false
   */
  def moveValid(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean

  /**
   * Checks if you set yourself into check with this move and
   * whether the figure at (x, y) is a king of the other player
   *
   * @param x value of the field
   * @param y value of the field
   * @param figure you want to move
   * @return true if you not set yourself into check and there is no
   *         king of the other player
   */
  def moveToFieldAllowed(x: Int, y: Int, figure: Figure): Boolean

  /**
   * Checks if you set yourself into check by simulating that move and check
   * if any figure of the enimy checks you.
   *
   * @param figure you want to move
   * @param xNext x-value the figure should move to
   * @param yNext y-value the figure should move to
   * @return true if a player could check you after that move, else false
   */
  def setSelfIntoCheck(figure: Figure, xNext : Int, yNext : Int): Boolean

  /**
   * Checks if a pawn of any color has reached the end of the field (y = 7 || y = 0)
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
   * Checks if the player is checked by checking if any of the other players figures
   * could move to the King
   *
   * @param playerCol color of the player to check
   * @return true if checked, else false
   */
  def isChecked(playerCol: Color): Boolean

  /**
   * Checks if there is no possibility for the king to get out of check by checking
   * if any cells around him are free and he can move to, to get out of check
   *
   * @param playerCol color of the player to check
   * @return true if the king can't move anymore, else false
   */
  def isCheckmate(playerCol: Color): Boolean

  /**
   * Checks the adjacent cells and returns a list of those who are free
   *
   * @param figure
   * @return List free cells
   */
  def cellsFreeAroundFigure(figure: Figure) : List[(Int, Int)]

  /**
   * Checks if there are no figures on the straight way from (xNow, yNow) to (xNext, yNext).
   * Straight means if xNow = xNext or yNow = yNext.
   * It will not check if the cell at (xNext, yNext) is free!.
   *
   * @param xNow x-value the figure stands at the moment
   * @param yNow y-value the figure stands at the moment
   * @param xNext x-value the figure should move to
   * @param yNext y-value the figure should move to
   * @return true if the way is free
   */
  def wayToIsFreeStraight(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean

  /**
   * Checks if there are no figures on the diagonal way from (xNow, yNow) to (xNext, yNext).
   * Diagonal means if xNow = xNext + n and yNow = yNext + n.
   * It will not check if the cell at (xNext, yNext) is free!.
   *
   * @param xNow x-value the figure stands at the moment
   * @param yNow y-value the figure stands at the moment
   * @param xNext x-value the figure should move to
   * @param yNext y-value the figure should move to
   * @return true if the way is free
   */
  def wayToIsFreeDiagonal(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean

  /**
   * The color of the player which turn it is
   *
   * @return the color of the valid player
   */
  def getPlayer: Color

  /**
   * Sets the valid players by the  color
   *
   * @param color of the vaild Player
   * @return the old Color
   */
  def setPlayer(color: Color): Color

  /**
   * Changes the valid players color
   * White -> Black
   * Black -> White
   * @return the color of the new valid player
   */
  def changePlayer(): Color

  /**
   *
   * @param xPos value of the field
   * @param yPos value of the field
   * @return the player at this cell which is not checked
   */
  def getFigure(xPos: Int, yPos: Int): Option[Figure]

  /**
   * Sets the valid player to White.
   * Clears the gameField
   * @return if the gameField is empty
   */
  def clear() : Boolean

  /**
   * sets the status of the gameField/ game
   * @param newStatus
   */
  def setStatus(newStatus : Int)

  /**
   *
   * @return the gameField/ game status
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
   * Sets up a normal chess field.
   */
  def makeGameField() : Unit

  /**
   *
   * @return actual gameField
   */
  def getGameField: GameField

  /**
   * Sets up a normal chess field.
   * @return new gameField
   */
  def getNewGameField: GameField
}
