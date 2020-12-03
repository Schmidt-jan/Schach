package Schach.model

import java.awt.Color

case class Rules() {
  private val instance = GameField.getInstance() : GameField

  def moveValidFigure(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean = {
    instance.getFigure(xNow, yNow) match {
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
        instance.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext) && instance.moveToFieldAllowed(xNext, yNext, figure.color)
      } else false
    } else {
      if (Math.abs(figure.x - xNext) == 0 && Math.abs(figure.y - yNext) <= 2) {
        instance.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext) && instance.moveToFieldAllowed(xNext, yNext, figure.color)
      } else false
    }

  }

  def validRook(figure: Rook, xNext: Int, yNext: Int): Boolean = {
    instance.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext) && instance.moveToFieldAllowed(xNext, yNext, figure.color)
  }

  def validKnight(figure: Knight, xNext: Int, yNext: Int): Boolean = {
    if (Math.abs(figure.x - xNext) == 1 && Math.abs(figure.y - yNext) == 2) {
      instance.moveToFieldAllowed(xNext, yNext, figure.color)
    } else if (Math.abs(figure.x - xNext) == 2 && Math.abs(figure.y - yNext) == 1) {
      instance.moveToFieldAllowed(xNext, yNext, figure.color)
    } else false
  }


  //Jan
  def validBishop(figure: Bishop, xNext: Int, yNext: Int): Boolean = {
    val first = instance.wayToIsFreeDiagonal(figure.x, figure.y, xNext, yNext)
    val second = instance.moveToFieldAllowed(xNext, yNext, figure.color)
    first && second
  }

  def validQueen(figure: Queen, xNext: Int, yNext: Int): Boolean = {
    if (figure.x == xNext || figure.y == yNext) {
      instance.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext) && instance.moveToFieldAllowed(xNext, yNext, figure.color)
    } else {
      instance.wayToIsFreeDiagonal(figure.x, figure.y, xNext, yNext) && instance.moveToFieldAllowed(xNext, yNext, figure.color)
    }
  }

  def validKing(figure: King, xNext: Int, yNext: Int): Boolean = {
    if (Math.abs(figure.x - xNext) <= 1 && Math.abs(figure.y - yNext) <= 1) {

      instance.moveToFieldAllowed(xNext, yNext, figure.color)
    } else false
  }

}
