package Schach

import java.awt.Color

case class Rook(override val posX: Char, override val posY : Int, override val color: Color) extends Figure (posX, posY, color) {

  override def toString: String = {
    color match {
      case Color.BLACK => "♜"
      case Color.WHITE => "♖"
    }
  }
}
