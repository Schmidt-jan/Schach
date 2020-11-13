package Schach.model

import java.awt.Color

class Bishop(posX: Int, posY : Int, override val color: Color) extends Figure (posX, posY, color) {

  override def toString: String = {
    color match {
      case Color.BLACK => "♝"
      case Color.WHITE => "♗"
    }
  }
}
