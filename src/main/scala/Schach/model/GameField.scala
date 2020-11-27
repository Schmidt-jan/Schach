package Schach.model

import java.awt.Color


class GameField(gameField: Vector[Figure]) {


  def this() = this(Vector(
    Rook(0, 0, Color.WHITE), Knight(1, 0, Color.WHITE), Bishop(2, 0, Color.WHITE), King(3, 0, Color.WHITE),
    Queen(4, 0, Color.WHITE), Bishop(5, 0, Color.WHITE), Knight(6, 0, Color.WHITE), Rook(7, 0, Color.WHITE),
    Pawn(0, 1, Color.WHITE), Pawn(1, 1, Color.WHITE), Pawn(2, 1, Color.WHITE), Pawn(3, 1, Color.WHITE),
    Pawn(4, 1, Color.WHITE), Pawn(5, 1, Color.WHITE), Pawn(6, 1, Color.WHITE), Pawn(7, 1, Color.WHITE),

    Pawn(0, 6, Color.BLACK), Pawn(1, 6, Color.BLACK), Pawn(2, 6, Color.BLACK), Pawn(3, 6, Color.BLACK),
    Pawn(4, 6, Color.BLACK), Pawn(5, 6, Color.BLACK), Pawn(6, 6, Color.BLACK), Pawn(7, 6, Color.BLACK),
    Rook(0, 7, Color.BLACK), Knight(1, 7, Color.BLACK), Bishop(2, 7, Color.BLACK), King(3, 7, Color.BLACK),
    Queen(4, 7, Color.BLACK), Bishop(5, 7, Color.BLACK), Knight(6, 7, Color.BLACK), Rook(7, 7, Color.BLACK)))


  def moveTo(xNow: Int, yNow: Int, xNext: Int, yNext: Int): GameField = {
    val figure = getFigure(xNow, yNow).get
    figure match {
      case p: Pawn => new GameField(gameField.filter(_ != figure) :+ Pawn(xNext, yNext, figure.color, Some(true)))
      case p: Rook => new GameField(gameField.filter(_ != figure) :+ Rook(xNext, yNext, figure.color))
      case p: Knight => new GameField(gameField.filter(_ != figure) :+ Knight(xNext, yNext, figure.color))
      case p: Bishop => new GameField(gameField.filter(_ != figure) :+ Bishop(xNext, yNext, figure.color))
      case p: Queen => new GameField(gameField.filter(_ != figure) :+ Queen(xNext, yNext, figure.color))
      case p: King => new GameField(gameField.filter(_ != figure) :+ King(xNext, yNext, figure.color))
      case _ => this
    }
  }

  def moveValid(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean = {
    val rule = Rules(this)
    rule.moveValidFigure(xNow, yNow, xNext, yNext)
  }

  def moveToFieldAllowed(x: Int, y: Int, color: Color): Boolean = getFigure(x, y) match {
    case Some(figure2) => !figure2.isInstanceOf[King] &&
      figure2.color != color &&
      !isCheck(gameField.filter(_.isInstanceOf[King]).find(_ == color).get)
    case None => true
  }

  def isCheck(king: Figure): Boolean = false

  def wayToIsFreeStraight(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean = {
    if (xNow == xNext && yNow == yNext) return false

    //vertical move
    if (xNow == xNext) {
      var incY = 1
      if (yNow > yNext) incY = -1

      for (y <- Range(yNow + incY, yNext, incY)) {
        if (gameField.exists(input => input.y == y && input.x == xNow)) return false
      }
      return true
    }

    //horizontal move
    else if (yNow == yNext) {
      var incX = 1
      if (xNow > xNext) incX = -1

      for (x <- Range(xNow + incX, xNext, incX)) {
        if (gameField.exists(input => input.x == x && input.y == yNow)) return false
      }
      return true
    }
    false

  }

  def wayToIsFreeDiagonal(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean = {
    if ((Math.abs(xNow - xNext) != Math.abs(yNow - yNext)) || (xNow == xNext || yNow == yNext)) {
      return false
    }

    //standard move diagonal right up
    var incX = 1
    var incY = 1

    //move diagonal left
    if (xNext < xNow) {
      incX = -1
      if (yNext < yNow) incY = -1 //move down
    }
    //move diagonal right
    else if (yNext < yNow) incY = -1 //move down

    var y = yNow + incY

    for (x <- Range(xNow + incX, xNext, incX)) {
      if (!gameField.exists(input => input.x == x && input.y == y)) y += incY
      else return false
    }
    true
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