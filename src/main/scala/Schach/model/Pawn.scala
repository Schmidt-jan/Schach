package Schach.model

import java.awt.Color

class Pawn(posX: Int,posY : Int, override val color: Color) extends Figure (posX, posY, color) {

  var firstMove = true
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
