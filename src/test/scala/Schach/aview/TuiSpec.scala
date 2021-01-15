package Schach.aview

import Schach.GameFieldModule
import Schach.controller.controllerComponent.ControllerInterface
import Schach.model.figureComponent._
import Schach.model.gameFieldComponent.gameFieldBaseImpl.GameField
import com.google.inject.Guice
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class TuiSpec extends AnyWordSpec with Matchers {

  "A Tui" should {
    val injector = Guice.createInjector(new GameFieldModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
    val tui = new Tui(controller)
    val input = "A1 F2"
    var field = new GameField
    "work correctly on undoing an invalid command and loading an invalid save" in {
      tui.interactWithUser("new")
      val old = controller.gameFieldToString

      tui.interactWithUser("undo")
      controller.gameFieldToString should be(old)

      tui.interactWithUser("redo")
      controller.gameFieldToString should be(old)

      tui.interactWithUser("load")
      controller.gameFieldToString should be(old)
    }
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
      tui.interactWithUser("move A1 A2")
      controller.moveIsValid(tui.readInput("A2 A3")) should be(true)
      controller.moveIsValid(tui.readInput("A1 A1")) should be(false)
      controller.gameField.getFigure(0,2) should be(None)
      val old = controller.gameFieldToString
      tui.interactWithUser("move XY ZX")
      controller.gameFieldToString should be(old)
      tui.interactWithUser("machmal XY ZX")
      controller.gameFieldToString should be(old)
      tui.interactWithUser("move A2 A3")
      controller.gameFieldToString should not be (old)
    }

    "undo and redo a move" in {
      tui.interactWithUser("move B7 A6")
      val old = controller.gameFieldToString

      tui.interactWithUser("undo")
      controller.gameFieldToString should not be (old)

      tui.interactWithUser("redo")
      controller.gameFieldToString should be (old)
    }
    "save and load a state" in {
      tui.interactWithUser("move B1 A3")
      tui.interactWithUser("save")
      val old = controller.gameFieldToString
      tui.interactWithUser("move A3 B5")
      tui.interactWithUser("load")
      controller.gameFieldToString should be(old)
    }
    "act accordingly to check and checkmate" in {
      tui.interactWithUser("new")
      tui.interactWithUser("move F2 F4")
      tui.interactWithUser("move E7 E5")
      tui.interactWithUser("move A2 A4")
      tui.interactWithUser("move D7 D5")
      tui.interactWithUser("move E1 H4")
      controller.isChecked() should be(true)

      tui.interactWithUser("new")
      tui.interactWithUser("move F2 F4")
      tui.interactWithUser("move E7 E5")
      tui.interactWithUser("move E1 H4")
      controller.isCheckmate() should be(true)

      tui.interactWithUser("new")
      tui.interactWithUser("move F2 F4")
      tui.interactWithUser("move E7 E5")
      controller.isChecked() should be (false)
      tui.printGameStatus()
      tui.interactWithUser("move A2 A4")
      tui.interactWithUser("move D7 D5")
      tui.interactWithUser("move E1 H4")
      controller.isChecked() should be (true)
      tui.printGameStatus()

      tui.interactWithUser("new")
      tui.interactWithUser("move C2 C4")
      tui.interactWithUser("move D7 D5")
      controller.isCheckmate() should be(false)
      tui.printGameStatus()
      tui.interactWithUser("move H2 H4")
      tui.interactWithUser("move E8 A4")
      controller.isCheckmate() should be(true)
      tui.printGameStatus()
    }
    "save and load a savefile" in {
      tui.interactWithUser("new")
      tui.interactWithUser("move H2 H4")
      tui.interactWithUser("move B7 B5")
      val old = controller.gameFieldToString
      tui.interactWithUser("save_game")
      tui.interactWithUser("move C2 C3")
      tui.interactWithUser("move A7 A5")
      controller.gameFieldToString should not be (old)
      tui.interactWithUser("load_game")
      controller.gameFieldToString should be (old)
    }

    "switch the Pawn when he has reached the other side of the GameField" should {

      "change the Pawn into a Queen" in {
        tui.interactWithUser("new")
        tui.interactWithUser("move G2 G4")
        tui.interactWithUser("move H7 H5")
        tui.interactWithUser("move A7 A6")
        tui.interactWithUser("move G4 H5")
        tui.interactWithUser("move H8 H6")
        tui.interactWithUser("move A2 A3")
        tui.interactWithUser("move H6 C6")
        tui.interactWithUser("move H5 H6")
        tui.interactWithUser("move C6 C5")
        tui.interactWithUser("move H6 H7")
        tui.interactWithUser("move C5 C4")
        tui.interactWithUser("move H7 H8")
        tui.interactWithUser("save_game")
        tui.interactWithUser("switch queen")
        controller.gameField.getFigure(7, 7).get shouldBe a[Queen]
      }
      "change the Pawn into a Rook, Knight or Bishop" in {
        tui.interactWithUser("new")
        tui.interactWithUser("load_game")
        tui.convertPawn("rook")
        controller.gameField.getFigure(7, 7).get shouldBe a[Rook]

        tui.interactWithUser("new")
        tui.interactWithUser("load_game")
        tui.convertPawn("knight")
        controller.gameField.getFigure(7, 7).get shouldBe a[Knight]

        tui.interactWithUser("new")
        tui.interactWithUser("load_game")
        tui.convertPawn("bishop")
        controller.gameField.getFigure(7, 7).get shouldBe a[Bishop]

        tui.convertPawn("abc")
      }
    }


  }

}
