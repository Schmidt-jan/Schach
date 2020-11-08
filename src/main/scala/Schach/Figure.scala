package Schach

import java.awt.Color

abstract class Figure (var posX : Char, var posY : Int, val color : Color) {

  def moveTo(x : Char, y : Int): Boolean = {
    false
  }

  def moveIsValid(x : Char, y : Int): Boolean = {
    false
  }
}
