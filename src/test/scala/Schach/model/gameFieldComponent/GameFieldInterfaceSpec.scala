package Schach.model.gameFieldComponent

import Schach.model.gameFieldComponent.gameFieldBaseImpl.ChessGameFieldBuilder
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameFieldInterfaceSpec extends AnyWordSpec with Matchers{

  "An Interface" should {

    "have some states" in {
      val builder = new ChessGameFieldBuilder
      val gameField: GameFieldInterface = builder.getNewGameField
      gameField.RUNNING should be (0)
      gameField.CHECKED should be (1)
      gameField.CHECKMATE should be (2)
      gameField.MOVE_ILLEGAL should be (3)
      gameField.PAWN_REACHED_END should be (4)
    }
  }
}
