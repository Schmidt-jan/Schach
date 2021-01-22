package Schach.model.figureComponent

import java.awt.Color

trait Figure {
  val x  : Int
  val y : Int
  val color : Color
  var checked: Boolean = false

  override def equals(input: Any): Boolean = {
    input match {
      case obj: Figure =>
        (obj.x == this.x) && (obj.y == this.y) && (obj.color == this.color) && (obj.checked == this.checked)
      case _ => false
    }
  }
}

object Figure {
  def apply(kind : String, x : Int, y : Int, color : Color): Figure = kind.toLowerCase match {
    case "pawn" => Pawn(x, y, color)
    case "knight" => Knight(x, y, color)
    case "rook" => Rook(x, y, color)
    case "queen" => Queen(x, y, color)
    case "bishop" => Bishop(x, y, color)
    case "king" => King(x, y, color)
  }
}