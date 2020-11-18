package Schach

import java.awt.Color

trait Figure {
  val x  : Int
  val y : Int
  val color : Color
  val checked : Boolean = false
}
