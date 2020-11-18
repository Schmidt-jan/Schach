package Schach

import java.awt.Color

case class GameField() {
  var gameField: Vector[Figure] = Vector(
      Rook(0, 0, Color.WHITE), Knight(1, 0, Color.WHITE), Bishop(2, 0, Color.WHITE), King(3, 0, Color.WHITE),
      Queen(4, 0, Color.WHITE), Bishop(5, 0, Color.WHITE), Knight(6, 0, Color.WHITE), Rook(7, 0, Color.WHITE),
      Pawn(0, 1, Color.WHITE), Pawn(1, 1, Color.WHITE), Pawn(2, 1, Color.WHITE), Pawn(3, 1, Color.WHITE),
      Pawn(4, 1, Color.WHITE), Pawn(5, 1, Color.WHITE), Pawn(6, 1, Color.WHITE), Pawn(7, 1, Color.WHITE),

      Pawn(0, 6, Color.BLACK), Pawn(1, 6, Color.BLACK), Pawn(2, 6, Color.BLACK), Pawn(3, 6, Color.BLACK),
      Pawn(4, 6, Color.BLACK), Pawn(5, 6, Color.BLACK), Pawn(6, 6, Color.BLACK), Pawn(7, 6, Color.BLACK),
      Rook(0, 7, Color.BLACK), Knight(1, 7, Color.BLACK), Bishop(2, 7, Color.BLACK), King(3, 7, Color.BLACK),
      Queen(4, 7, Color.BLACK), Bishop(5, 7, Color.BLACK), Knight(6, 7, Color.BLACK), Rook(7, 7, Color.BLACK))


  def validPawn(figure: Pawn, xNext: Int, yNext: Int) = true
  def validRook(figure: Rook, xNext: Int, yNext: Int) = true
  def validKnight(figure: Knight, xNext: Int, yNext: Int) = true
  def validBishop(figure: Bishop, xNext: Int, yNext: Int) = true
  def validQueen(figure: Queen, xNext: Int, yNext: Int) = true
  def validKing(figure: King, xNext: Int, yNext: Int) = true


  def moveValid(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean = {
    getFigure(xNow, yNow) match {
      case Some(figure : Pawn) => validPawn(figure, xNext, yNext)
      case Some(figure : Rook) => validRook(figure, xNext, yNext)
      case Some(figure : Knight) => validKnight(figure, xNext, yNext)
      case Some(figure : Bishop) => validBishop(figure, xNext, yNext)
      case Some(figure : Queen) => validQueen(figure, xNext, yNext)
      case Some(figure : King) => validKing(figure, xNext, yNext)
      case None => false
    }
  }


  def moveTo(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Vector[Figure] = if (moveValid(xNow, yNow, xNext, yNext)) {
    val figure = getFigure(xNow, yNow).get
    figure match {
      case p: Pawn => gameField.filter(_ != figure) :+ Pawn(xNext, yNext, figure.color)
      case p: Rook => gameField.filter(_ != figure) :+ Rook(xNext, yNext, figure.color)
      case p: Knight => gameField.filter(_ != figure) :+ Knight(xNext, yNext, figure.color)
      case p: Bishop => gameField.filter(_ != figure) :+ Bishop(xNext, yNext, figure.color)
      case p: Queen => gameField.filter(_ != figure) :+ Queen(xNext, yNext, figure.color)
      case p: King => gameField.filter(_ != figure) :+ King(xNext, yNext, figure.color)
    }
  } else {
    gameField
  }

  def setGameField(vector: Vector[Figure]): Unit = {
    gameField = vector
  }

  def getFigure(xPos: Int, yPos: Int): Option[Figure] = {
    gameField.filter(_.x == xPos).find(_.y == yPos)
  }

  override def toString: String = {

    val build = new StringBuilder
    build.append("\tA\tB\tC\tD\tE\tF\tG\tH\n")
    build.append("\t──────────────────────────────\n")

    for (y <- Range(7, -1, -1)) {
      build.append(y + 1).append(" │\t")

      val row = gameField.filter(_.y == y)

      for (x <- 0 to 7) {
        row.find(_.x == x) match {
          case Some(value) => build.append(value.toString + "\t")
          case None => build.append("─\t")
        }
      }
      build.append("\n")
    }
    build.toString
  }

}
