package Schach

import java.awt.Color

class Queen(posX: Char, posY : Int, override val color: Color) extends Figure (posX, posY, color) {

  override def toString: String = {
    color match {
      case Color.BLACK => "♛"
      case Color.WHITE => "♕"
    }
  }
}
