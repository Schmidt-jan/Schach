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
  
  
  def moveValidWithoutKingCheck(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean = {
    gameField.getFigure(xNow, yNow) match {
      case Some(figure: Pawn) => validPawnWithoutKingCheck(figure, xNext, yNext)
      case Some(figure: Rook) => validRookWithoutKingCheck(figure, xNext, yNext)
      case Some(figure: Knight) => validKnightWithoutKingCheck(figure, xNext, yNext)
      case Some(figure: Bishop) => validBishopWithoutKingCheck(figure, xNext, yNext)
      case Some(figure: Queen) => validQueenWithoutKingCheck(figure, xNext, yNext)
      case Some(figure: King) => validKingWithoutKingCheck(figure, xNext, yNext)
      case None => false
    }
  }
  
  def validPawnWithoutKingCheck(figure: Pawn, xNext: Int, yNext: Int): Boolean = {
    //TODO add support for checking other figures
    if ((figure.color == Color.BLACK && figure.y < yNext) || (figure.color == Color.WHITE && figure.y > yNext)) {
      false
    }
    //walks straight on
    else if (Math.abs(figure.x - xNext) == 0 && gameField.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext)) {
      if (figure.hasBeenMoved) Math.abs(figure.y - yNext) == 1
      else Math.abs(figure.y - yNext) <= 2
    }
    //walks diagonal -> needs to check someone
    else if (Math.abs(xNext - figure.x) == 1 && Math.abs(yNext - figure.y) == 1) {
      val ret = gameField.getFigure(xNext, yNext).isInstanceOf[Some[Figure]]
      return ret
    } else false
  }

  def validRookWithoutKingCheck(figure: Rook, xNext: Int, yNext: Int): Boolean = {
    gameField.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext)
  }

  def validKnightWithoutKingCheck(figure: Knight, xNext: Int, yNext: Int): Boolean = {
    if (Math.abs(figure.x - xNext) == 1 && Math.abs(figure.y - yNext) == 2 ||
      (Math.abs(figure.x - xNext) == 2 && Math.abs(figure.y - yNext) == 1)) {
      return true
    }
    false
  }

  def validBishopWithoutKingCheck(figure: Bishop, xNext: Int, yNext: Int): Boolean = {
    gameField.wayToIsFreeDiagonal(figure.x, figure.y, xNext, yNext)
  }

  def validQueenWithoutKingCheck(figure: Queen, xNext: Int, yNext: Int): Boolean = {
    if (figure.x == xNext || figure.y == yNext) {
      gameField.wayToIsFreeStraight(figure.x, figure.y, xNext, yNext)
    } else {
      gameField.wayToIsFreeDiagonal(figure.x, figure.y, xNext, yNext)
    }
  }

  def validKingWithoutKingCheck(figure: King, xNext: Int, yNext: Int): Boolean = {
    Math.abs(figure.x - xNext) <= 1 && Math.abs(figure.y - yNext) <= 1
  }

  //Benni
  //xPos Move Option for diagonal Hit has to be added later
  def validPawn(figure: Pawn, xNext: Int, yNext: Int): Boolean = {
    validPawnWithoutKingCheck(figure, xNext, yNext) && gameField.moveToFieldAllowed(xNext, yNext, figure)
  }

  def validRook(figure: Rook, xNext: Int, yNext: Int): Boolean = {
    validRookWithoutKingCheck(figure, xNext, yNext) &&
      gameField.moveToFieldAllowed(xNext, yNext, figure)
  }

  def validKnight(figure: Knight, xNext: Int, yNext: Int): Boolean = {
    validKnightWithoutKingCheck(figure, xNext, yNext) && 
      gameField.moveToFieldAllowed(xNext, yNext, figure)
  }
  
  //Jan
  def validBishop(figure: Bishop, xNext: Int, yNext: Int): Boolean = {
    validBishopWithoutKingCheck(figure, xNext, yNext) && gameField.moveToFieldAllowed(xNext, yNext, figure)
  }

  def validQueen(figure: Queen, xNext: Int, yNext: Int): Boolean = {
    validQueenWithoutKingCheck(figure, xNext, yNext) && gameField.moveToFieldAllowed(xNext, yNext, figure)
  }

  def validKing(figure: King, xNext: Int, yNext: Int): Boolean = {
    validKingWithoutKingCheck(figure, xNext, yNext) && gameField.moveToFieldAllowed(xNext, yNext, figure)
  }

}