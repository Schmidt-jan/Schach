package Schach

import java.awt.Color

case class Bishop(posX: Int, posY : Int, color: Color) extends Figure {

  override def toString: String = {
    color match {
      case Color.BLACK => "♝"
      case Color.WHITE => "♗"
    }
  }
}
