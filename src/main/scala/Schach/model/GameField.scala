package Schach.model

import java.awt.Color

import scala.collection.immutable._
import scala.util.control._

class GameField(private var gameField: Vector[Figure]) {



  private var validPlayer = Color.WHITE

  def this() = this(Vector())

  def addFigures(figures : Vector[Figure]) : GameField = {
    for (in <- gameField) {
      if (figures.contains(in)) return this
    }
    gameField = gameField.appendedAll(figures)
    this
  }

  def getFigures: Vector[Figure] = {
    gameField
  }

  def moveTo(xNow: Int, yNow: Int, xNext: Int, yNext: Int): GameField = {
    if (getFigure(xNow, yNow).isEmpty) return this

    getFigure(xNext, yNext) match {
      case Some(fig) => fig.checked = true
      case None =>
    }

    val figure = getFigure(xNow, yNow).get
    figure match {
      case _: Pawn => gameField = gameField.filter(!_.equals(figure)) :+ Pawn(xNext, yNext, figure.color, Some(true))
        this
      case _: Rook => gameField = gameField.filter(!_.equals(figure)) :+ Rook(xNext, yNext, figure.color)
        this
      case _: Knight => gameField = gameField.filter(!_.equals(figure)) :+ Knight(xNext, yNext, figure.color)
        this
      case _: Bishop => gameField = gameField.filter(!_.equals(figure)) :+ Bishop(xNext, yNext, figure.color)
        this
      case _: Queen => gameField = gameField.filter(!_.equals(figure)) :+ Queen(xNext, yNext, figure.color)
        this
      case _: King => gameField = gameField.filter(!_.equals(figure)) :+ King(xNext, yNext, figure.color)
        this
    }
  }

  def moveValid(xNow: Int, yNow: Int, xNext: Int, yNext: Int): Boolean = {
    getFigure(xNow, yNow) match {
      case Some(value) =>
        if (value.color != validPlayer) {
          println("Wrong player")
          return false
        }
      case None => return false
    }

    val rule = Rules(this)
    rule.moveValidFigure(xNow, yNow, xNext, yNext)
  }

  def moveToFieldAllowed(x: Int, y: Int, figure: Figure): Boolean = {
    val kingOfInterest = gameField.filter(_.isInstanceOf[King]).find(_.color == figure.color).get
    val check1 = !setSelfIntoCheck(figure, x, y, kingOfInterest)

    getFigure(x, y) match {
      case Some (figure2) =>
        val check2 = !figure2.isInstanceOf[King] && figure2.color != figure.color
        check1 && check2

      case None => check1
    }
  }

  def setSelfIntoCheck(figure: Figure, xNext : Int, yNext : Int, king: Figure): Boolean = {
    var output = false
    val loop = new Breaks

    val figuresEnimy = getFigures.filter(!_.checked).filter(_.color != king.color)

    val figureTo = getFigure(xNext, yNext)

    //simulate move and check for set yourself into check
    if (figureTo.isDefined) figureTo.get.checked = true

    moveTo(figure.x, figure.y, xNext, yNext)
    val rules  = Rules(this)
    loop.breakable {
      for (fig <- figuresEnimy) {
        if (rules.moveValidWithoutKingCheck(fig.x, fig.y, king.x, king.y)) {
          output = true
          loop.break
        }
      }
    }

    //reset changes
    moveTo(xNext, yNext, figure.x, figure.y)
    figure match {
      case pawn: Pawn => if (!pawn.hasBeenMoved) getFigure(figure.x, figure.y).get.asInstanceOf[Pawn].hasBeenMoved = false
      case _ =>
    }
    if (figureTo.isDefined) figureTo.get.checked = false
    output
  }

  def isCheckmate: Boolean = {
    false
  }

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

  def getPlayer: Color = validPlayer

  def setPlayer(color: Color): Color = {
    val before = validPlayer
    validPlayer = color
    before
  }

  def changePlayer(): Color = {
    validPlayer match {
      case Color.BLACK => validPlayer = Color.WHITE
      case Color.WHITE => validPlayer = Color.BLACK
    }
    validPlayer
  }

  def getFigure(xPos: Int, yPos: Int): Option[Figure] = {
    gameField.filter(_.checked == false).filter(_.x == xPos).find(_.y == yPos)
  }

  def clear() : Boolean = {
    validPlayer = Color.WHITE
    gameField = Vector.empty
    gameField.isEmpty
  }

  override def toString: String = {

    val build = new StringBuilder
    build.append("\tA\tB\tC\tD\tE\tF\tG\tH\n")
    build.append("\t──────────────────────────────\n")

    for (y <- Range(7, -1, -1)) {
      build.append(y + 1).append(" │\t")

      val row = gameField.filter(!_.checked).filter(_.y == y)

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