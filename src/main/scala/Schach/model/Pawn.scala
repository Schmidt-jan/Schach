package Schach.model

import java.awt.Color

case class Pawn(x: Int, y : Int, color: Color) extends Figure {

  var firstMove = true

  override def toString: String = {
    color match {
      case Color.BLACK => "♟︎"
      case Color.WHITE => "♙"
    }
  }

}