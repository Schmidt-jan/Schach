package Schach.aview

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class TuiSpec extends AnyWordSpec with Matchers {

  "A Tui" should {
    val tui = new Tui
    val input = "A1 F2"
    "convert a letter into a number for the GameField" in {
      tui.getPoint(input.charAt(0)) should be(0)
      tui.getPoint(input.charAt(1)) should be (0)
      tui.getPoint(input.charAt(3)) should be(5)
      tui.getPoint(input.charAt(4)) should be (1)
    }
    "control the input via regex" in {
      tui.controlInput(input) should be(true)
      tui.controlInput("abc def") should be(false)
      tui.controlInput("1A 2B") should be(false)
      tui.controlInput("a1 a2") should be(false)
    }
    "read input for the moveTo method via getPoint()" in {
      val read = tui.readInput(input)
      read(0) should be(0)
      read(1) should be(0)
      read(2) should be(5)
      read(3) should be(1)
    }
  }
}
