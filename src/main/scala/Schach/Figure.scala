package Schach

trait Figure {
  var x  : Int = _
  var y : Int = _

  def moveTo(nextX : Int, nextY : Int): Boolean = {
    x = nextX
    y = nextY
    true
  }

  def moveIsValid(x : Int, y : Int): Boolean = {
    false
  }
}
