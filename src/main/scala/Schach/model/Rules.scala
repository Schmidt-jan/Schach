package Schach.model

import java.awt.Color

case class Rules(gameField: GameField) {

  def moveValidFigure(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean = {
    gameField.getFigure(xNow, yNow) match {
      case Some(figure: Pawn) => validPawn(figure, xNext, yNext)
      case Some(figure: Rook) => validRook(figure, xNext, yNext)
      case Some(figure: Knight) => validKnight(figure, xNext, yNext)
      case Some(figure: Bishop) => validBishop(figure, xNext, yNext)
      case Some(figure: Queen) => validQueen(figure, xNext, yNext)
      case Some(figure: King) => validKing(figure, xNext, yNext)
      case None => false
    }
  }

  //Benni
  //xPos Move Option for diagonal Hit has to be added later
  def validPawn(figure: Pawn, xNext: Int, yNext: Int): Boolean = {
    if ((figure.color == Color.BLACK && figure.y < yNext) || (figure.color == Color.WHITE && figure.y > yNext)) {
      false
    }
    else if (figure.hasBeenMoved) {
      if (Math.abs(figure.x - xNext) == 0 && Math.abs(figure.y - yNext) == 1) {
        gameField.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext) && gameField.moveToFieldAllowed(xNext, yNext, figure.color)
      } else false
    } else {
      if (Math.abs(figure.x - xNext) == 0 && Math.abs(figure.y - yNext) <= 2) {
        gameField.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext) && gameField.moveToFieldAllowed(xNext, yNext, figure.color)
      } else false
    }

  }

  def validRook(figure: Rook, xNext: Int, yNext: Int): Boolean = {
    gameField.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext) && gameField.moveToFieldAllowed(xNext, yNext, figure.color)
  }

  def validKnight(figure: Knight, xNext: Int, yNext: Int): Boolean = {
    if (Math.abs(figure.x - xNext) == 1 && Math.abs(figure.y - yNext) == 2) {
      gameField.moveToFieldAllowed(xNext, yNext, figure.color)
    } else if (Math.abs(figure.x - xNext) == 2 && Math.abs(figure.y - yNext) == 1) {
      gameField.moveToFieldAllowed(xNext, yNext, figure.color)
    } else false
  }


  //Jan
  def validBishop(figure: Bishop, xNext: Int, yNext: Int): Boolean = {
    val first = gameField.wayToIsFreeDiagonal(figure.x, figure.y, xNext, yNext)
    val second = gameField.moveToFieldAllowed(xNext, yNext, figure.color)
    first && second
  }

  def validQueen(figure: Queen, xNext: Int, yNext: Int): Boolean = {
    if (figure.x == xNext || figure.y == yNext) {
      gameField.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext) && gameField.moveToFieldAllowed(xNext, yNext, figure.color)
    } else {
      gameField.wayToIsFreeDiagonal(figure.x, figure.y, xNext, yNext) && gameField.moveToFieldAllowed(xNext, yNext, figure.color)
    }
  }

  def validKing(figure: King, xNext: Int, yNext: Int): Boolean = {
    if (Math.abs(figure.x - xNext) <= 1 && Math.abs(figure.y - yNext) <= 1) {

      gameField.moveToFieldAllowed(xNext, yNext, figure.color)
    } else false
  }

}