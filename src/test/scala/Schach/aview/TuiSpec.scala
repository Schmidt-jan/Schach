package Schach.aview

import Schach.controller.Controller
import Schach.model.GameField
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class TuiSpec extends AnyWordSpec with Matchers {

  "A Tui" should {
    val controller = new Controller(new GameField)
    val tui = new Tui(controller)
    val input = "A1 F2"
    var field = new GameField()
    "convert a letter into a number for the GameField" in {
      tui.getPoint(input.charAt(0)) should be(0)
      tui.getPoint(input.charAt(1)) should be (0)
      tui.getPoint(input.charAt(3)) should be(5)
      tui.getPoint(input.charAt(4)) should be (1)
    }
    "control the input via regex" in {
      controller.controlInput("A1") should be(true)
      controller.controlInput("abc def") should be(false)
      controller.controlInput("1A 2B") should be(false)
      controller.controlInput("a1 a2") should be(false)
    }
    "read input for the movePiece method via getPoint()" in {
      val read = tui.readInput(input)
      read(0) should be(0)
      read(1) should be(0)
      read(2) should be(5)
      read(3) should be(1)
    }
    "create a new GameField on command 'new'" in {
      tui.interactWithUser("new")
      controller.gameField shouldBe a [GameField]
    }

  }

}
