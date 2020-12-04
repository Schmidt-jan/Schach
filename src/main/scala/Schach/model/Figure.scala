package Schach.model

import java.awt.Color

trait Figure {
  val x  : Int
  val y : Int
  val color : Color
  val checked : Boolean = false

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