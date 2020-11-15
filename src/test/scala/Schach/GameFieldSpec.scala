package Schach

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameFieldSpec extends AnyWordSpec with Matchers {


  "Figure should from " should {
    val gameField = GameField()
    gameField.init()

    val figure = gameField.isSet(1 ,1)
    gameField.moveTo(1, 1, 2, 3)
    "move to" in {
      gameField.isSet(2, 3) should be(true)
      gameField.isSet(1,1) should be(false)
    }

    "should not move to" in {
      gameField.moveTo(1, 1, 2,5) should be(false)
    }

    "have a nice String representation" in {
      gameField.toString shouldBe a[String]
    }
  }
}