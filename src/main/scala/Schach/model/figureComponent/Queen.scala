package Schach.model.figureComponent

import java.awt.Color

case class Queen(x: Int, y : Int, color: Color) extends Figure {

  override def toString: String = {
    color match {
      case Color.BLACK => "♛"
      case Color.WHITE => "♕"
    }
  }
}
