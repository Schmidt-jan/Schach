package Schach

import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameFieldSpec extends AnyWordSpec with Matchers {

  "Figure should from " should {
    var gameField = new GameField()


    val figure = gameField.getFigure(1, 1)
    "should be" in {
      figure.get  mustBe a [Pawn]
    }

    gameField = gameField.moveTo(1, 1, 2, 3)
    "move to" in {
      gameField.getFigure(1, 1) should be(None)
      gameField.getFigure(2,3).get mustBe a [Pawn]
    }

    "have a nice String representation" in {
      gameField.toString shouldBe a[String]
    }
  }
}