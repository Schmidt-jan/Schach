package Schach

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameFieldSpec extends AnyWordSpec with Matchers {


  "Figure should from " should {
    val gameField = GameField()
    gameField.init()

    val figure = gameField.getFigure(1 ,1)
    gameField.moveTo(1, 1, 2, 3)
    "move to" in {
      gameField.getFigure(2, 3) should be(figure)
      gameField.getFigure(1,1) should be(null)
    }

    "should not move to" in {
      gameField.moveTo(1, 1, 2,5) should be(false)
    }

    "have a nice String representation" in {
      gameField.toString shouldBe a[String]
    }
  }
}