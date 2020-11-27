package Schach.model

import java.awt.Color

case class Pawn(x: Int, y : Int, color: Color, moved: Option[Boolean] = None) extends Figure {
  //def apply(x: Int, y: Int, color: Color, moved: Boolean): Pawn = new Pawn(x, y, color, Option(moved))
  var hasBeenMoved = false
  if (moved.contains(true)) hasBeenMoved = true
  /*
  ♟
   */
  override def toString: String = {
    color match {
      case Color.BLACK => "♟"
      case Color.WHITE => "♙"
    }
  }

}
