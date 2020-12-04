package Schach.model

import java.awt.Color


class GameField(private var gameField: Vector[Figure]) {



  def addFigures(figures : Vector[Figure]) : GameField = {
    for (in <- gameField) {
      if (figures.contains(in)) return this
    }
    gameField = gameField.appendedAll(figures)
    this
  }

  def getFigures(): Vector[Figure] = {
    gameField
  }

  def moveTo(xNow: Int, yNow: Int, xNext: Int, yNext: Int): GameField = {
    if (getFigure(xNow, yNow).isEmpty) return this
    val figure = getFigure(xNow, yNow).get
    figure match {
      case p: Pawn => gameField = gameField.filter(_ != figure) :+ Pawn(xNext, yNext, figure.color, Some(true))
        this
      case p: Rook => gameField = gameField.filter(_ != figure) :+ Rook(xNext, yNext, figure.color)
        this
      case p: Knight => gameField = gameField.filter(_ != figure) :+ Knight(xNext, yNext, figure.color)
        this
      case p: Bishop => gameField = gameField.filter(_ != figure) :+ Bishop(xNext, yNext, figure.color)
        this
      case p: Queen => gameField = gameField.filter(_ != figure) :+ Queen(xNext, yNext, figure.color)
        this
      case p: King => gameField = gameField.filter(_ != figure) :+ King(xNext, yNext, figure.color)
        this
    }
  }

  def moveValid(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean = {
    val rule = Rules(this)
    rule.moveValidFigure(xNow, yNow, xNext, yNext)
  }

  def moveToFieldAllowed(x: Int, y: Int, color: Color): Boolean = getFigure(x, y) match {
    case Some(figure2) => !figure2.isInstanceOf[King] && figure2.color != color && !isCheck(gameField.filter(_.isInstanceOf[King]).find(_.color == color).get)
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

  def clear() : Boolean = {
    gameField = Vector.empty
    gameField.isEmpty
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

object GameField {
  private var instance : GameField = null

  def getInstance:GameField = {
    if (instance == null) {
      instance= new GameField(Vector())
    }
    instance
  }
}