package Schach

import java.awt.Color

class Pawn(override var posX: Char, override var posY : Int, override val color: Color) extends Figure (posX, posY, color) {

  var firstMove = true;

  override def toString: String = {
    color match {
      case Color.BLACK => "♟︎"
      case Color.WHITE => "♙"
    }
  }

}
