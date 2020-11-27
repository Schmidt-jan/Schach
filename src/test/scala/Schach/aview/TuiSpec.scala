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
      tui.getPoint('B') should be(1)
      tui.getPoint('C') should be(2)
      tui.getPoint('D') should be(3)
      tui.getPoint('E') should be(4)
      tui.getPoint('G') should be(6)
      tui.getPoint('H') should be(7)
      tui.getPoint('3') should be(2)
      tui.getPoint('4') should be(3)
      tui.getPoint('5') should be(4)
      tui.getPoint('6') should be(5)
      tui.getPoint('7') should be(6)
      tui.getPoint('8') should be(7)
      tui.getPoint('X') should be(-1)

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
    "move according to the input" in {
      tui.interactWithUser("move X")
    }

  }

}
