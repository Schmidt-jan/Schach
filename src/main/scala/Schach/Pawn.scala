package Schach

import java.awt.Color

case class Pawn(posX: Int, posY : Int, color: Color) extends Figure {

  var firstMove = true

  override def toString: String = {
    color match {
      case Color.BLACK => "♟︎"
      case Color.WHITE => "♙"
    }
  }

}
