package Schach.model.figureComponent

import Schach.model.figureComponent.Figure

import java.awt.Color

case class Bishop(x: Int, y : Int, color: Color) extends Figure {

  override def toString: String = {
    color match {
      case Color.BLACK => "♝"
      case Color.WHITE => "♗"
    }
  }
}