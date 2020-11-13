package Schach.model

import java.awt.Color

abstract class Figure (var posX : Int, var posY : Int, val color : Color) {

  def moveTo(x : Int, y : Int): Boolean = {
    false
  }

  def moveIsValid(x : Int, y : Int): Boolean = {
    false
  }
}
